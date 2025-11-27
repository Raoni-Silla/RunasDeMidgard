package br.com.raoni.RunasDeMidgard.model;

import br.com.raoni.RunasDeMidgard.Enum.EnemyType;
import br.com.raoni.RunasDeMidgard.Enum.RankEnemy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Enemy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "statistics_id")
    private Statistics statistics;

    @Enumerated(EnumType.STRING)
    private EnemyType type;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "enemy_skill", //nome da tabela no banco
            joinColumns = @JoinColumn(name = "enemy_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List <Skill> skills = new ArrayList<>();

    @OneToMany(mappedBy = "enemy", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<EnemyLoot> lootDrops = new ArrayList<>();



    public RankEnemy getRank(){
        return type.getRank();
    }

    public Enemy (String name, String description){
        this.name = name;
        this.description = description;
    }
}
