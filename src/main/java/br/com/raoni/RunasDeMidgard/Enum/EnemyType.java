package br.com.raoni.RunasDeMidgard.Enum;

import lombok.Getter;

@Getter
public enum EnemyType {

    // Common enemies
    GOBLIN("Goblin", RankEnemy.NORMAL),
    SKELETON("Skeleton", RankEnemy.NORMAL),
    WOLF("Wolf", RankEnemy.NORMAL),
    BANDIT("Bandit", RankEnemy.NORMAL),
    ORC("Orc", RankEnemy.NORMAL),
    SLIME("Slime", RankEnemy.NORMAL),
    BAT("Bat", RankEnemy.NORMAL),
    UNDEAD("Undead", RankEnemy.NORMAL),

    // Mini-boss / special
    GHOST("Ghost of Chaos", RankEnemy.SPECIAL),

    // Bosses
    DRAGON("Dragon Blood", RankEnemy.BOSS),
    DEMONKING("Demon King", RankEnemy.BOSS),
    VAMPIRELORD("Vampire Lord of Chaos", RankEnemy.BOSS),
    LICH("Lich God of Death", RankEnemy.GLOBAL),
    DARKKNIGHT("Mega Dark Knight", RankEnemy.GLOBAL);

    private final String displayName;
    private final RankEnemy rank;

    EnemyType(String displayName, RankEnemy rank) {
        this.displayName = displayName;
        this.rank = rank;
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
