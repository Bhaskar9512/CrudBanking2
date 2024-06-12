package com.example.CrudBanking2;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountDetail createAccount(AccountDetail account) {
        account.setAccountNumber(UUID.randomUUID().toString().replace("-", "").substring(0, 10));
        account.setBalance(0);
        account.setActive(true);
        return accountRepository.save(account);
    }

    public void closeAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public List<AccountDetail> getAllActiveAccounts() {
        return accountRepository.findByActive(true);
    }

    public List<AccountDetail> getAllInactiveAccounts() {
        return accountRepository.findByActive(false);
    }

    public AccountDetail transferMoney(Long fromAccountId, Long toAccountId, double amount) {
        Optional<AccountDetail> fromAccountOpt = accountRepository.findById(fromAccountId);
        Optional<AccountDetail> toAccountOpt = accountRepository.findById(toAccountId);

        if (fromAccountOpt.isPresent() && toAccountOpt.isPresent()) {
            AccountDetail fromAccount = fromAccountOpt.get();
            AccountDetail toAccount = toAccountOpt.get();

            if (fromAccount.getBalance() >= amount) {
                fromAccount.setBalance(fromAccount.getBalance() - amount);
                toAccount.setBalance(toAccount.getBalance() + amount);
                accountRepository.save(fromAccount);
                return accountRepository.save(toAccount);
            }
        }
        return null;
    }
}