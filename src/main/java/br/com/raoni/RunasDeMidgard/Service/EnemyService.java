package br.com.raoni.RunasDeMidgard.Service;

import br.com.raoni.RunasDeMidgard.Factory.MonsterFactory;
import br.com.raoni.RunasDeMidgard.Repository.EnemyRepository;
import br.com.raoni.RunasDeMidgard.model.Enemy;
import br.com.raoni.RunasDeMidgard.model.Statistics;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Transactional
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

    @Transactional
    public ResponseEntity<List<Enemy>> searchForNameOrDescriptionEnemy (String name, String description) {

        if (name == null && description == null) {
            throw new RuntimeException("At least one of the fields must be filled in");
        }

        if (name == null) {
            name = "";
        }

        if (description == null) {
            description = "";
        }

        List <Enemy> enemyFinds = enemyRepo.findByNameContainingOrDescriptionContainingIgnoreCase(name, description);

        if (enemyFinds.isEmpty()) {
            throw new RuntimeException("No enemies found with given name or description");
        }

        return ResponseEntity.ok(enemyFinds);
    }

    //procura inimigos por tamanho da vida
    public ResponseEntity<List<Enemy>> findEnemiesByHealthBetween(Long min, Long max) {
        List<Enemy> enemies = enemyRepo.findByStatisticsHealthBetween(min, max);
        return ResponseEntity.ok(enemies);
    }

    @Transactional
    public ResponseEntity<Void> deleteMonstersWhereHealthIsLessThan(Long health) {

        enemyRepo.deleteByStatisticsHealthLessThan(health);

        return ResponseEntity.ok().build();
    }

}
