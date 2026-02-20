package dev.eliel.pmanager.Task;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO createTask(@Valid @RequestBody TaskDTO task){
        return taskService.createTask(task);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO loadTask(@PathVariable String id){
        return taskService.loadTask(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Page<TaskDTO> loadAllTasks(@PageableDefault(size = 3) Pageable pageable){
        return taskService.loadAllTasks(pageable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable String id){
        taskService.deleteTask(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO updateTask(@PathVariable String id, @RequestBody TaskDTO task){
        return taskService.updateTask(id, task);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDTO> filterTask(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description
    ){
        return taskService.taskFilter(status, title, description);
    }
}
