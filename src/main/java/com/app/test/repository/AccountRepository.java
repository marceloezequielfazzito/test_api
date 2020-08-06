package com.app.test.repository;

import com.app.test.model.Account;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccountRepository {

    private Map<Long, Account> base;
    private long lastId;

    public AccountRepository() {
        this.base = new ConcurrentHashMap<>();
        this.lastId = 0;
    }

    public void save(Account account){
        lastId++;
        base.put(lastId,account);
    }

    public void update(Account account){
        base.put(account.getId(),account);
    }

    public Account get(Long id){
        return base.get(id);
    }

    public boolean exists(long id){
        return base.containsKey(id);
    }

}
