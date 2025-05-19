package com.example.DatabaseService.service;

import com.example.DatabaseService.DTO.CreateTransactionDTO;
import com.example.DatabaseService.DTO.UpdateTransactionDTO;
import com.example.DatabaseService.entity.Expenses;
import com.example.DatabaseService.entity.Transactions;
import com.example.DatabaseService.entity.Users;
import com.example.DatabaseService.repository.ExpensesRepository;
import com.example.DatabaseService.repository.TransactionRepository;
import com.example.DatabaseService.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor

public class TransactionService {
    private final UsersRepository usersRepository;
    private final ExpensesRepository expensesRepository;
    private TransactionRepository transactionRepository;


    public List<Transactions> getAllUserTransactions(Long userId) {
        return transactionRepository.findBySenderId(userId);
    }

    public Transactions createTransaction(CreateTransactionDTO transactionDTO) {

        Long senderId = transactionDTO.getSenderId();
        Users sender = usersRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + senderId));

        Long receiverId = transactionDTO.getReceiverId();
        Users receiver = usersRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + receiverId));

        Long expenseId = transactionDTO.getExpenseId();
        Expenses expenses = expensesRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + expenseId));

        Transactions transaction = new Transactions(sender, receiver, expenses);
        return transactionRepository.save(transaction);
    }

    public Transactions updateTransaction(Long id, UpdateTransactionDTO updateBudgetDTO) {
        Transactions currentTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
        BeanUtils.copyProperties(updateBudgetDTO, currentTransaction);
        return transactionRepository.save(currentTransaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

}
