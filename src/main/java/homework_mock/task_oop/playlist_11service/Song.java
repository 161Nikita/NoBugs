package homework_mock.task_oop.playlist_11service;

import java.util.Objects;

public class Song {

    private String title;
    private String singer;
    private int sec;

    public Song(String title, String singer, int sec) {
        this.title = title;
        this.singer = singer;
        this.sec = sec;
    }

    public String getTitle() {
        return title;
    }

    public String getSinger() {
        return singer;
    }

    public int getSec() {
        return sec;
    }

    @Override
    public String toString() {
        return title + " — " + singer + " — "+ sec;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return sec == song.sec && Objects.equals(title, song.title) && Objects.equals(singer, song.singer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, singer, sec);
    }
}