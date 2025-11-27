package br.com.raoni.RunasDeMidgard.model;

import br.com.raoni.RunasDeMidgard.Enum.RpgClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    private String name;

    @Min(0)
    private Long level;

    @Min(0)
    private Long xp;

    @DecimalMin("0")//melhor pra validar numeros do tipo big decimal
    private BigDecimal gold;

    private LocalDateTime lastLogin;

    @Enumerated(EnumType.STRING) //pra ser salvo como string no banco
    private RpgClass heroClass;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToMany(mappedBy = "avatars")
    @JsonIgnore //fica do lado passivo
    private List<Task> tasks = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "statistics_id")
    private Statistics statistics;

    @OneToMany(mappedBy = "avatar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inventory> inventory = new ArrayList<>();

    public Avatar(String name, RpgClass heroClass, Account account) {

        this.name = name;
        this.heroClass = heroClass;
        this.account = account;
        this.gold = BigDecimal.ZERO;
        this.lastLogin = LocalDateTime.now();
        this.level = 0L;

    }

    public void addTask(Task task) {
        this.tasks.add(task);
        task.getAvatars().add(this);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
        task.getAvatars().remove(this);
    }

    public void addItem(Inventory item) {
        this.inventory.add(item);
        item.setAvatar(this); // Garante o vínculo
    }

    public void removeItem(Inventory item) {
        this.inventory.remove(item);
        item.setAvatar(null);
    }

}
