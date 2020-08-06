# How To use

To run the app execute java -jar api-service-0.0.1.jar the jar file is located under /bin.
- The app will run on port 8080
- The account with id 1 will be created automatically 
- front end url /api 

###  debit operation method post url api/accounts/debit

request
```json

   {
       "account_id":1,
        "amount": -10.0
   }
```    

###  credit operation method post url api/accounts/credit

request
```json

   {
       "account_id":1,
        "amount": 10.0
   }
```   

###  obtain transactions method get url /api/accounts/{account_id}/ledger

response
```json

   {
       "account_id": 1,
       "transactions": [
           {
               "amount": 10.0,
               "date": "2020-08-06T05:26:14.782+0000",
               "transaction_type": "CREDIT"
           },
           {
               "amount": 10.0,
               "date": "2020-08-06T05:26:15.541+0000",
               "transaction_type": "CREDIT"
           },
           {
               "amount": 10.0,
               "date": "2020-08-06T05:26:16.199+0000",
               "transaction_type": "CREDIT"
           },
           {
               "amount": 10.0,
               "date": "2020-08-06T05:26:16.743+0000",
               "transaction_type": "CREDIT"
           },
           {
               "amount": -10.0,
               "date": "2020-08-06T05:26:19.304+0000",
               "transaction_type": "DEBIT"
           }
       ],
       "balance": 30.0
   }
```  
