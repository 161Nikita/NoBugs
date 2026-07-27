package homework_13.singleton_logger;

public class Logger {

    private static Logger logger;

    private Logger() {
    }

    public static Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public void info(String message) {
        System.out.println("[INFO] " + message);
    }
    public void error(String message) {
        System.out.println("[ERROR] " + message);
    }
    public void warn(String message) {
        System.out.println("[WARNING] " + message);
    }

}
