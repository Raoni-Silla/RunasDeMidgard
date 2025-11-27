package br.com.raoni.RunasDeMidgard.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Inventory {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private InventoryPk id = new InventoryPk();

    @ManyToOne
    @MapsId("avatarId")
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @ManyToOne
    @MapsId("lootId")
    @JoinColumn(name = "loot_id")
    private Loot loot;

    private int quantity;

    public Inventory(Avatar avatar, Loot loot, int quantity) {
        this.avatar = avatar;
        this.loot = loot;
        this.quantity = quantity;
    }
}
