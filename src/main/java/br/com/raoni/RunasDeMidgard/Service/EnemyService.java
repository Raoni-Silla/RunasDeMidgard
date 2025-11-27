package br.com.raoni.RunasDeMidgard.Service;

import br.com.raoni.RunasDeMidgard.Factory.MonsterFactory;
import br.com.raoni.RunasDeMidgard.Repository.EnemyRepository;
import br.com.raoni.RunasDeMidgard.model.Enemy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnemyService {

    @Autowired
    EnemyRepository enemyRepo;

    @Autowired
    MonsterFactory monsterFactory;

    public ResponseEntity<Enemy> save (Enemy enemy){

        Enemy enemyFind = enemyRepo.findById(enemy.getId()).orElseThrow(() -> new RuntimeException("Enemy is not found"));

        Enemy builtEnemy =  monsterFactory.create(enemy.getType());

        return ResponseEntity.ok(builtEnemy);

    }

    public ResponseEntity<Void> delete(Enemy enemy){
        enemyRepo.delete(enemy);
        return ResponseEntity.ok().build();
    }

}
