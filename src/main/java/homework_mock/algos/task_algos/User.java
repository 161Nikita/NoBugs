package homework_mock.algos.task_algos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Сравнение двух объектов по equals. Есть пользователь у которого есть, имя фамилия, список мест работ. Два одинаковых
 * пользователя с одним и тем же списком работ должны возвращать true.
 */

public class User {

  private String name;
  private int age;
  private List<String> job;

  public User(String name, int age, List<String> job) {
      this.name = name;
      this.age = age;
      this.job = job;
  }

  public String getName(){
      return name;
  }
    public int getAge(){
        return age;
    }
    public List<String> getJob(){
        return job;
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
      return Objects.hash(name,age,job);
   }

    public static void main(String[] args) {

      User user1 = new User("Nikita", 34, new ArrayList<>(List.of("google", "yandex")));
      User user2 = new User("Nikita", 34, new ArrayList<>(List.of("google", "yandex", "neva")));
      User user3 = new User("Nikita", 34,new ArrayList<>(List.of("google", "yandex")));

        System.out.println(user1.equals(user2));
        System.out.println(user1.equals(user3));
    }
}