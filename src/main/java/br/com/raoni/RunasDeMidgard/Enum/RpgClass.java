package br.com.raoni.RunasDeMidgard.Enum;

/*
WARRIOR = objeto do enum
"Warrior" = atributo dentro desse objeto
getDisplayName() = método pra acessar o atributo dentro de WARRIOR
Enum = “classe especial com instâncias pré-definidas”
Cada constante do enum (WARRIOR, MAGE, etc.) = instância única e imutável
Você não cria novas instâncias; elas são criadas automaticamente pela JVM
O valor passado no construtor ("Warrior") é armazenado no atributo interno do objeto
*/

public enum RpgClass {

    WARRIOR("Warrior"),
    MAGE("Mage"),
    ARCHER("Archer"),
    ROGUE("Rogue"),
    CLERIC("Cleric"),
    PALADIN("Paladin"),
    NECROMANCER("Necromancer"),
    BERSERKER("Berserker"),
    DRUID("Druid"),
    HUNTER("Hunter");

    //é um atributo do objeto do enum
    private final String displayName;

    // Construtor do enum que se autoinstancia
    RpgClass(String displayName) {
        this.displayName = displayName;
    }

    //pra pegar o nome mais "amigavel" do enum
    public String getDisplayName() {
        return displayName;
    }

}
