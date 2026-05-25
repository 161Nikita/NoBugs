package homework_2;

public class University {

    static String universityName = "BGU";
    final int STUDENT_ID;
    String studentName;

    University( int someSTUDENT_ID, String someStudentName) {
        this.STUDENT_ID = someSTUDENT_ID;
        this.studentName = someStudentName;
    }

    static String changeUniversityName(String newName) {
        return University.universityName = newName;
    }

    String getStudentName() {
        return this.studentName;
    }

    void printStudentInfo() {
        System.out.println("Имя: " + this.studentName + " ID: " + this.STUDENT_ID + " Университет: " + universityName);
    }
}