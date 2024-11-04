import java.io.File;
import java.util.List;
import java.util.Scanner;

public class AdminProgram {
    private static final File STUDENT_FILE = new File("students.info");
    private static final String ADMIN_PASSWORD = "admin123";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        if (!ADMIN_PASSWORD.equals(password)) {
            System.out.println("Invalid password. Access denied.");
            return;
        }

        List<Student> students = Student.loadStudentsFromFile(STUDENT_FILE);
        
        while (true) {
            System.out.println("1. View all students");
            System.out.println("2. Edit student data");
            System.out.println("3. Save and exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewStudents(students);
                case 2 -> editStudent(students, scanner);
                case 3 -> {
                    Student.saveStudentsToFile(STUDENT_FILE, students);
                    System.out.println("Data saved. Exiting.");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void viewStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println("ID: " + student.getStudentId());
            System.out.println("Name: " + student.getName());
            System.out.println("EduID: " + student.getEduId());
            System.out.println("Grade: " + student.getGrade());
            System.out.println("Withdrawn: " + (student.isWithdrawn() ? "Yes" : "No"));
            System.out.println("---");
        }
    }

    private static void editStudent(List<Student> students, Scanner scanner) {
        System.out.print("Enter student ID to edit: ");
        String studentId = scanner.nextLine();

        Student student = students.stream()
                                  .filter(s -> s.getStudentId().equals(studentId))
                                  .findFirst()
                                  .orElse(null);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Editing student " + student.getName());
        System.out.print("Enter new phone (10 digits): ");
        String newPhone = scanner.nextLine();
        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();
        student.updateContactInfo(newPhone, newEmail);
        System.out.println("Contact info updated.");
    }
}

