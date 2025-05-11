package com.example.DatabaseService.controller;

import com.example.DatabaseService.DTO.CreateNotificationDTO;
import com.example.DatabaseService.DTO.UpdateNotificationDTO;
import com.example.DatabaseService.entity.Notifications;
import com.example.DatabaseService.service.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notifications")
public class NotificationsController {

    private final NotificationsService notificationsService;

    @Autowired
    public NotificationsController(NotificationsService notificationsService) {
        this.notificationsService = notificationsService;
    }


    @GetMapping("get-notifications")
    public List<Notifications> getNotifications(){
        return notificationsService.getAllNotifications();
    }

    @GetMapping("get-notifications/{id}")
    public Notifications getNotifications(@PathVariable long id){
        return notificationsService.getNotificationById(id);
    }


    @PostMapping("add-notifications")
    public void addNotification(@RequestBody CreateNotificationDTO notificationsDTO){
        notificationsService.addNotification(notificationsDTO);
    }

    @PutMapping("update-notifications/{id}")
    public Notifications updateNotifications(@PathVariable long id, @RequestBody UpdateNotificationDTO notificationsDTO){
        return notificationsService.updateNotification(notificationsDTO, id);
    }

    @DeleteMapping("delete-notification/{id}")
    public boolean deleteNotification(@PathVariable long id){
        return notificationsService.deleteNotification(id);
    }





}