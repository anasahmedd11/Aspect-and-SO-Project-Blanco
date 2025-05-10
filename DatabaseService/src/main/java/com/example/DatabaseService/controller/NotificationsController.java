package com.example.DatabaseService.controller;

import com.example.DatabaseService.DTO.NotificationsDTO;
import com.example.DatabaseService.entity.Notifications;
import com.example.DatabaseService.repository.NotificationsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notifications")
public class NotificationsController {

    private NotificationsRepository notificationsRepository;

    @GetMapping("get-notifications")
    public List<NotificationsDTO> getNotifications(){
        return notificationsRepository.findAll();
    }

    @GetMapping("get-notifications/{id}")
    public NotificationsDTO getNotifications(@PathVariable int id){
        return notificationsRepository.findById(id).orElse(null);
    }


    @PostMapping("add-notifications")
    public void addNotification(@RequestBody NotificationsDTO notifications){
        notificationsRepository.save(notifications);
    }

    @PutMapping("update-notifications/{id}")
    public NotificationsDTO updateNotifications(@PathVariable int id, @RequestBody NotificationsDTO notifications){

        NotificationsDTO updateNotifications = notificationsRepository.findById(id).orElse(null);
        if(updateNotifications != null){
            updateNotifications.setMsg(notifications.getMsg());
            updateNotifications.setStatus(notifications.getStatus());
            updateNotifications.setType(notifications.getType());
            updateNotifications.setSent_At(notifications.getSent_At());
            notificationsRepository.save(updateNotifications);
            return updateNotifications;
        }
        return null;
    }

    @DeleteMapping("delete-notification/{id}")
    public boolean deleteNotification(@PathVariable int id, @RequestBody NotificationsDTO notifications){
        if(notificationsRepository.findById(id).isPresent()){
            notificationsRepository.delete(notifications);
            return true;
        }
        return false;
    }

    @DeleteMapping("delete-notifications")
    public boolean deleteNotifications(@RequestBody NotificationsDTO notifications){
        notificationsRepository.delete(notifications);
        return true;
    }



}