package com.example.DatabaseService.service;

import com.example.DatabaseService.DTO.createUserDTO;
import com.example.DatabaseService.DTO.updateUserDTO;
import com.example.DatabaseService.entity.Users;
import com.example.DatabaseService.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return usersRepository.findById(id);
    }

    public Users createUser(createUserDTO createUserDTO){
        Users user = Users.builder()
                .email(createUserDTO.getEmail())
                .password(createUserDTO.getPassword())
                .firstName(createUserDTO.getFirstName())
                .lastName(createUserDTO.getLastName())
                .build();
        return usersRepository.save(user);
    }

    public Users updateUser(Long id, updateUserDTO updateUserDTO){
        return usersRepository.findById(id).map(user -> {
            user.setEmail(updateUserDTO.getEmail());
            user.setPassword(updateUserDTO.getPassword());
            user.setFirstName(updateUserDTO.getFirstName());
            user.setLastName(updateUserDTO.getLastName());
            return usersRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    public void deleteUser(Long id){
        Users user = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        usersRepository.deleteById(id);
    }

//    public Users addExpenseToUser(Long userId, Expenses expense) {
//        Users user = usersRepository.findById(userId).orElseThrow();
//        user.addExpense(expense);
//        return usersRepository.save(user);
//    }
//
//    public Users removeExpenseFromUser(Long userId, Expenses expense) {
//        Users user = usersRepository.findById(userId).orElseThrow();
//        user.removeExpense(expense);
//        return usersRepository.save(user);
//    }
//
//    public Users addNotificationToUser(Long userId, Notifications notification) {
//        Users user = usersRepository.findById(userId).orElseThrow();
//        user.addNotification(notification);
//        return usersRepository.save(user);
//    }
//
//    public Users removeNotificationFromUser(Long userId, Notifications notification) {
//        Users user = usersRepository.findById(userId).orElseThrow();
//        user.removeNotification(notification);
//        return usersRepository.save(user);
//    }
//
//    public Users addTransactionToUser(Long userId, Transactions transaction) {
//        Users user = usersRepository.findById(userId).orElseThrow();
//        user.addTransaction(transaction);
//        return usersRepository.save(user);
//    }
//
//    public Users removeTransactionFromUser(Long userId, Transactions transaction) {
//        Users user = usersRepository.findById(userId).orElseThrow();
//        user.removeTransaction(transaction);
//        return usersRepository.save(user);
//    }
//
//    public Users addBudgetToUser(Long userId, Budgets budget) {
//        Users user = usersRepository.findById(userId).orElseThrow();
//        user.addBudget(budget);
//        return usersRepository.save(user);
//    }
//
//    public Users removeBudgetFromUser(Long userId, Budgets budget) {
//        Users user = usersRepository.findById(userId).orElseThrow();
//        user.removeBudget(budget);
//        return usersRepository.save(user);
//    }

}
