package br.com.raoni.RunasDeMidgard.Enum;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum EnemyType {

    // Common enemies
    GOBLIN("Goblin", RankEnemy.NORMAL,22.5), //
    SKELETON("Skeleton", RankEnemy.NORMAL, 12.5),
    WOLF("Wolf", RankEnemy.NORMAL, 8),
    BANDIT("Bandit", RankEnemy.NORMAL, 30.5),
    ORC("Orc", RankEnemy.NORMAL,45.6),
    SLIME("Slime", RankEnemy.NORMAL,22.2),
    BAT("Bat", RankEnemy.NORMAL,5),
    UNDEAD("Undead", RankEnemy.NORMAL,12),

    // Mini-boss / special
    GHOST("Ghost of Chaos", RankEnemy.SPECIAL, 100.0),

    // Bosses
    DRAGON("Dragon Blood", RankEnemy.BOSS, 250.5),
    DEMONKING("Demon King", RankEnemy.BOSS, 300),
    VAMPIRELORD("Vampire Lord of Chaos", RankEnemy.BOSS, 450.2),
    LICH("Lich God of Death", RankEnemy.GLOBAL, 500.0),
    DARKKNIGHT("Mega Dark Knight", RankEnemy.GLOBAL,650.5);

    private final String displayName;

    private final RankEnemy rank;

    private final double gold;

    EnemyType(String displayName, RankEnemy rank, double gold) {
        this.displayName = displayName;
        this.rank = rank;
        this.gold = gold;
    }

    //tem que ser do rank boss ou de rank global
    public boolean isBoss() {
        return rank == RankEnemy.BOSS || rank == RankEnemy.GLOBAL;
    }

    //tem que ser de rank normal
    public boolean isCommon() {
        return rank == RankEnemy.NORMAL;
    }

    //é especial? (rank special, rank elite ou rank lendario?)
    public boolean isSpecial() {
        return rank == RankEnemy.SPECIAL || rank == RankEnemy.ELITE || rank == RankEnemy.LEGENDARY;
    }
}
