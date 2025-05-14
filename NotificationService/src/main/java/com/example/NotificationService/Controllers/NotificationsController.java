package com.example.NotificationService.Controllers;

import com.example.NotificationService.Entity.Notifications;
import com.example.NotificationService.Services.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/notification-service/notifications")
public class NotificationsController {
    private final NotificationsService notificationService;

    @Autowired
    public NotificationsController(NotificationsService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notifications>> getUserNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getUserNotifications(userId));
    }

    // Budget-related endpoints
    @PostMapping("/budget/limit")
    public ResponseEntity<Notifications> createBudgetLimitNotification(
            @RequestParam Long userId,
            @RequestParam String categoryName,
            @RequestParam int currentAmount,
            @RequestParam int limitAmount,
            @RequestParam Date sentAt) {
        return ResponseEntity.ok(notificationService.createBudgetLimitNotification(
                userId, categoryName, currentAmount, limitAmount, sentAt));
    }

    @PostMapping("/budget/expiration")
    public ResponseEntity<Notifications> createBudgetExpirationNotification(
            @RequestParam Long userId,
            @RequestParam String categoryName,
            @RequestParam Date expiresAt) {
        return ResponseEntity.ok(notificationService.createBudgetExpirationNotification(
                userId, categoryName, expiresAt));
    }

    @PostMapping("/budget/created")
    public ResponseEntity<Notifications> createBudgetCreatedNotification(
            @RequestParam Long userId,
            @RequestParam String categoryName,
            @RequestParam int limitAmount,
            @RequestParam Date sentAt) {
        return ResponseEntity.ok(notificationService.createBudgetCreatedNotification(
                userId, categoryName, limitAmount, sentAt));
    }

    @PostMapping("/expense/created")
    public ResponseEntity<Notifications> createExpenseNotification(
            @RequestParam Long userId,
            @RequestParam String categoryName,
            @RequestParam Double amount,
            @RequestParam String notes,
            @RequestParam Date sentAt) {
        return ResponseEntity.ok(notificationService.createExpenseNotification(
                userId, categoryName, amount, notes, sentAt));
    }

    @PostMapping("/expense/updated")
    public ResponseEntity<Notifications> createExpenseUpdatedNotification(
            @RequestParam Long userId,
            @RequestParam String categoryName,
            @RequestParam Double oldAmount,
            @RequestParam Double newAmount,
            @RequestParam Date sentAt) {
        return ResponseEntity.ok(notificationService.createExpenseUpdatedNotification(
                userId, categoryName, oldAmount, newAmount, sentAt));
    }

    @PostMapping("/transaction/sent")
    public ResponseEntity<Notifications> createTransactionSentNotification(
            @RequestParam Long userId,
            @RequestParam Long receiverId,
            @RequestParam Double amount,
            @RequestParam Date sentAt) {
        return ResponseEntity.ok(notificationService.createTransactionSentNotification(
                userId, receiverId, amount, sentAt));
    }

    @PostMapping("/transaction/received")
    public ResponseEntity<Notifications> createTransactionReceivedNotification(
            @RequestParam Long userId,
            @RequestParam Long senderId,
            @RequestParam Double amount,
            @RequestParam Date sentAt) {
        return ResponseEntity.ok(notificationService.createTransactionReceivedNotification(
                userId, senderId, amount, sentAt));
    }

    @PostMapping("/category/created")
    public ResponseEntity<Notifications> createCategoryCreatedNotification(
            @RequestParam Long userId,
            @RequestParam String categoryName,
            @RequestParam Date sentAt) {
        return ResponseEntity.ok(notificationService.createCategoryCreatedNotification(
                userId, categoryName, sentAt));
    }

    @PostMapping("/category/updated")
    public ResponseEntity<Notifications> createCategoryUpdatedNotification(
            @RequestParam Long userId,
            @RequestParam String oldName,
            @RequestParam String newName,
            @RequestParam Date sentAt) {
        return ResponseEntity.ok(notificationService.createCategoryUpdatedNotification(
                userId, oldName, newName, sentAt));
    }

    //    @GetMapping("/user/{userId}/unread")
//    public ResponseEntity<List<Notifications>> getUnreadNotifications(@PathVariable Long userId) {
//        return ResponseEntity.ok(notificationService.getUnreadNotifications(userId));
//    }
//
//    @GetMapping("/user/{userId}/unread/count")
//    public ResponseEntity<Long> getUnreadCount(@PathVariable Long userId) {
//        return ResponseEntity.ok(notificationService.getUnreadCount(userId));
//    }
//
//    @PutMapping("/{notificationId}/read")
//    public ResponseEntity<Void> markAsRead(@PathVariable Long notificationId) {
//        notificationService.markAsRead(notificationId);
//        return ResponseEntity.ok().build();
//    }
//    @PutMapping("/user/{userId}/read-all")
//    public ResponseEntity<Void> markAllAsRead(@PathVariable Long userId) {
//        notificationService.markAllAsRead(userId);
//        return ResponseEntity.ok().build();
//    }
}