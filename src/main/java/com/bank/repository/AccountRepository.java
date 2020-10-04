package com.bank.repository;


import java.util.List;

import com.bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

    List<Account> findAccountsByClientId(String clientId);

}
