import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataEntryProgram {
    private static final File STUDENT_FILE = new File("students.info");

    public static void main(String[] args) {
        List<Student> students = Student.loadStudentsFromFile(STUDENT_FILE);
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("1. Add new student");
            System.out.println("2. View all students");
            System.out.println("3. Save and exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> addStudent(students, scanner);
                case 2 -> viewStudents(students);
                case 3 -> {
                    Student.saveStudentsToFile(STUDENT_FILE, students);
                    System.out.println("Data saved. Exiting.");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addStudent(List<Student> students, Scanner scanner) {
        System.out.print("Enter EduID (9 digits): ");
        String eduid = scanner.nextLine();
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Birthdate (yyyy-mm-dd): ");
        LocalDate birthdate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Parent Names: ");
        String parentNames = scanner.nextLine();
        System.out.print("Enter Parent Phone (10 digits): ");
        String parentPhone = scanner.nextLine();
        System.out.print("Enter Parent Email: ");
        String parentEmail = scanner.nextLine();
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter Grade (1-12): ");
        int grade = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        students.add(new Student(eduid, studentId, birthdate, name, address, parentNames, parentPhone, parentEmail, gender, grade));
        System.out.println("Student added successfully.");
    }

    private static void viewStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student.getStudentId() + ": " + student.getName());
        }
    }
}
