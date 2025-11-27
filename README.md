# 🐉 Runas de Midgard (Back-End API)

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow)

> **Uma API RESTful robusta para gerenciamento de um MMORPG, focada em arquitetura escalável, relacionamentos complexos de banco de dados e regras de negócio avançadas.**

---

## 📖 Sobre o Projeto

O **Runas de Midgard** é o motor (Back-End) de um jogo de RPG Online. Diferente de CRUDs simples, este projeto resolve problemas reais de modelagem de dados em jogos, como inventários dinâmicos, tabelas de loot com probabilidade (Drop Rate) e instanciação de inimigos baseada em protótipos.

O objetivo é demonstrar o domínio do ecossistema **Spring** aplicado a um cenário de alta complexidade lógica, preparado para lidar com a economia e combate do jogo.

---

## 🛠️ Tech Stack & Ferramentas

* **Linguagem:** Java 21 (LTS)
* **Framework:** Spring Boot 3
* **Persistência:** Spring Data JPA / Hibernate
* **Banco de Dados:** H2 (Dev) / MariaDB (Prod)
* **Ferramentas:** Maven, Lombok, Git
* **Qualidade de Código:** Bean Validation, Tratamento Global de Exceções (`@ControllerAdvice`)

---

## 🏛️ Arquitetura e Decisões Técnicas

Este projeto vai além do MVC básico, implementando padrões de design para resolver problemas específicos de Game Design e escalabilidade:

### 1. Modelagem Avançada de Dados (JPA)
* **Chaves Compostas (`@EmbeddedId`):** Implementado nas tabelas de ligação `Inventory` (Personagem + Item) e `EnemyLoot` (Inimigo + Item).
    * *Por que?* Isso permite que a relação em si carregue atributos vitais como **quantidade** (no inventário) e **chance de drop** (na tabela de loot), garantindo integridade referencial forte.
* **Relacionamentos Complexos:** Uso extensivo de `@OneToMany`, `@ManyToOne` e `@ManyToMany` com entidades associativas gerenciadas manualmente para controle total da persistência.
* **Performance:** Estratégia `FetchType.LAZY` adotada como padrão para evitar o problema de *N+1 queries* e economizar memória.

### 2. Design Patterns Aplicados
* **Factory Pattern:** Implementação de uma `MonsterFactory` para centralizar a lógica de criação de inimigos complexos (Bosses, Mobs) a partir de definições prévias (Enums), garantindo encapsulamento.
* **Prototype Pattern (Conceitual):** O banco de dados armazena os "Moldes" (Blueprints) dos inimigos e itens. O sistema instancia cópias únicas para batalhas em tempo real, preservando os dados originais.

---

## 🧩 Estrutura do Banco de Dados (Entities)

O Core do sistema gira em torno destas entidades principais:

* **`Account`:** Gerenciamento de conta e autenticação do jogador.
* **`Avatar`:** O personagem jogável, com atributos (Nível, XP, Ouro) e relacionamentos.
* **`Enemy`:** Definição dos monstros, incluindo atributos de combate e Rank (Normal, Elite, Boss).
* **`Item` (Loot):** Definição de itens, equipamentos e consumíveis com raridade.
* **`Inventory`:** Tabela associativa (Chave Composta) que gerencia a posse e quantidade de itens pelos avatares.
* **`EnemyLoot`:** Tabela associativa (Chave Composta) que define a **Loot Table** de cada monstro com suas respectivas porcentagens de chance (RNG).

---

## 🚧 Funcionalidades (Roadmap)

### ✅ Implementado
- [x] Configuração inicial do Spring Boot e Banco de Dados.
- [x] Modelagem completa das Entidades e Relacionamentos.
- [x] Implementação de **Chaves Compostas** para Inventário e Drops.
- [x] Factory para criação e persistência de Inimigos.
- [x] CRUDs básicos via Service/Repository.

### 🔄 Em Desenvolvimento
- [ ] **Sistema de Batalha:** Lógica de turno, cálculo de dano e atualização de HP.
- [ ] **Sistema de Loot:** Algoritmo de RNG (Random Number Generator) para processar os drops após a morte do inimigo baseados na `dropChance`.
- [ ] **Endpoints REST:** Controllers para expor as ações do jogo (Atacar, Equipar, Comprar).

### 🔮 Futuro
- [ ] Integração com **Spring Security** e JWT para autenticação.
- [ ] Front-end em **Angular**.

---

## 🚀 Como Rodar Localmente

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/raoni-silla/runas-de-midgard.git](https://github.com/raoni-silla/runas-de-midgard.git)
   ```

2. **Compile o projeto:**
   ```bash
   ./mvnw clean install
   ```

3. **Execute:**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Acesse:**
   * A API estará rodando em `http://localhost:8080`.
   * Utilize o Console do H2 (se habilitado em dev) ou Postman para interagir com os dados.

---

## 👨‍💻 Autor

Desenvolvido por **Raoni Mendes Silla** como parte de estudos avançados em Back-End Java.
