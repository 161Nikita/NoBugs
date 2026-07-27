package homework_mock.task_oop.report_5service;

public class HtmlReport extends Report{
    public HtmlReport(String title) {
        super(title);
    }

    @Override
    public void generate() {
        System.out.println("HTML отчет " + getTitle() + " сгенерирован");
    }
}
