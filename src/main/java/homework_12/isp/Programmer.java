package homework_12.isp;

/*
Нарушение ISP (Interface Segregation Principle) – слишком большой интерфейс
Задача: Разделите интерфейс на отдельные специализированные интерфейсы.
 */

public class Programmer implements Workable {
    @Override
    public void work() {
        System.out.println("Программист пишет код");
    }
}