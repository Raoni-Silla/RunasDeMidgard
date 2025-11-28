package br.com.raoni.RunasDeMidgard.Service;

import br.com.raoni.RunasDeMidgard.Repository.AccountRepository;
import br.com.raoni.RunasDeMidgard.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public ResponseEntity<Account> createAccount (Account account) {

        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }

        if (account.getDateOfCreation() == null) {
            account.setDateOfCreation(LocalDate.now());
        }

        if (accountRepository.existsAccountByEmailIgnoreCase(account.getEmail())) {
            throw new IllegalArgumentException("Account with email " + account.getEmail() + " already exists");
        }

        if (accountRepository.existsAccountByNicknameIgnoreCase(account.getNickname())) {
            throw new IllegalArgumentException("Account with nickname " + account.getNickname() + " already exists");
        }

        account.setVip(false);
        account.setActive(true);

        Account accountCreated = accountRepository.save(account);

        return ResponseEntity.status(HttpStatus.CREATED).body(accountCreated);
    }

    public ResponseEntity<Account> readAccountByName(String name) {

        String sanitized = name.strip();

        Account found = accountRepository.findByNicknameIgnoreCase(sanitized)
                .orElseThrow(() ->
                        new IllegalArgumentException("Account with nickname " + sanitized + " does not exist")
                );

        return ResponseEntity.ok(found);
    }

    public ResponseEntity<Account> updateAccountNickame(String nameActualy, String nameNew) {

        Account account = accountRepository.findByNicknameIgnoreCase(nameActualy)
                .orElseThrow(() ->
                        new IllegalArgumentException("Account with name " + nameActualy + " not found")
                );

        String sanitizedNew = nameNew.strip(); //remove espaços em brancos

        if (accountRepository.existsAccountByNicknameIgnoreCase(sanitizedNew)) {
            throw new IllegalArgumentException("Account with new nickname " + sanitizedNew + " already exists");
        }

        account.setNickname(sanitizedNew);

        Account updated = accountRepository.save(account);

        return ResponseEntity.ok(updated);
    }

    public ResponseEntity<Account> deleteAccountByName(String name) {

        Account find = accountRepository.findByNicknameIgnoreCase(name).orElseThrow(() ->
                new IllegalArgumentException("Account with nickname " + name + " does not exist"));

        accountRepository.delete(find);

        return ResponseEntity.ok().build();

    }


}





