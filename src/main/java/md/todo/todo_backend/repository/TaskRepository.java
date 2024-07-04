package md.todo.todo_backend.repository;

import md.todo.todo_backend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // if we inherit JpaRepository interface then we don't need @Repository annotation
public interface TaskRepository extends JpaRepository<Task, Long> {
}
