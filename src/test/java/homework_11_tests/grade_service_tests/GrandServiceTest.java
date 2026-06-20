package homework_11_tests.grade_service_tests;

import homework_11.grade_service.GradeService;
import homework_11.grade_service.InvalidGradeException;
import homework_11.grade_service.StudentGrade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GrandServiceTest {
    /**
     * Позитивные кейсы:
     * <p>
     * Добавления оценки по предмету 5 -> 5
     * Добавления оценки по предмету 7.3 -> 7.3
     * <p>
     * Расчет среднего значения по предмету 10.0 -> 10.0
     * <p>
     * Угловые кейсы:
     * Добавления оценки по предмету 0 -> 0
     * Расчет среднего значения по предмету 0 -> 0
     * <p>
     * Негативные кейсы:
     * <p>
     * Добавление отрицательной оценки по предмету -4 -> InvalidGradeException
     * Расчет среднего значения по предмету которого нет в списке Физра -> InvalidGradeException (возможно другое исключения, проверить!
     */


    @Test
    @DisplayName("Добавления оценки по предмету 5 -> 5")
    public void testValidAddIntGrade() {
        GradeService<Integer> service = new GradeService<>();

        service.addGrade(new StudentGrade<>("Niktia", "History", 5));
        service.addGrade(new StudentGrade<>("Petr", "History", 0));

        assertEquals(5, service.getAll().get(0).getGrade());
        assertEquals(0, service.getAll().get(1).getGrade());

    }

    @Test
    @DisplayName("Добавления оценки по предмету 7.3 -> 7.3")
    public void testValidAddDoubleGrade() {
        GradeService<Double> service = new GradeService<>();

        service.addGrade(new StudentGrade<>("Niktia", "History", 7.3));

        assertEquals(1, service.getAll().size());
        assertEquals(7.3, service.getAll().get(0).getGrade());
    }

    @Test
    @DisplayName("Расчет среднего значения по предмету 10 -> 10")
    public void testAvgGradeSubjectName() {

        GradeService<Integer> service = new GradeService<>();

        service.addGrade(new StudentGrade<>("Nikita", "History", 7));
        service.addGrade(new StudentGrade<>("Anna", "History", 8));
        service.addGrade(new StudentGrade<>("Kirill", "History", 15));
        service.addGrade(new StudentGrade<>("Petr", "Физра", 0));

        double avg1 = service.avgGradeSubjectName("History");
        double avg2 = service.avgGradeSubjectName("Физра");

        assertEquals(10.0, avg1);
        assertEquals(0.0, avg2);
    }

    @Test
    @DisplayName("Добавление отрицательной оценки по предмету -4 -> InvalidGradeException")
    public void testInValidAddIntGrade() {
        GradeService<Integer> service = new GradeService<>();

        assertThrows(InvalidGradeException.class, () -> service.addGrade(new StudentGrade<>("Niktia", "History", -4)));

    }

    @Test
    @DisplayName("Расчет среднего значения по предмету которого нет в списке Физра -> InvalidGradeException!")
    public void testInValidAddDoubleGrade() {
        GradeService<Integer> service = new GradeService<>();

        StudentGrade student = new StudentGrade<>("Nikita", "Math", 10);

        assertThrows(InvalidGradeException.class, () -> service.avgGradeSubjectName("Физра"));
    }
}

