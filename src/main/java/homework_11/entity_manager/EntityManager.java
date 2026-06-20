package homework_11.entity_manager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class EntityManager<T extends Entity> {

    // Создаем потокобезопасную коллекцию, она пустая
    CopyOnWriteArrayList<T> entities = new CopyOnWriteArrayList<>();

    // Создаем потокобезопасный метод добавления в коллекцию
    public synchronized void add(T entity) {
        entities.add(entity);
    }

    // Создаем потокобезопасный метод удаления из коллекции
    public synchronized boolean remove(T entity) {
        return entities.remove(entity);
    }

    // Создаем потокобезопасный метод получения коллекции (списка)
    public synchronized List<T> getAll() {
        return List.copyOf(entities);
    }

    // Фильтрация по возрасту
    public List<T> filterByAge(int min, int max) {
        return entities.stream().filter(entity -> entity.getAge() >= min && entity.getAge() <= max)
                .collect(Collectors.toList());
    }

    // Фильтрация по имени
    public List<T> filterByName(String name) {
        return entities.stream().filter(entity -> entity.getName().equals(name))
                .collect(Collectors.toList());
    }

    // Фильтрация по активности
    public List<T> filterByIsActive(boolean isActive) {
        return entities.stream().filter(entity -> entity.isActive() == isActive)
                .collect(Collectors.toList());
    }
}
