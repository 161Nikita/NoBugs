package homework_mock.task_oop.playlist_11service;

/*
"В системе есть плейлист.

У плейлиста есть: Playlist
название
список песен

У каждой песни есть: Song
название
исполнитель
длительность в секундах

Нужно реализовать систему, которая может:
добавить песню в плейлист
удалить песню из плейлиста
посчитать общую длительность плейлиста
показать все песни в плейлисте

Правила:
общая длительность плейлиста считается как сумма длительностей всех песен
если песня удалена, она больше не участвует в расчете

Пример:
В плейлисте есть песни:
Believer — Imagine Dragons — 204
Numb — Linkin Park — 185
Halo — Beyonce — 261
Общая длительность:
650 секунд"
 */


public class Main {
    public static void main(String[] args) {
        Song Believer = new Song("Believer", "Imagine Dragons", 204);
        Song Numb = new Song("Numb", "Linkin Park", 185);
        Song Halo = new Song("Halo", "Beyonce", 261);

        Playlist playlist = new Playlist("Мой любимый плейлист");
        playlist.addSong(Believer);
        playlist.addSong(Numb);
        playlist.addSong(Halo);
        playlist.printPlaylist();
        playlist.removeSong(Halo);
        playlist.printPlaylist();
    }
}
