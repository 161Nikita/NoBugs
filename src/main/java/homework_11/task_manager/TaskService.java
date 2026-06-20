package homework_11.task_manager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService<T> {


    private final List<Task<T>> tasks = new ArrayList<>();

    public synchronized void addTask(Task<T> task) {
        boolean exist = tasks.stream().anyMatch(t -> t.getId().equals(task.getId()));
        if (exist) {
            throw new IllegalArgumentException("Такой ID уже существует");
        }
        tasks.add(task);
    }

    public synchronized boolean removeTask(T id) {
        return tasks.removeIf(r -> r.getId().equals(id));
    }

    public synchronized List<Task<T>> getTaskByStatus(Task.STATUS status) {
        return tasks.stream().filter(t -> t.getStatus() == status).collect(Collectors.toList());
    }

    public synchronized List<Task<T>> getTaskByPriority(Task.PRIORITY priority) {
        return tasks.stream().filter(t -> t.getPriority() == priority).collect(Collectors.toList());
    }

    public synchronized List<Task<T>> getTaskByDate() {
        return tasks.stream().sorted(Comparator.comparing(Task::getDate)).collect(Collectors.toList());
    }

    public synchronized List<Task<T>> getAllTask() {
        return List.copyOf(tasks);
    }

}