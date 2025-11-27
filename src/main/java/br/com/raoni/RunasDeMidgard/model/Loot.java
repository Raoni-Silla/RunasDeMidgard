package br.com.raoni.RunasDeMidgard.model;

import br.com.raoni.RunasDeMidgard.Enum.Rarity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Loot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @Min(1)
    private Long damage;

    @DecimalMin("0")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Rarity rarity;

    @NotBlank
    @Size(min = 1, max = 500)
    private String description;

    @OneToMany(mappedBy = "loot")
    @JsonIgnore
    private List<EnemyLoot> enemyDrops = new ArrayList<>();


    public Loot(String name, Long damage, BigDecimal price, Rarity rarity, String description) {
        this.name = name;
        this.damage = damage;
        this.price = price;
        this.rarity = rarity;
        this.description = description;
    }
}
