package com.example.banking_app3.repository;
import com.example.banking_app3.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface AccountRepository extends JpaRepository<Account, Long> {


}
