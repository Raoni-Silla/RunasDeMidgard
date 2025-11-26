package br.com.raoni.RunasDeMidgard.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class InventoryPk implements Serializable {

    private Long avatarId;
    private Long lootId;

}
