package practice_11_complex_tasks.entity_manager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


public class EntityManager<T extends Entity> {

    private final CopyOnWriteArrayList<T> entities = new CopyOnWriteArrayList<>();


    // Добавление элементов
    public void add(T entity) {
        entities.add(entity);
    }
    // Удаление элементов
    public boolean remove(T entity) {
        return entities.remove(entity);
    }
    // Получение элементов
    public List<T> getAll() {
        return List.copyOf(entities);
    }

public List<T> filterByAge(int min, int max) {
        return entities.stream().filter(entity -> entity.getAge() >= min && entity.getAge() <= max)
                .collect(Collectors.toList());
}

public List<T> filterByName(String name) {
       return entities.stream().filter(entity -> entity.getName().equals(name))
               .collect(Collectors.toList());
}

    public List<T> filterByIsActive(boolean isActive) {
        return entities.stream().filter(entity -> entity.isActive())
                .collect(Collectors.toList());
    }

}
