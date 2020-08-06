package com.app.test.model;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private Long id;
    private User user;
    private List<Transaction> ledge;
    private Double balance;

    public Account(User user) {
        this.user = user;
        this.ledge = new ArrayList<>();
        this.balance = 0.0D;
    }

    public void addTransaction(Transaction transaction){
        ledge.add(transaction);
        balance = ledge.stream().map(t -> t.getAmount()).reduce(Double::sum).get();
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<Transaction> getLedge() {
        return ledge;
    }

    public Double getBalance() {
        return balance;
    }
}
