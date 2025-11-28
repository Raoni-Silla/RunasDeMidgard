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
    private InventoryPk idInventory = new InventoryPk();

    @ManyToOne
    @MapsId("avatarId")
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @ManyToOne
    @MapsId("lootId")
    @JoinColumn(name = "loot_id")
    private Loot loot;

    private Long quantity;

    public Inventory(Avatar avatar, Loot loot, Long quantity) {
        this.avatar = avatar;
        this.loot = loot;
        this.quantity = quantity;
    }

    public Inventory(Avatar avatar, Loot loot) {
        this.avatar = avatar;
        this.loot = loot;
    }
}
