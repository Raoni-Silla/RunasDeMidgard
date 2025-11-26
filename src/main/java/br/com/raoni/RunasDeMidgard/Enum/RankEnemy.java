package br.com.raoni.RunasDeMidgard.Enum;

import lombok.Getter;

@Getter
public enum RankEnemy {

    NORMAL("normal"),        // inimigos fracos/medianos
    ELITE("elite"),          // inimigos fortes
    LEGENDARY("legendary"),  // inimigos fortes e raros
    SPECIAL("special"),      // mini-boss
    BOSS("boss"),            // boss normal
    GLOBAL("global boss");   // boss megaforte

    private final String displayName;

    RankEnemy(String displayName) {
        this.displayName = displayName;
    }
}
