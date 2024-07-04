package md.todo.todo_backend.controller;

import md.todo.todo_backend.model.Task;
import md.todo.todo_backend.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks() {
        return taskService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> taskOptional = taskService.getById(id);

        if (taskOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(taskOptional.get());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task postTask(@RequestBody Task task) {
        return taskService.add(task);
    }

    @PutMapping("{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Optional<Task> taskOptional = taskService.getById(id);

        if (taskOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            taskService.update(id, task);

            return ResponseEntity.ok().body(taskOptional.get());
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaskById(@PathVariable Long id) {
        taskService.delete(id);
    }
}
