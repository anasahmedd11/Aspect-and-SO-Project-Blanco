package com.example.DatabaseService.controller;

import com.example.DatabaseService.DTO.CreateNotificationDTO;
import com.example.DatabaseService.DTO.UpdateNotificationDTO;
import com.example.DatabaseService.entity.Notifications;
import com.example.DatabaseService.service.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/db-service/notifications")
public class NotificationsController {

    private final NotificationsService notificationsService;

    @Autowired
    public NotificationsController(NotificationsService notificationsService) {
        this.notificationsService = notificationsService;
    }


    @GetMapping
    public List<Notifications> getNotifications() {
        return notificationsService.getAllNotifications();
    }

    @GetMapping("/{id}")
    public Notifications getNotifications(@PathVariable Long id) {
        return notificationsService.getNotificationById(id);
    }

    @PostMapping
    public void addNotification(@RequestBody CreateNotificationDTO notificationsDTO) {
        notificationsService.addNotification(notificationsDTO);
    }

    @PutMapping("/{id}")
    public Notifications updateNotifications(@PathVariable Long id, @RequestBody UpdateNotificationDTO notificationsDTO) {
        return notificationsService.updateNotification(notificationsDTO, id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteNotification(@PathVariable Long id) {
        return notificationsService.deleteNotification(id);
    }


}