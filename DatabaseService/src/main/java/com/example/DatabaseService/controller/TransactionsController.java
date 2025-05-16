package com.example.DatabaseService.controller;

import com.example.DatabaseService.DTO.CreateTransactionDTO;
import com.example.DatabaseService.DTO.UpdateTransactionDTO;
import com.example.DatabaseService.entity.Transactions;
import com.example.DatabaseService.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/db-service/transaction")
public class TransactionsController {

    private final TransactionService transactionService;

    public TransactionsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<List<Transactions>> getAllUserTransactions(@PathVariable Long id) {
        List<Transactions> transactions = transactionService.getAllUserTransactions(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Transactions> createTransaction(@RequestBody CreateTransactionDTO transactionDTO) {
        Transactions transaction = transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transactions> updateTransaction(@PathVariable Long id, @RequestBody UpdateTransactionDTO transactionDTO) {
        try {
            Transactions transaction = transactionService.updateTransaction(id, transactionDTO);
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Transactions> deleteTransaction(@PathVariable Long id) {
        try {
            transactionService.deleteTransaction(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}