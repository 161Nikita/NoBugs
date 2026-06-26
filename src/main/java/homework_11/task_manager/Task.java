package homework_11.task_manager;

import java.time.LocalDateTime;

public class Task <T>{

    private final T id;
    private final STATUS status;
    private final PRIORITY priority;
    private final LocalDateTime date;

    public enum STATUS {NEW, IN_PROGRESS, DONE};
    public enum PRIORITY {LOW, MEDIUM, HIGH};

    public Task(T id, STATUS status, PRIORITY priority, LocalDateTime date) {
        this.id = id;
        this.status = status;
        this.priority = priority;
        this.date = date;
    }

    public T getId() {
        return id;
    }

    public STATUS getStatus() {
        return status;
    }

    public PRIORITY getPriority() {
        return priority;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
