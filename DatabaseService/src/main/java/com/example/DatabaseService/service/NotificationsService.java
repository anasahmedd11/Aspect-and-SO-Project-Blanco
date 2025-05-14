package com.example.DatabaseService.service;

import com.example.DatabaseService.DTO.CreateNotificationDTO;
import com.example.DatabaseService.DTO.UpdateNotificationDTO;
import com.example.DatabaseService.entity.Notifications;
import com.example.DatabaseService.entity.Users;
import com.example.DatabaseService.repository.NotificationsRepository;
import com.example.DatabaseService.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationsService {

    @Autowired
    private final NotificationsRepository notificationsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    public NotificationsService(NotificationsRepository notificationsRepository, NotificationsRepository notificationsRepository1) {
        this.notificationsRepository = notificationsRepository1;
    }

    public List<Notifications> getAllNotifications() {
        return notificationsRepository.findAll();
    }

    public Notifications getNotificationById(Long id) {
        return notificationsRepository.findById(id).orElse(null);
    }

    public void addNotification(CreateNotificationDTO notificationsDTO) {

        Long userId = notificationsDTO.getUserId();
        System.out.println(userId);
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Notifications notifications = new Notifications(user,
                notificationsDTO.getMsg(),
                notificationsDTO.getType(),
                notificationsDTO.getStatus(),
                notificationsDTO.getSent_At());


        notificationsRepository.save(notifications);
    }

    public Notifications updateNotification(UpdateNotificationDTO notificationsDTO, Long id) {
        Notifications updateNotifications = notificationsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found with id: " + id));

        if (updateNotifications != null) {
            updateNotifications.setMsg(notificationsDTO.getMsg());
            updateNotifications.setStatus(notificationsDTO.getStatus());
            updateNotifications.setType(notificationsDTO.getType());
            updateNotifications.setSent_At(notificationsDTO.getSent_At());
            notificationsRepository.save(updateNotifications);
            return updateNotifications;
        }
        return null;
    }

    public boolean deleteNotification(Long id) {
        if(notificationsRepository.findById(id).isPresent()){
            notificationsRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
