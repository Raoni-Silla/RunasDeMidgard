package br.com.raoni.RunasDeMidgard.model;

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
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Long id;

    @EqualsAndHashCode.Include
    String name;

    String description;

    @ManyToMany(mappedBy = "skills")
    @JsonIgnore
    List<Enemy> enemiesSkills = new ArrayList<>();


    public Skill(String name, String description){
        this.name = name;
        this.description = description;
    }

}
