package com.example.MoneyManagementService.Controllers;

import com.example.MoneyManagementService.DTO.TransactionDTO;
import com.example.MoneyManagementService.entity.Transactions;
import com.example.MoneyManagementService.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Transactions>> getAllUserTransactions(@PathVariable Long id) {
        List<Transactions> transactions = transactionService.getAllTransactions(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Transactions> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        Transactions transaction = transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transactions> updateTransaction(@PathVariable Long id, @RequestBody TransactionDTO transactionDTO) {
        try {
            Transactions transaction = transactionService.updateTransaction(id, transactionDTO);
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Transactions> deleteTransaction(@PathVariable Long id) {
        try {
            transactionService.deleteTransaction(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
