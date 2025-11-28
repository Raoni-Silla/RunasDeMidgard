package br.com.raoni.RunasDeMidgard.Repository;

import br.com.raoni.RunasDeMidgard.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsAccountByEmailIgnoreCase(String email);

    boolean existsAccountByNicknameIgnoreCase(String nickname);

    Optional <Account> findByNicknameIgnoreCase(String nickname);


}
