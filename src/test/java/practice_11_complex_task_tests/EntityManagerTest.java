package practice_11_complex_task_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import practice_11_complex_tasks.entity_manager.EntityManager;
import practice_11_complex_tasks.entity_manager.Student;

import java.util.List;

public class EntityManagerTest {
    /**
     * Позитивные кейсы:
     * <p>
     * Добавление одного элемента в пустую коллекцию 0 -> 1
     * <p>
     * Добавление одного элемента в коллекцию с одним элементом 1 -> 2
     * <p>
     * Удаление одного элемента из коллекции с элементами(1 шт. в коллекции) 2 -> 1
     * <p>
     * Получение элементов из пустой коллекции 0 -> 0
     * <p>
     * Получение элементов из коллекции (2 шт.) 2 -> 2
     * <p>
     * Негативные кейсы:
     * <p>
     * Удаление одного элемента из пустой коллекции 0 -> ошибка по индексу
     *
     */

    @Test
    public synchronized void addASingleElementToAnEmptyCollection() {

        EntityManager<Student> manager = new EntityManager();

        Student entity = new Student("Nikita", 34, true);

        manager.add(entity);

        List<Student> students = manager.getAll();
        Assertions.assertEquals(1, students.size());
        Assertions.assertEquals("Nikita", students.get(0).getName());
    }

    @Test
    public synchronized void addASingleElementToACollectionWithOneElement() {

        EntityManager<Student> manager = new EntityManager();

        Student entity = new Student("Nikita", 34, true);

        manager.add(entity);

        Student entity2 = new Student("Petr", 30, false);

        manager.add(entity2);

        List<Student> students = manager.getAll();
        Assertions.assertEquals(2, students.size());
        Assertions.assertEquals("Nikita", students.get(0).getName());
        Assertions.assertEquals("Petr", students.get(1).getName());
    }

}