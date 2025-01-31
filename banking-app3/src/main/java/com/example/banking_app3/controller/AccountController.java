package com.example.banking_app3.controller;

import com.example.banking_app3.dto.AccountDto;
import com.example.banking_app3.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;
    public AccountController(AccountService accountService){   // constructor.
        this.accountService = accountService;
    }
    //add account rest api
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    // desposit.
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){
        AccountDto accountDto = accountService.deposit(id, request.get("Amount"));
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }
    //  withdraw.
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request){
        double amount = request.get("Amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    // get list of all accounts.
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllaccounts(){
        List<AccountDto> accountDtos = accountService.getAllAccounts();
        return new ResponseEntity<>(accountDtos, HttpStatus.OK);

    }

    // deleting the account.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable Long id){
        accountService.deleteAccount(id);
        return new ResponseEntity<>("Account deleted", HttpStatus.OK);
    }
}
