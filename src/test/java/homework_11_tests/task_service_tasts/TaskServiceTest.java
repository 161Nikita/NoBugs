package homework_11_tests.task_service_tasts;

import homework_11.task_manager.Task;
import homework_11.task_manager.TaskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    /**
     * Позитивные кейсы:
     * <p>
     * Добавить задачу 0->1
     * Удалить задачу 1->0
     * Фильтрация по статусу ->NEW
     * Фильтрация по приоритету ->HIGH
     * Сортировка по дате ->now
     * <p>
     * Негативные кейсы:
     * Добавить с неуникальным ID -> IllegalArgumentException
     *
     */

    @Test
    @DisplayName("Добавить задачу 0->1")
    public void testAddTask() {
        TaskService<String> service = new TaskService<>();
        Task<String> task = new Task<>("1", Task.STATUS.NEW, Task.PRIORITY.HIGH, LocalDateTime.now());
        service.addTask(task);

        assertEquals(1, service.getAllTask().size());
    }

    @Test
    @DisplayName("Удалить задачу 1->0")
    public void testRemoveTask() {
        TaskService<String> service = new TaskService<>();
        Task<String> task = new Task<>("1", Task.STATUS.NEW, Task.PRIORITY.HIGH, LocalDateTime.now());
        service.removeTask("1");

        assertEquals(0, service.getAllTask().size());
    }

    @Test
    @DisplayName("Фильтрация по статусу ->NEW")
    public void testFilterByStatus() {
        TaskService<String> service = new TaskService<>();
        service.addTask(new Task<>("1", Task.STATUS.NEW, Task.PRIORITY.HIGH, LocalDateTime.now()));
        service.addTask(new Task<>("2", Task.STATUS.IN_PROGRESS, Task.PRIORITY.HIGH, LocalDateTime.now()));


        List<Task<String>> tasks = service.getTaskByStatus(Task.STATUS.NEW);

        assertEquals(1, tasks.size());
        assertEquals("1", tasks.get(0).getId());

    }

    @Test
    @DisplayName("Фильтрация по приоритету ->HIGH")
    public void testFilterByPriority() {
        TaskService<String> service = new TaskService<>();
        service.addTask(new Task<>("1", Task.STATUS.NEW, Task.PRIORITY.HIGH, LocalDateTime.now()));
        service.addTask(new Task<>("2", Task.STATUS.IN_PROGRESS, Task.PRIORITY.HIGH, LocalDateTime.now()));


        List<Task<String>> tasks = service.getTaskByPriority(Task.PRIORITY.HIGH);

        assertEquals(2, tasks.size());
        assertEquals("1", tasks.get(0).getId());
        assertEquals("2", tasks.get(1).getId());

    }

    @Test
    @DisplayName("Сортировка по дате ->now")
    public void testSortedByDate() {
        TaskService<String> service = new TaskService<>();
        service.addTask(new Task<>("1", Task.STATUS.NEW, Task.PRIORITY.HIGH, LocalDateTime.now()));
        service.addTask(new Task<>("2", Task.STATUS.IN_PROGRESS, Task.PRIORITY.HIGH, LocalDateTime.of(2020, 1, 2, 3, 4)));

        List<Task<String>> sorted = service.getTaskByDate();

        assertEquals("2", sorted.get(0).getId());
    }

    @Test
    @DisplayName("Добавить с неуникальным ID -> IllegalArgumentException")
    public void testInvalidId() {
        TaskService<String> service = new TaskService<>();
        Task<String> task = new Task<>("1", Task.STATUS.NEW, Task.PRIORITY.HIGH, LocalDateTime.now());
        service.addTask(task);

        assertThrows(IllegalArgumentException.class, () -> service.addTask(task));
    }
}
