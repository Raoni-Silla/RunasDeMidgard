package br.com.raoni.RunasDeMidgard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(0)
    private Long health;

    @Min(0)
    private Long strength;

    @Min(0)
    private Long dexterity;

    @Min(0)
    private Long intelligence;

    @Min(0)
    private Long vitality;

    @OneToOne (mappedBy = "statistics")
    @JsonIgnore
    private Avatar avatar;

    @OneToOne(mappedBy = "statistics")
    @JsonIgnore
    private Statistics statistics;

    public Statistics ( Long health,Long strength, Long dexterity, Long intelligence, Long vitality) {
        this.health = health;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.vitality = vitality;
    }
}
