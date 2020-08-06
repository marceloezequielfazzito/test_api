package com.app.test.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LedgeResponse {

    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("transactions")
    private List<TransactionResponse> transactionsDTO;
    @JsonProperty("balance")
    private double balance;

    public LedgeResponse(Long accountId, List<TransactionResponse> transactionsDTO, double balance) {
        this.accountId = accountId;
        this.transactionsDTO = transactionsDTO;
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public List<TransactionResponse> getTransactionsDTO() {
        return transactionsDTO;
    }

    public double getBalance() {
        return balance;
    }
}
