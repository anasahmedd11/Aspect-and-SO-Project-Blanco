package com.example.DatabaseService.controller;

import com.example.DatabaseService.entity.Notifications;
import com.example.DatabaseService.repository.NotificationsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/notifications")
public class NotificationsController {

    private NotificationsRepository notificationsRepository;

    @GetMapping
    public List<Notifications> getNotifications(){
        return notificationsRepository.findAll();
    }




}