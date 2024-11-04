import java.io.File;
import java.util.List;

public class TeacherProgram {
    private static final File STUDENT_FILE = new File("students.info");

    public static void main(String[] args) {
        List<Student> students = Student.loadStudentsFromFile(STUDENT_FILE);
        
        System.out.println("List of students:");
        for (Student student : students) {
            System.out.println("ID: " + student.getStudentId());
            System.out.println("Name: " + student.getName());
            System.out.println("Grade: " + student.getGrade());
            System.out.println("Withdrawn: " + (student.isWithdrawn() ? "Yes" : "No"));
            System.out.println("---");
        }
    }
}
