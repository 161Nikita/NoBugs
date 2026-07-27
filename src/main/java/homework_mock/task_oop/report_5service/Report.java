package homework_mock.task_oop.report_5service;

public abstract class Report {

    private final String name;

    public Report(String name) {
        this.name = name;
    }

    public String getTitle() {
        return name;
    }

    public abstract void generate();
}
