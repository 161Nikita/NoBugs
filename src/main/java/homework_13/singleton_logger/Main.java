package homework_13.singleton_logger;

public class Main {
    public static void main(String[] args) {

        Logger logger1 = Logger.getInstance();
        logger1.info("Успешное подключение к базе данных");
        logger1.warn("Попытка работы с устаревшими методами");
        logger1.error("Поймали исключение");

        Logger logger2 = Logger.getInstance();
        logger2.error("Поймали исключение при делении на 0");

        System.out.println("Ссылки одинаковые? " + (logger1 == logger2));
    }
}
