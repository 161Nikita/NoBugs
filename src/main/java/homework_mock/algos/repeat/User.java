package homework_mock.algos.repeat;

import java.util.*;

/**
 * Сравнение двух объектов по equals. Есть пользователь у которого есть, имя возраст, список мест работ. Два одинаковых
 * пользователя с одним и тем же списком работ должны возвращать true.
 */

public class User {

    private String name;
    private int age;
    private Set<String> job;

    public User(String name, int age, Set<String> job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User u = (User) o;
        return age == u.age && Objects.equals(name, u.name) && Objects.equals(job, u.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, job);
    }

    public static void main(String[] args) {
        User user1 = new User("Nikita", 34, new LinkedHashSet<>(List.of("Google", "Yandex", "Apple")));
        User user2 = new User("Nikita", 34, new LinkedHashSet<>(List.of("Google", "Apple")));
        User user3 = new User("Nikita", 34, new LinkedHashSet<>(List.of("Google", "Apple", "Yandex")));

        System.out.println(user1.equals(user3));
        System.out.println(user1.equals(user2));
    }
}