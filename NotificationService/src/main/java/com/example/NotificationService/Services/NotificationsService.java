package com.example.NotificationService.Services;

import com.example.NotificationService.Entity.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class NotificationsService {
    private final RestTemplate restTemplate;
    private final String databaseServiceUrl = "http://localhost:8080/db-service/notifications";

    @Autowired
    public NotificationsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Notifications> getUserNotifications(Long userId) {
        Notifications[] notifications = restTemplate.getForObject(
                databaseServiceUrl + userId,
                Notifications[].class
        );
        return Arrays.asList(notifications);
    }

    public Notifications createNotification(Long userId, String msg, String type, String status, Date sentAt) {
        Notifications notifications = new Notifications(userId, msg, type, status, sentAt);
        return restTemplate.postForObject(
                databaseServiceUrl,
                notifications,
                Notifications.class
        );
    }

    // Budget-related:
    public Notifications createBudgetLimitNotification(Long userId, String categoryName, double currentAmount, double limitAmount, Date sentAt) {
        String msg = String.format("Budget limit exceeded for category '%s'. Current amount: %f, Limit: %f",
                categoryName, currentAmount, limitAmount);
        return createNotification(userId, msg, "BUDGET_LIMIT", "UNREAD", sentAt);
    }

    public Notifications createBudgetExpirationNotification(Long userId, String categoryName, Date expiresAt) {
        String msg = String.format("Budget for category '%s' will expire on %s", categoryName, expiresAt);
        return createNotification(userId, msg, "BUDGET_EXPIRATION", "UNREAD", expiresAt);
    }

    public Notifications createBudgetCreatedNotification(Long userId, String categoryName, double limitAmount, Date sentAt) {
        String msg = String.format("New budget created for category '%s' with limit: %f", categoryName, limitAmount);
        return createNotification(userId, msg, "BUDGET_CREATED", "UNREAD", sentAt);
    }

    // Expense-related:
    public Notifications createExpenseNotification(Long userId, String categoryName, double amount, String notes, Date sentAt) {
        String msg = String.format("New expense of %.2f has been registered in category '%s'. Notes: %s",
                amount, categoryName, notes);
        return createNotification(userId, msg, "EXPENSE", "UNREAD", sentAt);
    }

    public Notifications createExpenseUpdatedNotification(Long userId, String categoryName, double oldAmount, double newAmount, Date sentAt) {
        String msg = String.format("Expense in category '%s' has been updated from %.2f to %.2f",
                categoryName, oldAmount, newAmount);
        return createNotification(userId, msg, "EXPENSE_UPDATED", "UNREAD", sentAt);
    }

    // Transaction-related:
    public Notifications createTransactionSentNotification(Long userId, Long receiverId, double amount, Date sentAt) {
        String msg = String.format("You sent %.2f to user ID: %d", amount, receiverId);
        return createNotification(userId, msg, "TRANSACTION_SENT", "UNREAD", sentAt);
    }

    public Notifications createTransactionReceivedNotification(Long userId, Long senderId, double amount, Date sentAt) {
        String msg = String.format("You received %.2f from user ID: %d", amount, senderId);
        return createNotification(userId, msg, "TRANSACTION_RECEIVED", "UNREAD", sentAt);
    }

    // Category-related
    public Notifications createCategoryCreatedNotification(Long userId, String categoryName, Date sentAt) {
        String msg = String.format("New category '%s' has been created", categoryName);
        return createNotification(userId, msg, "CATEGORY_CREATED", "UNREAD", sentAt);
    }

    public Notifications createCategoryUpdatedNotification(Long userId, String oldName, String newName, Date sentAt) {
        String msg = String.format("Category name has been updated from '%s' to '%s'", oldName, newName);
        return createNotification(userId, msg, "CATEGORY_UPDATED", "UNREAD", sentAt);
    }

//    public List<Notification> getUnreadNotifications(Long userId) {
//        Notification[] notifications = restTemplate.getForObject(
//                databaseServiceUrl + "/user/" + userId + "/status/UNREAD",
//                Notification[].class
//        );
//        return Arrays.asList(notifications);
//    }
//
//    public void markAsRead(Long notificationId) {
//        restTemplate.put(
//                databaseServiceUrl + "/" + notificationId + "/status/READ",
//                null
//        );
//    }
//
//    public void markAllAsRead(Long userId) {
//        restTemplate.put(
//                databaseServiceUrl + "/user/" + userId + "/status/READ",
//                null
//        );
//    }
//    public long getUnreadCount(Long userId) {
//        return restTemplate.getForObject(
//                databaseServiceUrl + "/user/" + userId + "/status/UNREAD/count",
//                Long.class
//        );
//    }


}
