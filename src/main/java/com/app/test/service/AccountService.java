package com.app.test.service;

import com.app.test.exceptions.BadRequestException;
import com.app.test.exceptions.NotFoundException;
import com.app.test.exceptions.ServiceUnavailableException;
import com.app.test.model.Account;
import com.app.test.model.Transaction;
import com.app.test.model.TransactionType;
import com.app.test.repository.AccountRepository;
import com.app.test.api.request.TransactionRequest;
import com.app.test.api.response.LedgeResponse;
import com.app.test.api.response.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private ExecutorService executorService;
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.executorService = Executors.newSingleThreadExecutor();
        this.accountRepository = accountRepository;
    }

    public TransactionResponse credit(TransactionRequest transactionRequest){
        return executeTransaction(transactionRequest, TransactionType.CREDIT);
    }

    public TransactionResponse debit(TransactionRequest transactionRequest){
        return executeTransaction(transactionRequest,TransactionType.DEBIT);
    }

    public LedgeResponse ledger(long accountId){
        Account account = getAccountIfExists(accountId);
        Double balance = account.getBalance();
        List<TransactionResponse> transactionResponses = account.getLedge().stream()
                .map(transaction -> new TransactionResponse(transaction.getAmount(), transaction.getDate(), transaction.getType()))
                .collect(Collectors.toList());
        return new LedgeResponse(accountId,transactionResponses,balance);
    }

    private TransactionResponse executeTransaction(TransactionRequest transactionRequest,TransactionType transactionType) {
        Future<TransactionResponse> transactionResponseFuture = executorService.submit(() -> {
            Long accountId = transactionRequest.getAccountId();
            Account account = getAccountIfExists(accountId);
            Double balance = account.getBalance();
            validateAccountBalance(transactionRequest, balance,transactionType);
            Double amount = transactionRequest.getAmount();
            Date now = new Date();
            account.addTransaction(new Transaction(amount, transactionType, now));
            accountRepository.save(account);
            return new TransactionResponse(account.getId(),amount,now,transactionType);
        });

        try {
            return transactionResponseFuture.get();
        } catch (InterruptedException e) {
           throw new ServiceUnavailableException("could not execute operation please try later");
        } catch (ExecutionException e) {
            throw new BadRequestException(e.getCause().getMessage());
        }
    }

    private void validateAccountBalance(TransactionRequest transactionRequest, Double balance, TransactionType transactionType) {
        if(transactionType == TransactionType.DEBIT && (balance + transactionRequest.getAmount()) < 0.0){
            throw new BadRequestException(" there is not balance left to make the transaction " );
        }
    }

    private Account getAccountIfExists(Long accountId) {
        boolean exists = accountRepository.exists(accountId);
        if (!exists) {
            throw new NotFoundException(" could not find account id " + accountId);
        }
        return accountRepository.get(accountId);
    }


}
