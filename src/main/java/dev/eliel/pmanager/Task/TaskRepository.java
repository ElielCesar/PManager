package dev.eliel.pmanager.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, String> {

    // filtrar tarefa por status
    List<TaskModel> findByStatus(TaskStatus status);

    // filtrar por title parcial case insensitive
    List<TaskModel> findByTitleContainingIgnoreCase(String title);

    // filtrar por decription parcial case insensitive
    List<TaskModel> findByDescriptionContainingIgnoreCase(String description);

}
