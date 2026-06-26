package homework_11_tests.entity_manager_tests;

import homework_11.entity_manager.EntityManager;
import homework_11.entity_manager.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EntityManagerTest {

    /**
     * Тест кейсы
     * <p>
     * Добавление одного элемента в пустую коллекцию 0 -> 1
     * <p>
     * Добавление одного элемента в непустую коллекцию 1 -> 2
     * <p>
     * Удаление одного элемента из коллекции 2 -> 1
     * <p>
     * Фильтрация по возрасту [30:40] -> 2 элемента
     * <p>
     * Фильтрация по имени Nikita -> 2 элемента
     * <p>
     * Фильтрация по активности true-> true / false-> false
     *
     */

    @Test
    @DisplayName("Добавление одного элемента в пустую коллекцию 0 -> 1")
    public void testAddingASingleElementToAnEmptyCollection() {
        // Создаем пустую коллекцию
        EntityManager<User> manager = new EntityManager<>();
        // Создаем юзера
        User user = new User("Nikita", 34, true);
        // Добавляем юзера в пустую коллекцию 0 -> 1
        manager.add(user);
        // Создаем переменную, которая возвращает список всех элементов
        List<User> users = manager.getAll();
        // Проверяем размер коллекции 1 -> 1
        assertEquals(1, users.size());
        assertEquals("Nikita", users.get(0).getName());
    }

    @Test
    @DisplayName("Добавление элемента в непустую коллекцию 1 -> 2")
    public void testAddingASingleElementToAOnEmptyCollection() {
        // Создаем пустую коллекцию
        EntityManager<User> manager = new EntityManager<>();
        // Создаем юзера
        User user1 = new User("Nikita", 34, true);
        // Добавляем юзера в пустую коллекцию 0 -> 1
        manager.add(user1);
        // Создаем юзера
        User user2 = new User("Petr", 24, false);
        // Добавляем юзера в непустую коллекцию 1 -> 2
        manager.add(user2);
        // Создаем переменную, которая возвращает список всех элементов
        List<User> users = manager.getAll();
        // Проверяем размер коллекции 2 -> 2
        assertEquals(2, users.size());
        assertEquals("Nikita", users.get(0).getName());
        assertEquals("Petr", users.get(1).getName());
    }

    @Test
    @DisplayName("Удаление одного элемента из коллекции 2 -> 1")
    public void testRemovingOneItemFromCollection() {
        // Создаем пустую коллекцию
        EntityManager<User> manager = new EntityManager<>();
        // Создаем юзера
        User user1 = new User("Nikita", 34, true);
        // Добавляем юзера в пустую коллекцию 0 -> 1
        manager.add(user1);
        // Создаем юзера
        User user2 = new User("Petr", 24, false);
        // Добавляем юзера в непустую коллекцию 1 -> 2
        manager.add(user2);
        // Создаем переменную, которая возвращает список всех элементов
        List<User> users = manager.getAll();
        // Удаляем юзера из коллекции 2 -> 1
        assertEquals(2, users.size());
        assertEquals("Nikita", users.get(0).getName());
        assertEquals("Petr", users.get(1).getName());
        // Удаляем юзера из коллекции 2 -> 1
        assertTrue(manager.remove(user2));
        assertFalse(manager.remove(user2));
        assertEquals(1, manager.getAll().size());
        assertEquals("Nikita", manager.getAll().get(0).getName());

    }

    @Test
    @DisplayName("Фильтрация по возрасту [30:40] -> 2 элемента")
    public void testFilterByAge() {
        // Создаем пустую коллекцию
        EntityManager<User> manager = new EntityManager<>();
        // Создаем 3-х юзеров и добавляем в коллекцию
        manager.add(new User("Nikita", 34, true));
        manager.add(new User("Petr", 40, false));
        manager.add(new User("Ivan", 41, true));
        // Создаем переменную, которая возвращает список всех элементов
        List<User> users = manager.filterByAge(30, 40);
        // Проверяем размер коллекции 2 -> 2
        assertEquals(2, users.size());
        // Проверяем фильтрацию по возрасту [30:40] -> 2 элемента
        assertEquals(34, users.get(0).getAge());
        assertEquals(40, users.get(1).getAge());

    }

    @Test
    @DisplayName("Фильтрация по имени Nikita -> 2 элемента")
    public void testFilterByName() {
        // Создаем пустую коллекцию
        EntityManager<User> manager = new EntityManager<>();
        // Создаем 3-х юзеров и добавляем в коллекцию
        manager.add(new User("Nikita", 34, true));
        manager.add(new User("Egor", 40, false));
        manager.add(new User("Nikita", 41, true));
        // Создаем переменную, которая возвращает список всех элементов
        List<User> users = manager.filterByName("Nikita");
        // Проверяем размер коллекции 2 -> 2
        assertEquals(2, users.size());
        // Проверяем фильтрацию по имени Nikita -> 2 элемента
        assertEquals(34, users.get(0).getAge());
        assertEquals(41, users.get(1).getAge());
    }

    @Test
    @DisplayName("Фильтрация по активности")
    public void testFilterByIsActive() {
        // Создаем пустую коллекцию
        EntityManager<User> manager = new EntityManager<>();
        // Создаем 3-х юзеров и добавляем в коллекцию
        manager.add(new User("Nikita", 34, true));
        manager.add(new User("Egor", 40, false));
        manager.add(new User("Ivan", 55, false));
        // Создаем переменную, которая фильтрует список по активности
        List<User> users = manager.filterByIsActive(false);
        // Проверяем размер коллекции 2 -> 2
        assertEquals(2, users.size());
        // Проверяем фильтрацию по активности -> 2 элемента
        assertEquals("Egor", users.get(0).getName());
        assertEquals("Ivan", users.get(1).getName());
    }

}
