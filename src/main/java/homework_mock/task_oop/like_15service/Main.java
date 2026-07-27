package homework_mock.task_oop.like_15service;

/*
"В системе есть посты.

У каждого поста есть: Post

id
текст
список пользователей, которые поставили лайк

Нужно реализовать систему, которая может:
поставить лайк посту
убрать лайк
посчитать количество лайков
проверить, поставил ли пользователь лайк

Правила:
один пользователь может поставить только один лайк
повторный лайк не должен увеличивать количество лайков
пользователь может убрать свой лайк

Пример:
Пользователь Alex ставит лайк посту.
Пользователь Maria ставит лайк посту.

Количество лайков:
2

Alex убирает лайк.
Количество лайков:
1"
 */

public class Main {
    public static void main(String[] args) {
        Post post = new Post("123", "Мок собес");
        post.addLike("Alex");
        post.addLike("Maria");
        post.countLike();
        post.removeLike("Alex");
        post.countLike();
        post.checkLikeUser("Maria");
        post.checkLikeUser("NIkita");
    }
}
