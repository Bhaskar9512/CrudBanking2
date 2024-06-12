package com.example.CrudBanking2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/CheckAcc")
    public ResponseEntity<AccountDetail> createAccount(@RequestBody AccountDetail account) {
        AccountDetail createdAccount = accountService.createAccount(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }


    @DeleteMapping("/closeAcc/{id}")
    public ResponseEntity<Void> closeAccount(@PathVariable Long id) {
        accountService.closeAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

 
    @GetMapping("/activeAcc")
    public ResponseEntity<List<AccountDetail>> getAllActiveAccounts() {
        List<AccountDetail> activeAccounts = accountService.getAllActiveAccounts();
        return new ResponseEntity<>(activeAccounts, HttpStatus.OK);
    }

    
    @GetMapping("/inactiveAcc")
    public ResponseEntity<List<AccountDetail>> getAllInactiveAccounts() {
        List<AccountDetail> inactiveAccounts = accountService.getAllInactiveAccounts();
        return new ResponseEntity<>(inactiveAccounts, HttpStatus.OK);
    }

  
    @PostMapping("/transferMoney")
    public ResponseEntity<AccountDetail> transferMoney(@RequestParam Long fromAccountId, @RequestParam Long toAccountId, @RequestParam double amount) {
        AccountDetail updatedAccount = accountService.transferMoney(fromAccountId, toAccountId, amount);
        if (updatedAccount != null) {
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}