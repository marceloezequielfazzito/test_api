package com.app.test.api;

import com.app.test.api.request.TransactionRequest;
import com.app.test.api.response.LedgeResponse;
import com.app.test.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AccountAPI {

    private AccountService accountService;

    @Autowired
    public AccountAPI(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/accounts/debit" , consumes = "application/json")
    public @ResponseBody
    ResponseEntity<?> debit(@Valid @RequestBody TransactionRequest transactionRequest){
        if(transactionRequest.getAmount() >= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(" balance must be negative ");
        }
        return ResponseEntity.ok(accountService.debit(transactionRequest));
    }

    @PostMapping(value = "/accounts/credit" , consumes = "application/json")
    public @ResponseBody
    ResponseEntity<?> credit(@Valid @RequestBody TransactionRequest transactionRequest){
        if(transactionRequest.getAmount() <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(" balance must be greater than 0 ");
        }
        return ResponseEntity.ok(accountService.credit(transactionRequest));
    }


    @GetMapping(value = "/accounts/{account_id}/ledger" , produces = "application/json")
    public @ResponseBody ResponseEntity<?> ledger(@PathVariable(name = "account_id") Long accountId){
        LedgeResponse ledger = accountService.ledger(accountId);
        return ResponseEntity.ok(ledger);
    }
}
