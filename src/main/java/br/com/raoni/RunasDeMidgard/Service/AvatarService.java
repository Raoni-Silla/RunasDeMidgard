package br.com.raoni.RunasDeMidgard.Service;

import br.com.raoni.RunasDeMidgard.Repository.AvatarRepository;
import br.com.raoni.RunasDeMidgard.model.Avatar;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AvatarService {

    @Autowired
    private AvatarRepository avatarRepository;

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


    //parei na logica de xp, e no monsterFactory!!!!
    public ResponseEntity<Avatar> earnXp(Avatar avatar) {

        Avatar FindAvatar = findAvatarByName(avatar.getName()).getBody();




    }

}
