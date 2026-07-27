package homework_mock.task_oop.playlist_11service;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private final String name;

    public String getName() {
        return name;
    }

    private final List<Song> songList = new ArrayList<>();

    public Playlist(String name) {
        this.name = name;
    }

    // добавить песню в плейлист
    public void addSong(Song song) {
        if(song == null) {
            return;
        }
        this.songList.add(song);
    }

    // удалить песню из плейлиста
    public void removeSong(Song song) {
        if (song == null) {
            return;
        }
        this.songList.remove(song);
    }

    // посчитать общую длительность плейлиста
    public int durationPlaylist() {
        int duration = 0;
        for (Song song : songList) {
            duration += song.getSec();
        }
        return duration;
    }

    // показать все песни в плейлисте
    public void printPlaylist() {
        System.out.println("В плейлисте \"" + name + "\" есть песни:");
        for (Song s : songList) {
            System.out.println(s);
        }
        System.out.println("Общая длительность:\n" + durationPlaylist() + " секунд");
    }

}
