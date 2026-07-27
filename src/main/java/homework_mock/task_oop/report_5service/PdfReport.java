package homework_mock.task_oop.report_5service;

public class PdfReport extends Report{
    public PdfReport(String title) {
        super(title);
    }

    @Override
    public void generate() {
        System.out.println("PDF отчет " + getTitle() + " сгенерирован");
    }
}
