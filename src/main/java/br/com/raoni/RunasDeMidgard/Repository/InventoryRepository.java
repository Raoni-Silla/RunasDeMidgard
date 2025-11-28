package br.com.raoni.RunasDeMidgard.Repository;

import br.com.raoni.RunasDeMidgard.model.Avatar;
import br.com.raoni.RunasDeMidgard.model.Inventory;
import br.com.raoni.RunasDeMidgard.model.InventoryPk;
import br.com.raoni.RunasDeMidgard.model.Loot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository <Inventory,InventoryPk> {
    Optional<Inventory> findByIdInventory(InventoryPk id);

    Optional<Inventory> findByAvatarAndLoot(Avatar avatar, Loot loot);
}
