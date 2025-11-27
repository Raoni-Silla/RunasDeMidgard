package br.com.raoni.RunasDeMidgard.Repository;

import br.com.raoni.RunasDeMidgard.Enum.Rarity;
import br.com.raoni.RunasDeMidgard.model.Loot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LootRepository extends JpaRepository<Loot, Long> {

    // 10. findByLessThan - Itens baratos (Preço < X)
    List<Loot> findByPriceLessThan(BigDecimal price);

    // 11. findByLessThanEqual - Itens fracos (Dano <= X)
    List<Loot> findByDamageLessThanEqual(BigDecimal damage);

    // 12. findByGreaterThan - Itens caros (Preço > X)
    List<Loot> findByPriceGreaterThan(BigDecimal price);

    // 13. findByGreaterThanEqual - Itens lendários (Dano >= X)
    List<Loot> findByDamageGreaterThanEqual(BigDecimal damage);

    // 5. findByIn - Buscar itens de uma lista de raridades (Ex: Só RARO e EPICO)
    List<Loot> findByRarityIn(List<Rarity> rarities);

}
