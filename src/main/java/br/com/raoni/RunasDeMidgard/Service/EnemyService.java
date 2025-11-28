package br.com.raoni.RunasDeMidgard.Service;

import br.com.raoni.RunasDeMidgard.Factory.MonsterFactory;
import br.com.raoni.RunasDeMidgard.Repository.EnemyRepository;
import br.com.raoni.RunasDeMidgard.model.Enemy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EnemyService {

    @Autowired
    EnemyRepository enemyRepo;

    @Autowired
    MonsterFactory monsterFactory;


    public Enemy create(Enemy enemy) {

        // Cria um inimigo completo com base no tipo
        Enemy builtEnemy = monsterFactory.create(enemy.getType());

        // Salva no banco
        return enemyRepo.save(builtEnemy);
    }

    @Transactional
    public void delete(Enemy enemy){

        if(enemy.getId() == null){
            throw new RuntimeException("id não existente");
        }

        enemyRepo.deleteById(enemy.getId());

    }

    public List<Enemy> findAll(){
        return enemyRepo.findAll();
    }

    public List<Enemy> findByType(Enemy enemy){
        return enemyRepo.findByType(enemy.getType());
    }

    public Enemy spawRandomEnemy() {
        Enemy enemy = monsterFactory.randomEnemyByType();
        enemyRepo.save(enemy);
        return enemy;
    }

    @Transactional
    public List<Enemy> searchForNameOrDescriptionEnemy (String name, String description) {

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

        return enemyFinds;
    }

    //procura inimigos por tamanho da vida
    public List<Enemy> findEnemiesByHealthBetween(Long min, Long max) {
        return enemyRepo.findByStatisticsHealthBetween(min, max);
    }

    @Transactional
    public void deleteMonstersWhereHealthIsLessThan(Long health) {
        enemyRepo.deleteByStatisticsHealthLessThan(health);
    }



}
