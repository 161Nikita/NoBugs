package homework_mock.task_oop.like_15service;

import java.util.HashSet;
import java.util.Set;

public class Post {

    private final String id;
    private final String text;
    private final Set<String> likedUsers = new HashSet<>();

    public Post(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    // поставить лайк посту
    public void addLike(String userName) {
        if (userName == null) return;
        this.likedUsers.add(userName);
        System.out.println("Пользователь " + userName + " ставит лайк посту.");
    }

    // убрать лайк
    public void removeLike(String userName) {
        if (userName == null) return;
        this.likedUsers.remove(userName);
        System.out.println("\n" + userName + " убирает лайк.");
    }

    // посчитать количество лайков
    public void countLike() {
        System.out.println("\nКоличество лайков:\n" + likedUsers.size());
    }

    // проверить, поставил ли пользователь лайк
    public void checkLikeUser(String userName) {
        if (userName == null) return;
        if (likedUsers.contains(userName)) {
            System.out.println("Да, пользователь " + userName + " ставил(а) лайк");
        } else { System.out.println("Нет, пользователь " + userName + " не ставил(а) лайк");}
    }
}
