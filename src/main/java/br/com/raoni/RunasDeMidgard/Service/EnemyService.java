package br.com.raoni.RunasDeMidgard.Service;

import br.com.raoni.RunasDeMidgard.Enum.EnemyType;
import br.com.raoni.RunasDeMidgard.Factory.MonsterFactory;
import br.com.raoni.RunasDeMidgard.Repository.EnemyRepository;
import br.com.raoni.RunasDeMidgard.model.Enemy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnemyService {

    @Autowired
    EnemyRepository enemyRepo;

    @Autowired
    MonsterFactory monsterFactory;

    public ResponseEntity<Enemy> save(Enemy enemy) {

        // Cria um inimigo completo com base no tipo
        Enemy builtEnemy = monsterFactory.create(enemy.getType());

        // Salva no banco
        Enemy savedEnemy = enemyRepo.save(builtEnemy);

        return ResponseEntity.ok(savedEnemy);
    }

    public ResponseEntity<Void> delete(Enemy enemy){

        if(enemy.getId() == null){
            throw new RuntimeException("id não existente");
        }

        enemyRepo.deleteById(enemy.getId());
        return ResponseEntity.ok().build();

    }

    public ResponseEntity<List<Enemy>> findAll(){
        List <Enemy> enemyList = enemyRepo.findAll();
        return ResponseEntity.ok(enemyList);
    }

    public ResponseEntity<List<Enemy>> findByType(Enemy enemy){
        List<Enemy> enemiesFound = enemyRepo.findByType(enemy.getType());
        return ResponseEntity.ok(enemiesFound);
    }

    public ResponseEntity<Enemy> randomEnemy() {
        Enemy enemy = monsterFactory.randomEnemyByType();
        enemyRepo.save(enemy);
        return ResponseEntity.ok(enemy);
    }



}
