package practice_5.user_monitor;

import java.util.HashSet;

public class UserMonitor {
    // хранение уникальных сессий
    private HashSet<String> sessions;

    public UserMonitor() {
        this.sessions = new HashSet<>();
    }

    public void addNewSession(String session) {
        sessions.add(session);
    }

    // доступ к сессии
    public HashSet<String> getSessions() {
        return this.sessions;
    }

    // метод вывода принт

    public void printSession() {
        System.out.println("Все уникальные сессии");

        sessions.forEach(System.out::println);
    }

}
