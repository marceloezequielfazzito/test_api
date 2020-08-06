package com.app.test.api.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.app.test.model.TransactionType;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponse {

    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("date")
    private Date date;
    @JsonProperty("transaction_type")
    private TransactionType transactionType;

    public TransactionResponse(Long accountId, Double amount, Date date, TransactionType transactionType) {
        this.accountId = accountId;
        this.amount = amount;
        this.date = date;
        this.transactionType = transactionType;
    }

    public TransactionResponse(Double amount, Date date, TransactionType transactionType) {
        this.amount = amount;
        this.date = date;
        this.transactionType = transactionType;
    }

    public Long getAccountId() {
        return accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }
}
