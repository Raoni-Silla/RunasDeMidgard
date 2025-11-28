package br.com.raoni.RunasDeMidgard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String nickname;

    @NotBlank(message = "email")
    private String email;

    @NotBlank
    @Size(min =  5, max = 100) //valida o tamanho da string
    private String password;

    private LocalDate dateOfCreation;

    @JsonIgnore
    private boolean vip;

    @JsonIgnore //Pra usuario não conseguir se autocolocar como vip ou não ativado
    private boolean active;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)//remover um Avatar da lista, ele também será deletado do banco automaticamente.
    private List<Avatar> avatars;


    public Account(String nickname, String email, String password) {

        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.dateOfCreation = LocalDate.now();
        this.active = true;
        this.vip = false;

    }
}
