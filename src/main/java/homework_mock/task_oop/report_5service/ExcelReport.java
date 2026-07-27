package homework_mock.task_oop.report_5service;

public class ExcelReport extends Report{
    public ExcelReport(String title) {
        super(title);
    }

    @Override
    public void generate() {
        System.out.println("Excel отчет " + getTitle() + " сгенерирован");
    }
}
