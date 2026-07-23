package homework_mock.task_oop.report_5service;

import java.util.ArrayList;
import java.util.List;

/**
 * "Система работает с разными типами отчетов:
 *
 * * PDF отчет
 * * Excel отчет
 * * HTML отчет
 *
 * У каждого отчета есть **название**.
 *
 * Все отчеты умеют:
 *
 * * генерировать отчет
 *
 * Но каждый делает это по-своему:
 *
 * * PDF отчет формируется в pdf-файл
 * * Excel отчет формируется в таблицу
 * * HTML отчет формируется как веб-страница
 *
 * Нужно реализовать систему, которая может **сгенерировать все отчеты**.
 *
 * Требование:
 *
 * Метод, который запускает генерацию, должен работать **со списком отчетов**, не зная их конкретный тип.
 * Каждый тип отчета должен **сам реализовывать**, как он генерируется.
 *
 * Метод должен вывести, например:
 *
 * PDF отчет ""Продажи за март"" сгенерирован
 * Excel отчет ""Зарплаты"" сгенерирован
 * HTML отчет ""Статистика сайта"" сгенерирован"
 */

public class Main {
    public static void main(String[] args) {
        List<Report> reports = new ArrayList<>();
        reports.add(new PdfReport("\"Продажи за март\""));
        reports.add(new ExcelReport("\"Зарплаты\""));
        reports.add(new HtmlReport("\"Статистика сайта\""));

        ReportService reportService = new ReportService();
        reportService.generateAll(reports);
    }
}
