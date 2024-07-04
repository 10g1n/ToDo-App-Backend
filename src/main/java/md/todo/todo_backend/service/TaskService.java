package md.todo.todo_backend.service;

import md.todo.todo_backend.model.Task;
import md.todo.todo_backend.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> getById(Long id) {
        return taskRepository.findById(id);
    }

    public Task add(Task task) {
        task.setId(null);
        return taskRepository.save(task);
    }

    public boolean update(Long id, Task task) {
        Optional <Task> oldTask = taskRepository.findById(id);
        if (oldTask.isPresent()){
            oldTask.get().setName(task.getName());
            oldTask.get().setDescription(task.getDescription());
            oldTask.get().setCompleted(task.isCompleted());
            taskRepository.save(oldTask.get());

            return true;
        }
        return false;
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
