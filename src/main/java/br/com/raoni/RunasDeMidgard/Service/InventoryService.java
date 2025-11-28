package br.com.raoni.RunasDeMidgard.Service;

import br.com.raoni.RunasDeMidgard.Repository.InventoryRepository;
import br.com.raoni.RunasDeMidgard.model.Avatar;
import br.com.raoni.RunasDeMidgard.model.Inventory;
import br.com.raoni.RunasDeMidgard.model.Loot;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepo;


    @Transactional
    public void addItem(Avatar avatar, Loot loot, Long qntd){

        Inventory inventory = new Inventory(avatar,loot);

        Optional <Inventory> inventoryFind = inventoryRepo.findByIdInventory(inventory.getIdInventory());


        inventoryFind.ifPresent(inventory1 -> {
           inventory1.setQuantity(inventory1.getQuantity() + qntd);
           inventoryRepo.save(inventory1);
        });

       if (inventoryFind.isEmpty()){
           Inventory inventory2 = new Inventory(avatar,loot,qntd);
           inventoryRepo.save(inventory2);
       }

    }

    @Transactional
    public void removeItem(Avatar avatar, Loot loot, Long qntd) {

        Inventory inventoryReal = inventoryRepo.findByAvatarAndLoot(avatar, loot)
                .orElseThrow(() -> new RuntimeException("Item not found in inventory."));


        if (inventoryReal.getQuantity() < qntd) {
            throw new RuntimeException("You don't have enough items to remove.");
        }

        if (inventoryReal.getQuantity() > qntd) {

            inventoryReal.setQuantity(inventoryReal.getQuantity() - qntd);
            inventoryRepo.save(inventoryReal); // Atualiza
        } else {
            // Quantidade igual (Zera o item) -> Deleta do banco
            inventoryRepo.delete(inventoryReal);
        }
    }


    public Set<Inventory> listItens(){
        return inventoryRepo.findAll().stream().sorted(Comparator.comparing(Inventory::getQuantity)).collect(Collectors.toCollection(HashSet::new));
    }


}
