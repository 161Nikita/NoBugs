package practice_11_complex_tasks.task_manager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService <T> {

    private final List<Task<T>> tasks = new ArrayList<>();

    public synchronized void addTask(Task<T> task) {
        boolean exist = tasks.stream().anyMatch(t ->t.getId().equals(t.getId()));
        if (exist) {
            throw new IllegalArgumentException("Задача с таким ID уже есть");
        }
        tasks.add(task);
    }

    public synchronized boolean removeTaskById(T id) {
       return tasks.removeIf(t-> t.getId().equals(id));
    }

    public synchronized List<Task<T>> getTasksByStatus(Task.Status status) {
       return tasks.stream().filter(s -> s.getStatus() == status).collect(Collectors.toList());
    }

    public synchronized List<Task<T>> getTasksByPriority(Task.Priority priority) {
        return tasks.stream().filter(p->p.getPriority() == priority).collect(Collectors.toList());
    }

    public synchronized List<Task<T>> getTasksByDate() {
        return tasks.stream().sorted(Comparator.comparing(Task::getDate)).collect(Collectors.toList());
    }

    public List<Task<T>> getAll() {
        return List.copyOf(tasks);
    }

}
