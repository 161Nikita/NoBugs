package homework_2;

public class Company {

    static String companyName = "МЕГА";
    final int EMPLOYEE_ID;
    String employeeName;

    Company(int someEMPLOYEE_ID, String employeeName){
        this.EMPLOYEE_ID = someEMPLOYEE_ID;
        this.employeeName = employeeName;
    }

    String getEmployeeName() {
        return this.employeeName;
    }

    void setEmployeeName(String newEmployeeName) {
        this.employeeName = newEmployeeName;
    }

    static void printCompanyName() {
        System.out.println(companyName);
    }

}
