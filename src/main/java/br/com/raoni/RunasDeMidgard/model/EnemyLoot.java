package br.com.raoni.RunasDeMidgard.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class EnemyLoot {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private EnemyLootPk id = new EnemyLootPk();

    @ManyToOne
    @MapsId("enemyId") //vincula o id do inimigo com o id do loot
    @JoinColumn(name = "enemy_id") //coloca a fk direto na tabela de enemy loot?
    private Enemy enemy;

    @ManyToOne
    @MapsId("lootId")
    @JoinColumn(name = "loot_id")
    private Loot loot;

    //chance de drop de itens de acordo com inimigos
    @DecimalMin("0.0")
    @DecimalMax("100.0")
    private BigDecimal dropChance;

    public EnemyLoot(Enemy enemy, Loot loot, BigDecimal dropChance) {
        this.enemy = enemy;
        this.loot = loot;
        this.dropChance = dropChance;
    }

}
/*
Um inimigo pode dropar vários itens
Um item pode ser dropado por vários inimigos
Cada par Enemy + Loot tem a sua própria dropChance
um inimigo pode dropar varios itens, e um item pode dropar de varios inimigos

uma relação n:n com atributos extras
*/