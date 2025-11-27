package br.com.raoni.RunasDeMidgard.Service;

import br.com.raoni.RunasDeMidgard.Repository.AvatarRepository;
import br.com.raoni.RunasDeMidgard.Repository.TaskRepository;
import br.com.raoni.RunasDeMidgard.model.Avatar;
import br.com.raoni.RunasDeMidgard.model.Task;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AvatarService {

    @Autowired
    private AvatarRepository avatarRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Transactional//pra cancelar a transição caso de errado
    public ResponseEntity<Avatar> saveAvatar(Avatar avatar) {

        if (avatarRepository.existsById(avatar.getId())) {
            throw new RuntimeException("Avatar with id " + avatar.getId() + " already exists");
        }

        if (avatar.getName() == null || avatar.getName().isEmpty()) {
            throw new RuntimeException("Avatar name cannot be empty");
        }

        if (avatarRepository.findByNameIgnoreCase(avatar.getName()).isPresent()) {
            throw new RuntimeException("Avatar with name " + avatar.getName() + " already exists");
        }

        if (avatar.getAccount() == null) {
            throw new RuntimeException("Avatar account cannot be empty");
        }

        Avatar avatarSaved = avatarRepository.save(avatar);

        //retorna o avatar salvo com seu id
        return ResponseEntity.ok(avatarSaved);

    }

    public ResponseEntity<Avatar> findAvatarByName(String name) {
       Avatar avatar = avatarRepository.findByNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("Avatar with name " + name + " not found"));
       return ResponseEntity.ok(avatar);
    }

    public ResponseEntity<Set<Avatar>> findAllAvatars() {
        List<Avatar> avatars = avatarRepository.findAll();
        Set <Avatar> orderedListOfAvatars = avatars.stream() //cria uma stream de dados
                .sorted(Comparator.comparing(Avatar::getLevel)) //ordena por nivel
                .collect(Collectors.toCollection(LinkedHashSet::new));

        return ResponseEntity.ok(orderedListOfAvatars); //retorna a lista de todos os avatares da pessoa ordenado por level
    }



    private Long xpForNextLevel(Long level) {
        return 2L * (level * level); // XP necessária para subir do nível atual para o próximo
        // se o level for 4, 4*4 = 16, 2 * 16 = 32 exp pro proximo nivel
    }


    public ResponseEntity<Avatar> updateLevel(Avatar avatar, Long xpEarned) {

        avatar.setXp(avatar.getXp() + xpEarned);

        while (avatar.getXp() >= xpForNextLevel(avatar.getLevel())) { // pega o xp atual, vamos supor, 50, enquanto 50 for maior que 30


            avatar.setXp(avatar.getXp() - xpForNextLevel(avatar.getLevel())); //50 - 30 = 20

            avatar.setLevel(avatar.getLevel() + 1); // 4++ = 5

            avatar.getStatistics().setHealth(avatar.getLevel() * 100);

        }

        avatarRepository.save(avatar);

        return ResponseEntity.ok(avatar);

    }

    public ResponseEntity<Void> deleteAvatar(String name) {
        Avatar avatar = avatarRepository.findByNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("Avatar with name " + name + " not found"));
        avatarRepository.delete(avatar);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Avatar> toEnterMission (Avatar avatar, Task task) {
        Avatar avatarFind = avatarRepository.findById(avatar.getId()).orElseThrow(() -> new RuntimeException("Avatar with id " + avatar.getId() + " not found"));

        avatarFind.getTasks().add(task);

        avatarRepository.save(avatarFind);

        return ResponseEntity.ok(avatarFind);
    }

    public ResponseEntity<Avatar> toExitMission (Avatar avatar, Task task) {

        Avatar avatarFind = avatarRepository.findById(avatar.getId()).orElseThrow(() -> new RuntimeException("Avatar with id " + avatar.getId() + " not found"));
        Task taskFind = taskRepository.findById(task.getId()).orElseThrow(() -> new RuntimeException("task with id " + task.getId() + " not found"));

        avatarFind.getTasks().remove(task);

        avatarRepository.save(avatarFind);

        return ResponseEntity.ok(avatarFind);

    }

}
