package com.emma.Task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.emma.Task.domain.entities.Task;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
//    Querring methods for task
    List<Task> findByTaskListId(UUID taskListId);
    Optional<Task> findByTaskListIdAndId(UUID taskListID, UUID id);
}
