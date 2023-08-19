package com.telegrammBot.repository;

import com.telegrammBot.entity.NotificationTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface TasksRepository extends JpaRepository<NotificationTask, Long> {
    Collection<NotificationTask> findAllByDateTime(LocalDateTime dateTime);
}