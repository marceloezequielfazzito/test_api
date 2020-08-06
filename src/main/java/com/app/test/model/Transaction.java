package com.app.test.model;

import java.util.Date;

public class Transaction {

    private Double amount;
    private TransactionType type;
    private Date date;

    public Transaction(Double amount, TransactionType type, Date date) {
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
