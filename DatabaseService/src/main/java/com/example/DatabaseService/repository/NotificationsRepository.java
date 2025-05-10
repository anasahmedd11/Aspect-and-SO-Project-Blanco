package com.example.DatabaseService.repository;

import com.example.DatabaseService.DTO.NotificationsDTO;
import com.example.DatabaseService.entity.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationsRepository extends JpaRepository<NotificationsDTO, Long> {}