package br.com.raoni.RunasDeMidgard.Factory;

import br.com.raoni.RunasDeMidgard.Enum.EnemyType;
import br.com.raoni.RunasDeMidgard.Enum.RankEnemy;
import br.com.raoni.RunasDeMidgard.Enum.Rarity;
import br.com.raoni.RunasDeMidgard.Repository.EnemyRepository;
import br.com.raoni.RunasDeMidgard.Repository.LootRepository;
import br.com.raoni.RunasDeMidgard.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Component
public class MonsterFactory {

    @Autowired
    private LootRepository lootRepo;

    @Autowired
    private EnemyRepository enemyRepo;

    //pega o tipo do inimigo e retorna o inimigo inteiro montado
    public Enemy create (EnemyType type){

        switch (type) {

            case BANDIT:
                Enemy bandit = new Enemy("Bandit", "a common thief");

                bandit.setStatistics(new Statistics(
                        4L, 6L, 2L, 4L     // STR, DEX, INT, VIT
                ));

                bandit.setSkills(List.of(
                        new Skill("stab", "a wound from a rusty knife"),
                        new Skill("punch", "a hard punch right in the nose")
                ));

                Loot lootBandit = new Loot(
                        "rusty knife",
                        3L,
                        BigDecimal.valueOf(3.50),
                        Rarity.COMMON,
                        "a simple rusty knife"
                );

                bandit.setLootDrops(List.of(
                        new EnemyLoot(bandit, lootBandit, BigDecimal.valueOf(0.25))
                ));

                return bandit;


            case GOBLIN:
                Enemy goblin = new Enemy("Goblin", "a green, short, and repulsive creature");

                goblin.setStatistics(new Statistics(
                        3L, 7L, 2L, 3L
                ));

                goblin.setSkills(List.of(
                        new Skill("bite", "The bite can be fatal"),
                        new Skill("theft", "This creature stole your gold")
                ));

                Loot lootGoblin = new Loot(
                        "goblin tooth",
                        0L,
                        BigDecimal.valueOf(5.20),
                        Rarity.RARE,
                        "Goblin tooth, can be used in potions"
                );

                goblin.setLootDrops(List.of(
                        new EnemyLoot(goblin, lootGoblin, BigDecimal.valueOf(0.20))
                ));

                return goblin;


            case SKELETON:
                Enemy skeleton = new Enemy("Skeleton", "an old animated skeleton");

                skeleton.setStatistics(new Statistics(
                        5L, 2L, 1L, 7L   // alta vitalidade (defesa)
                ));

                skeleton.setSkills(List.of(
                        new Skill("bone strike", "A hard strike with bone fragments"),
                        new Skill("rattle fear", "A frightening rattling sound")
                ));

                Loot lootSkeleton = new Loot(
                        "bone fragment",
                        1L,
                        BigDecimal.valueOf(2.00),
                        Rarity.COMMON,
                        "Bone fragment useful for necromancy crafting"
                );

                skeleton.setLootDrops(List.of(
                        new EnemyLoot(skeleton, lootSkeleton, BigDecimal.valueOf(0.40))
                ));

                return skeleton;


            case WOLF:
                Enemy wolf = new Enemy("Wolf", "a fast and aggressive wild wolf");

                wolf.setStatistics(new Statistics(
                        6L, 8L, 1L, 3L
                ));

                wolf.setSkills(List.of(
                        new Skill("claw", "A quick slash with claws"),
                        new Skill("howl", "A frightening howl that boosts aggression")
                ));

                Loot lootWolf = new Loot(
                        "wolf pelt",
                        2L,
                        BigDecimal.valueOf(4.30),
                        Rarity.RARE,
                        "Used to craft light protective gear"
                );

                wolf.setLootDrops(List.of(
                        new EnemyLoot(wolf, lootWolf, BigDecimal.valueOf(0.30))
                ));

                return wolf;


            case ORC:
                Enemy orc = new Enemy("Orc", "a strong and brutal green warrior");

                orc.setStatistics(new Statistics(
                        9L, 3L, 1L, 8L
                ));

                orc.setSkills(List.of(
                        new Skill("smash", "A powerful strike capable of crushing bones"),
                        new Skill("rage", "The orc enters a berserk state, boosting strength")
                ));

                Loot lootOrc = new Loot(
                        "orc tusk",
                        4L,
                        BigDecimal.valueOf(6.80),
                        Rarity.RARE,
                        "A tough tusk used in blacksmith forging"
                );

                orc.setLootDrops(List.of(
                        new EnemyLoot(orc, lootOrc, BigDecimal.valueOf(0.35))
                ));

                return orc;


            case SLIME:
                Enemy slime = new Enemy("Slime", "a gelatinous creature with high endurance");

                slime.setStatistics(new Statistics(
                        2L, 1L, 2L, 10L   // muita vitalidade
                ));

                slime.setSkills(List.of(
                        new Skill("acid touch", "Weak acid that burns slightly"),
                        new Skill("split", "Attempts to divide into smaller slimes")
                ));

                Loot lootSlime = new Loot(
                        "slime gel",
                        0L,
                        BigDecimal.valueOf(1.80),
                        Rarity.COMMON,
                        "Slime gel frequently used in alchemy"
                );

                slime.setLootDrops(List.of(
                        new EnemyLoot(slime, lootSlime, BigDecimal.valueOf(0.50))
                ));

                return slime;


            case BAT:
                Enemy bat = new Enemy("Bat", "a small, fast and annoying flying creature");

                bat.setStatistics(new Statistics(
                        1L, 9L, 1L, 2L
                ));

                bat.setSkills(List.of(
                        new Skill("sonic squeak", "A high-frequency sound that causes confusion")
                ));

                Loot lootBat = new Loot(
                        "bat wing",
                        0L,
                        BigDecimal.valueOf(2.10),
                        Rarity.COMMON,
                        "Useful in magical or alchemical recipes"
                );

                bat.setLootDrops(List.of(
                        new EnemyLoot(bat, lootBat, BigDecimal.valueOf(0.25))
                ));

                return bat;


            case UNDEAD:
                Enemy undead = new Enemy("Undead", "a cursed creature animated by dark magic");

                undead.setStatistics(new Statistics(
                        5L, 3L, 5L, 6L
                ));

                undead.setSkills(List.of(
                        new Skill("dark claw", "A cursed necrotic strike"),
                        new Skill("plague breath", "A cloud of disease is expelled"),
                        new Skill("revive", "Can rise again through black magic")
                ));

                Loot lootUndead = new Loot(
                        "dark essence",
                        2L,
                        BigDecimal.valueOf(7.50),
                        Rarity.RARE,
                        "A fragment of dark energy extracted from undead creatures"
                );

                undead.setLootDrops(List.of(
                        new EnemyLoot(undead, lootUndead, BigDecimal.valueOf(0.15))
                ));

                return undead;

            case GHOST:
                Enemy ghost = new Enemy("Ghost of Chaos", "a spectral entity that haunts and terrifies its victims");

                // mais rápido e inteligente, mas frágil fisicamente
                ghost.setStatistics(new Statistics(
                        3L, 10L, 8L, 2L   // STR, DEX, INT, VIT
                ));

                // Skills únicas do ghost
                ghost.setSkills(List.of(
                        new Skill("haunting wail", "A terrifying scream that can paralyze enemies"),
                        new Skill("ethereal touch", "Deals damage that ignores physical armor"),
                        new Skill("phase shift", "Becomes intangible, evading the next attack")
                ));

                // Loot do ghost
                Loot lootGhost = new Loot(
                        "phantom essence",
                        1L,
                        BigDecimal.valueOf(15.0),
                        Rarity.EPIC,
                        "A fragment of ghostly energy, valuable for magic crafting"
                );

                ghost.setLootDrops(List.of(
                        new EnemyLoot(ghost, lootGhost, BigDecimal.valueOf(0.30)) // 30% chance de drop
                ));

                return ghost;

            case DRAGON:
                Enemy dragon = new Enemy("Dragon Blood", "a massive dragon with scales as hard as steel");

                dragon.setStatistics(new Statistics(
                        15L, 5L, 10L, 20L  // STR, DEX, INT, VIT
                ));

                dragon.setSkills(List.of(
                        new Skill("fire breath", "Unleashes a devastating stream of fire"),
                        new Skill("tail sweep", "Sweeps the area with its massive tail"),
                        new Skill("wing gust", "Knocks back enemies with powerful wing gusts")
                ));

                Loot lootDragon = new Loot(
                        "dragon scale",
                        5L,
                        BigDecimal.valueOf(100.0),
                        Rarity.LEGENDARY,
                        "Scales of a dragon, extremely valuable for armor crafting"
                );

                dragon.setLootDrops(List.of(
                        new EnemyLoot(dragon, lootDragon, BigDecimal.valueOf(0.20)) // 20% chance
                ));

                return dragon;


            case DEMONKING:
                Enemy demonKing = new Enemy("Demon King", "the ruler of a demonic army, radiating dark energy");

                demonKing.setStatistics(new Statistics(
                        18L, 6L, 12L, 18L
                ));

                demonKing.setSkills(List.of(
                        new Skill("dark strike", "A strike infused with dark magic"),
                        new Skill("summon minions", "Calls lesser demons to fight alongside"),
                        new Skill("hellfire aura", "Deals continuous fire damage to nearby enemies")
                ));

                Loot lootDemonKing = new Loot(
                        "demonic core",
                        1L,
                        BigDecimal.valueOf(200.0),
                        Rarity.LEGENDARY,
                        "A powerful core of demonic energy, used in ultimate spells"
                );

                demonKing.setLootDrops(List.of(
                        new EnemyLoot(demonKing, lootDemonKing, BigDecimal.valueOf(0.25))
                ));

                return demonKing;


            case VAMPIRELORD:
                Enemy vampireLord = new Enemy("Vampire Lord of Chaos", "an ancient vampire with immense power");

                vampireLord.setStatistics(new Statistics(
                        12L, 10L, 15L, 12L
                ));

                vampireLord.setSkills(List.of(
                        new Skill("blood drain", "Drains life from enemies to heal itself"),
                        new Skill("shadow step", "Teleports behind the target, striking unexpectedly"),
                        new Skill("mesmerize", "Charms enemies, making them fight for it temporarily")
                ));

                Loot lootVampireLord = new Loot(
                        "vampire fang",
                        3L,
                        BigDecimal.valueOf(150.0),
                        Rarity.LEGENDARY,
                        "A fang from the Vampire Lord, useful for crafting rare artifacts"
                );

                vampireLord.setLootDrops(List.of(
                        new EnemyLoot(vampireLord, lootVampireLord, BigDecimal.valueOf(0.30))
                ));

                return vampireLord;

            default:
                throw new IllegalArgumentException("Unknown enemy type: " + type);
        }



    }

        public Enemy randomEnemyByType() {

        EnemyType [] typesEnemiesArray = EnemyType.values(); //pega os tipos do enum

        int size  = typesEnemiesArray.length;//pega o tamanho do array

        Random random = new Random(); //instancia um objeto random

        int randomIndex = random.nextInt(size); //pega um indice aleatorio

        return create(typesEnemiesArray[randomIndex]); //cria um inimigo aleatorio

    }


}


