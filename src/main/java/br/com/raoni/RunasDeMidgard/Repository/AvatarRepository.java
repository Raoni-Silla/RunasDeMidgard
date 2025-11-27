package br.com.raoni.RunasDeMidgard.Repository;

import br.com.raoni.RunasDeMidgard.Enum.RpgClass;
import br.com.raoni.RunasDeMidgard.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar,Long> {

    //1. encontrar por nome ignorando maiusculas e minusculas
    Optional <Avatar> findByNameIgnoreCase(String name);

    // 3. findByAnd Buscar por Classe E Nível maior que X
    List<Avatar> findByHeroClassAndLevelGreaterThan(RpgClass heroClass, Integer level);

    // 9. findByOrderBy - Ranking de Nível
    List<Avatar> findByOrderByLevel();

    // 6. findByOrderByDesc - Ranking de Ouro (Os mais ricos primeiro)
    List<Avatar> findByOrderByGoldDesc();

    // 2. findFirstBy O "Top 1" do servidor (Ouro)
    Optional<Avatar> findFirstByOrderByGoldDesc();

}
