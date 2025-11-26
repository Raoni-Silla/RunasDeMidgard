package br.com.raoni.RunasDeMidgard.Factory;

import br.com.raoni.RunasDeMidgard.Enum.EnemyType;
import br.com.raoni.RunasDeMidgard.Repository.LootRepository;
import br.com.raoni.RunasDeMidgard.model.Enemy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MonsterFactory {

    @Autowired
    private LootRepository lootRepo;

    //pega o tipo do inimigo e retorna o inimigo inteiro montado
    public Enemy criar (EnemyType type){

        return null;
    }

}
