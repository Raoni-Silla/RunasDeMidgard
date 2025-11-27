package br.com.raoni.RunasDeMidgard.Repository;

import br.com.raoni.RunasDeMidgard.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar,Long> {

    Optional <Avatar> findByNameIgnoreCase(String name);

}
