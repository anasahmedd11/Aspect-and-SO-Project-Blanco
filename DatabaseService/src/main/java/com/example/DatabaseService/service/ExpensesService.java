package com.example.DatabaseService.service;

import com.example.DatabaseService.DTO.CreateExpenseDTO;
import com.example.DatabaseService.DTO.UpdateExpenseDTO;
import com.example.DatabaseService.entity.Categories;
import com.example.DatabaseService.entity.Expenses;
import com.example.DatabaseService.entity.Users;
import com.example.DatabaseService.repository.CategoriesRepository;
import com.example.DatabaseService.repository.ExpensesRepository;
import com.example.DatabaseService.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExpensesService {

    private final ExpensesRepository expensesRepository;
    private final CategoriesRepository categoriesRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public ExpensesService(ExpensesRepository expensesRepository, CategoriesRepository categoriesRepository,
                           UsersRepository usersRepository) {
        this.expensesRepository = expensesRepository;
        this.categoriesRepository = categoriesRepository;
        this.usersRepository = usersRepository;
    }

    public List<Expenses> getAllExpenses() {
        return expensesRepository.findAll();
    }
    public Expenses getExpenseById(Long id) {
        return expensesRepository.findById(id).orElse(null);
    }

    @Transactional
    public Expenses createExpense(CreateExpenseDTO createExpenseDTO) {
        Long userId = createExpenseDTO.getUserId();
        Users existingUser = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Long categoryId = createExpenseDTO.getCategoryId();
        Categories existingCategory = categoriesRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
        Expenses expense = new Expenses(
                existingUser,
                createExpenseDTO.getAmount(),
                existingCategory,
                createExpenseDTO.getNotes(),
                createExpenseDTO.getDate()
        );
        return expensesRepository.save(expense);
    }

    @Transactional
    public Expenses updateExpense(Long id, UpdateExpenseDTO updateExpenseDTO) {
        Expenses existingExpense = expensesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
        BeanUtils.copyProperties(updateExpenseDTO, existingExpense, getNullPropertyNames(updateExpenseDTO));
        return expensesRepository.save(existingExpense);
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    @Transactional
    public void deleteExpense(Long id) {
        expensesRepository.deleteById(id);
    }

}
