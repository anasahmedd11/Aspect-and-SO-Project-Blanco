package com.example.DatabaseService.service;

import com.example.DatabaseService.DTO.CategoryExpenseDTO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static com.example.DatabaseService.service.NullPropertyNameHelper.getNullPropertyNames;

@Service
@Transactional
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
                new Date()
        );
        return expensesRepository.save(expense);
    }


    public Expenses updateExpense(Long id, UpdateExpenseDTO updateExpenseDTO) {
        Expenses existingExpense = expensesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
        BeanUtils.copyProperties(updateExpenseDTO, existingExpense, getNullPropertyNames(updateExpenseDTO));
        return expensesRepository.save(existingExpense);
    }


    public void deleteExpense(Long id) {
        expensesRepository.deleteById(id);
    }

    public List<Expenses> getAllUserExpenses(Long userId) {
        Users existingUser = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return expensesRepository.findByUserId(existingUser.getId());
    }

    public List<Expenses> getUserMonthlyExpenses(Long userId) {
        LocalDate firstDateOfMonth = LocalDate.now().withDayOfMonth(1);
        Date startDate = Date.from(firstDateOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return expensesRepository.getUserExpenses(startDate, userId);
    }

    public List<Expenses> getUserWeeklyExpenses(Long userId) {
        LocalDate now = LocalDate.now();
        LocalDate startOfWeek = now.with(DayOfWeek.SATURDAY);
        Date startDate = Date.from(startOfWeek.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return expensesRepository.getUserExpenses(startDate, userId);
    }

    // get expenses by date
    public List<Expenses> getExpensesByDate(Long userId, Date date) {
        Users existingUser = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return expensesRepository.findByUserIdAndDate(existingUser.getId(), date);
    }

    public List<CategoryExpenseDTO> getTotalExpenseAmountByCategory(Long userId) {
        return expensesRepository.findTotalExpenseAmountByCategory(userId);
    }
}
