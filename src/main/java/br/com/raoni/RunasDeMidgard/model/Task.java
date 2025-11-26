package br.com.raoni.RunasDeMidgard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //equals e hashcode apenas com oque eu incluir
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @NotBlank
    @Size(min = 1, max = 500)
    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @JoinTable(
            name = "task_avatars", //nome da tabela no banco
            joinColumns = @JoinColumn(name = "task_id"),//nome da coluna que referencia essa entidade(task) na tabela
            inverseJoinColumns = @JoinColumn(name = "avatar_id")//nome coluna que referencia a outra entidade (avatar)


    )
    List<Avatar> avatars =  new ArrayList<>();


    public Task (String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addAvatar(Avatar avatar) {
        this.avatars.add(avatar);
        avatar.getTasks().add(this);
    }

    public void removeAvatar(Avatar avatar) {
        this.avatars.remove(avatar);
        avatar.getTasks().remove(this);
    }

    /*
    Task perde o avatar da sua lista
    Avatar perde a task da sua lista
    */

}
