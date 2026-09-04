package com.parita.notification.repository;

import com.parita.notification.entity.NotificationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationLogRepository
        extends JpaRepository<NotificationLog, Long> {
}