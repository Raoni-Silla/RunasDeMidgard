package br.com.raoni.RunasDeMidgard.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class EnemyLootPk implements Serializable {
    private Long enemyId;
    private Long lootId;
}
