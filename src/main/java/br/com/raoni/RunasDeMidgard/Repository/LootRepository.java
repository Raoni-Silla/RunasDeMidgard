package br.com.raoni.RunasDeMidgard.Repository;

import br.com.raoni.RunasDeMidgard.model.Loot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LootRepository extends JpaRepository<Loot, Long> {



}
