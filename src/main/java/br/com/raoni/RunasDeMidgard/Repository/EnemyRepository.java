package br.com.raoni.RunasDeMidgard.Repository;

import br.com.raoni.RunasDeMidgard.Enum.EnemyType;
import br.com.raoni.RunasDeMidgard.model.Enemy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnemyRepository extends JpaRepository<Enemy,Long> {

    // 4. findByOr - Busca Global (Por nome OU descrição)
    // achar o monstro que tem 'Dragão' no nome ou na descrição
    List<Enemy> findByNameContainingOrDescriptionContainingIgnoreCase(String name, String description);

    List<Enemy> findByStatisticsHealthBetween(Long min, Long max);


    // 8. Deletar monstros com HP menor que 10
    // Métodos delete precisam de @Transactional no Service que chamar
    void deleteByStatisticsHealthLessThan(Long hp);

//    deleteBy
//            Statistics         // objeto dentro do Enemy
//                       Health         // campo dentro de Statistics
//                              LessThan     // operador
    List<Enemy> findByType(EnemyType  type);
}
