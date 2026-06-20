package practice_11_complex_tasks.task_manager;

import java.time.LocalDateTime;

public class Task<T> {

    private final T id;
    private final Status status;
    private final Priority priority;
    private final LocalDateTime date;

    public enum Status {NEW, IN_PROGRESS, DONE}
    public enum Priority {LOW, MEDIUM, HIGH}

    public Task(T id, Status status, Priority priority, LocalDateTime date) {
        this.id = id;
        this.status = status;
        this.priority = priority;
        this.date = date;
    }

    public T getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
