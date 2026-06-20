package homework_11.grade_service;

public class StudentGrade<T extends Number> {

    private String name;
    private String subjectName;
    private T grade;

    public StudentGrade(String name, String subjectName, T grade) {
        this.name = name;
        this.subjectName = subjectName;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public T getGrade() {
        return grade;
    }
}
