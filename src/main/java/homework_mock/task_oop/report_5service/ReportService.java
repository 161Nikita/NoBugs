package homework_mock.task_oop.report_5service;

import java.util.List;

public class ReportService {
    public void generateAll(List<Report> reports) {
        for (Report report : reports) {
            report.generate();
        }
    }
}
