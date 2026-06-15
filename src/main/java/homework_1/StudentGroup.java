package homework_1;

public class StudentGroup {

    String groupName;
    int studentCount;

    StudentGroup(String someGroupName, int someStudentCount) {
        this.groupName = someGroupName;
        this.studentCount = someStudentCount;
    }

    String getGroupName() {
        return this.groupName;
    }

    int getStudentCount() {
        return studentCount;
    }

    void setGroupName(String newGroupName) {
        this.groupName = newGroupName;
    }

    void setStudentCount(int newStudentCount) {
        this.studentCount = newStudentCount;
    }

    void printInfo() {
        System.out.println("Группа: " + this.groupName + ", Количество студентов: " + this.studentCount);
    }

}