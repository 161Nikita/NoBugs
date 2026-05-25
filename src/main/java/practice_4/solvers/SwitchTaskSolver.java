package practice_4.solvers;

import practice_4.Season;

public class SwitchTaskSolver {
    public static void main(String[] args) {
        /*// проверка метода возвращающего день недели по числу
        System.out.println(dayOfWeek(2));
        System.out.println(dayOfWeek(10));*/

        // проверка метода по описанию сезона
        System.out.println(describeSeason(Season.AUTUMN));
        System.out.println(describeSeason(Season.SUMMER));
    }

    /**
     * Метод для вывода название дня недели
     */

    public static String dayOfWeek(int day) {
        String dayOfWeek = "";
        switch (day) {
            case 1:
                dayOfWeek = "Понедельник";
                break;
            case 2:
                dayOfWeek = "Вторник";
                break;
            case 3:
                dayOfWeek = "Среда";
                break;
            case 4:
                dayOfWeek = "Четверг";
                break;
            case 5:
                dayOfWeek = "Пятница";
                break;
            case 6:
                dayOfWeek = "Суббота";
                break;
            case 7:
                dayOfWeek = "Воскресенье";
                break;
            default:
                dayOfWeek = "Такого дня в неделе нет";
                break;
        }
        return dayOfWeek;
    }

    /**
     * Работа с сезонами
     */

    public static String describeSeason(Season season) {
        String descriotion = "";
        switch (season) {
            case WINTER -> descriotion = "Зима - холодно и снежной";
            case SUMMER -> descriotion = "Лето - жарко";
            case SPRING -> descriotion = "Весна - все цветет";
            case AUTUMN -> descriotion = "Осень - это плачущее небо под ногами";
        }
        return descriotion;
    }
}
