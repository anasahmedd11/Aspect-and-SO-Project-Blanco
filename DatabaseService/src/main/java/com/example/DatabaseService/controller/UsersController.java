package com.example.DatabaseService.controller;

import com.example.DatabaseService.DTO.createUserDTO;
import com.example.DatabaseService.DTO.updateUserDTO;
import com.example.DatabaseService.entity.Users;
import com.example.DatabaseService.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = usersService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable long id) {
        Optional<Users> user = usersService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable long id, @Valid @RequestBody updateUserDTO updateUserDTO){
        try{
            Users updatedUser = usersService.updateUser(id, updateUserDTO);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@Valid @RequestBody createUserDTO createUserDTO) {
        Users newUser = usersService.createUser(createUserDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Users> deleteUser(@PathVariable long id){
        try {
            usersService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("/{id}/expenses")
//    public ResponseEntity<Users> addExpenseToUser(@PathVariable Long id, @RequestBody Expenses expense) {
//        Users updatedUser = usersService.addExpenseToUser(id, expense);
//        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}/expenses/{expenseId}")
//    public ResponseEntity<Void> removeExpenseFromUser(@PathVariable Long id, @PathVariable Long expenseId) {
//        Expenses expense = expenseRepository.findById(expenseId).orElseThrow();
//        usersService.removeExpenseFromUser(id, expense);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @PostMapping("/{id}/notifications")
//    public ResponseEntity<Users> addNotificationToUser(@PathVariable Long id, @RequestBody Notifications notification) {
//        Users updatedUser = usersService.addNotificationToUser(id, notification);
//        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}/notifications/{notificationId}")
//    public ResponseEntity<Void> removeNotificationFromUser(@PathVariable Long id, @PathVariable Long notificationId) {
//        Notifications notification = notificationRepository.findById(notificationId).orElseThrow();
//        usersService.removeNotificationFromUser(id, notification);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @PostMapping("/{id}/budgets")
//    public ResponseEntity<Users> addBudgetToUser(@PathVariable Long id, @RequestBody Budgets budget) {
//        Users updatedUser = usersService.addBudgetToUser(id, budget);
//        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}/budgets/{budgetId}")
//    public ResponseEntity<Void> removeBudgetFromUser(@PathVariable Long id, @PathVariable Long budgetId) {
//        Budgets budget = budgetRepository.findById(budgetId)
//                .orElseThrow(() -> new RuntimeException("Budget not found"));
//        usersService.removeBudgetFromUser(id, budget);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @PostMapping("/{id}/transactions")
//    public ResponseEntity<Users> addTransactionToUser(@PathVariable Long id, @RequestBody Transactions transaction) {
//        Users updatedUser = usersService.addTransactionToUser(id, transaction);
//        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}/transactions/{transactionId}")
//    public ResponseEntity<Void> removeTransactionFromUser(@PathVariable Long id, @PathVariable Long transactionId) {
//        Transactions transaction = transactionRepository.findById(transactionId)
//                .orElseThrow(() -> new RuntimeException("Transaction not found"));
//        usersService.removeTransactionFromUser(id, transaction);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
