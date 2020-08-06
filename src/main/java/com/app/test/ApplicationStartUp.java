package com.app.test;

import com.app.test.model.Account;
import com.app.test.model.Transaction;
import com.app.test.model.TransactionType;
import com.app.test.model.User;
import com.app.test.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ApplicationStartUp {

    private AccountRepository accountRepository;

    @Autowired
    public ApplicationStartUp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void afterStartup() {
       User user = new User(1l,"John","Wick");
       Account account = new Account(user);
        Date now = new Date();
        account.addTransaction(new Transaction(10.00, TransactionType.CREDIT, now));
        account.addTransaction(new Transaction(50.00, TransactionType.CREDIT, now));
        account.addTransaction(new Transaction(100.00, TransactionType.CREDIT, now));
        account.addTransaction(new Transaction(-10.00, TransactionType.DEBIT, now));
        account.addTransaction(new Transaction(-50.00, TransactionType.DEBIT, now));
        account.addTransaction(new Transaction(-90.00, TransactionType.DEBIT, now));
       accountRepository.save(account);
    }
}
