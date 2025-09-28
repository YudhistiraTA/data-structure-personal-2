import java.util.LinkedList;
import java.util.Scanner;

class Student {
    private String name;
    private String id;
    private String major;

    public Builder builder(Scanner scanner) {
        return new Builder(scanner);
    }

    public static class Builder {
        private String name;
        private String id;
        private String major;
        private Scanner scanner;

        public Builder(Scanner scanner) {
            this.scanner = scanner;
        }

        public Builder inputName() {
            System.out.print("Enter name: ");
            this.name = scanner.nextLine();
            if (this.name.length() > 30) {
                System.out.println("Name cannot exceed 30 characters. Please re-enter.");
                return inputName();
            }
            return this;
        }

        public Builder inputId() {
            System.out.print("Enter ID: ");
            this.id = scanner.nextLine();
            if (this.id.length() > 10) {
                System.out.println("ID cannot exceed 10 characters. Please re-enter.");
                return inputId();
            }
            return this;
        }

        public Builder inputMajor() {
            System.out.print("Enter major: ");
            this.major = scanner.nextLine();
            if (this.major.length() > 50) {
                System.out.println("Major cannot exceed 50 characters. Please re-enter.");
                return inputMajor();
            }
            return this;
        }

        public Student build() {
            Student student = new Student();
            student.name = this.name;
            student.id = this.id;
            student.major = this.major;
            return student;
        }
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getMajor() {
        return major;
    }
}

class StudentList {
    private LinkedList<Student> students = new LinkedList<>();
    private Scanner scanner;
    private static final int MAX_STUDENTS = 5;

    public StudentList(Scanner scanner) {
        this.scanner = scanner;
    }

    public void addStudent() {
        if (students.size() >= MAX_STUDENTS) {
            System.out.println("Cannot add more students. Maximum limit reached.");
            return;
        }
        Student student = new Student()
                .builder(scanner)
                .inputName()
                .inputId()
                .inputMajor()
                .build();
        int index = 0;
        for (Student s : students) {
            if (s.getId().compareTo(student.getId()) > 0) {
                break;
            }
            index++;
        }
        students.add(index, student);
        System.out.println("Student added successfully.");
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        System.out.printf("%-30s %-10s %-50s%n", "Name", "ID", "Major");
        for (Student s : students) {
            System.out.printf("%-30s %-10s %-50s%n", s.getName(), s.getId(), s.getMajor());
        }
    }

    public void removeAllStudents() {
        students.clear();
        System.out.println("All students have been removed.");
    }
}

public class Main {
    private static int choice = -1;
    private static Scanner scanner = new Scanner(System.in);
    private static StudentList studentList = new StudentList(scanner);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Remove All Students");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    studentList.addStudent();
                    break;
                case 2:
                    studentList.viewStudents();
                    break;
                case 3:
                    studentList.removeAllStudents();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}