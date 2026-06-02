package practice_5.user_monitor;

public class Main {
    public static void main(String[] args) {
        UserMonitor userMonitor = new UserMonitor();

        userMonitor.addNewSession("177");
        userMonitor.addNewSession("177");
        userMonitor.addNewSession("177");
        userMonitor.addNewSession("177");
        userMonitor.addNewSession("177");
        userMonitor.addNewSession("177");
        userMonitor.addNewSession("153");
        userMonitor.addNewSession("153");
        userMonitor.addNewSession("153");

        userMonitor.printSession();
    }
}
