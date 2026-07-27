package homework_13.singleton_configuration;

public class Main {
    public static void main(String[] args) {

        ConfigurationManager config1 = ConfigurationManager.getInstance();

        System.out.println("---Исходная конфигурация---");
        System.out.println(config1.getUserName());
        System.out.println(config1.getPassword());
        System.out.println(config1.getUrl());

        config1.setUserName("Kirill");
        config1.setPassword("321");

        ConfigurationManager config2 = ConfigurationManager.getInstance();

        System.out.println("---Измененная конфигурация---");
        System.out.println(config2.getUserName());
        System.out.println(config2.getPassword());
        System.out.println(config2.getUrl());

        System.out.println("Ссылки на объект одинаковые? " + (config1 == config2));
    }
}
