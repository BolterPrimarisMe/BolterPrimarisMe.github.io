import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class Student implements Serializable, Comparable<Student> {
    private static final long serialVersionUID = 1L;

    // Fields
    private String eduid; // only accessible by admin
    private String studentId;
    private LocalDate birthdate;
    private String name;
    private String address;
    private String parentNames;
    private String parentPhone;
    private String parentEmail;
    private String gender;
    private int grade;
    private boolean isWithdrawn;
    private LocalDate lastUpdated;

    // Constructor
    public Student(String eduid, String studentId, LocalDate birthdate, String name, String address,
                   String parentNames, String parentPhone, String parentEmail, String gender, int grade) {
        this.eduid = eduid;
        this.studentId = studentId;
        this.birthdate = birthdate;
        this.name = name;
        this.address = address;
        this.parentNames = parentNames;
        this.parentPhone = parentPhone;
        this.parentEmail = parentEmail;
        this.gender = gender;
        this.grade = grade;
        this.isWithdrawn = false;
        this.lastUpdated = LocalDate.now();
    }

    // Methods
    public void advanceToNextGrade() {
        if (!isWithdrawn) {
            if (grade < 12) {
                grade++;
            } else {
                grade = -1; // Use -1 to represent "Graduated"
                System.out.println(name + " has graduated.");
            }
            lastUpdated = LocalDate.now();
        } else {
            System.out.println("Cannot advance grade. Student is withdrawn.");
        }
    }

    public void withdraw() {
        this.isWithdrawn = true;
        System.out.println(name + " has been withdrawn.");
    }

    public int getAge() {
        return Period.between(birthdate, LocalDate.now()).getYears();
    }

    // Additional method: updateContactInfo
    public void updateContactInfo(String newPhone, String newEmail) {
        if (!isWithdrawn) {
            if (validatePhone(newPhone) && validateEmail(newEmail)) {
                this.parentPhone = newPhone;
                this.parentEmail = newEmail;
                lastUpdated = LocalDate.now();
            } else {
                System.out.println("Invalid contact information.");
            }
        } else {
            System.out.println("Cannot update contact info. Student is withdrawn.");
        }
    }

    // Validation Methods
    private boolean validateEduId(String eduid) {
        return eduid.matches("\\d{9}");
    }

    private boolean validatePhone(String phone) {
        return phone.matches("\\d{10}"); // Simple 10-digit phone number
    }

    private boolean validateEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@[\\w-\\.]+\\.[a-z]{2,3}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    // Comparable implementation to sort by studentId
    @Override
    public int compareTo(Student other) {
        return this.studentId.compareTo(other.studentId);
    }

    // Getters and Setters
    public String getEduId() {
        return eduid;
    }

    public String getStudentId() {
        return studentId;
    }

    public int getGrade() {
        return grade == -1 ? 12 : grade; // Show 12 for "graduated"
    }

    public boolean isWithdrawn() {
        return isWithdrawn;
    }

    // Serialize and deserialize methods
    public static void saveStudentsToFile(File file, java.util.List<Student> students) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    public static java.util.List<Student> loadStudentsFromFile(File file) {
        if (!file.exists()) {
            return new java.util.ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (java.util.List<Student>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading students: " + e.getMessage());
            return new java.util.ArrayList<>();
        }
    }
}

