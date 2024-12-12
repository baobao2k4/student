import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Lớp đại diện cho một sinh viên
class Student {
    private int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    // Tính xếp hạng dựa trên điểm
    public String getRank() {
        if (marks <= 5.0) return "Fail";
        else if (marks <= 6.5) return "Medium";
        else if (marks <= 7.5) return "Good";
        else if (marks <= 9.0) return "Very Good";
        else return "Excellent";
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Rank: " + getRank();
    }
}

public class StudentManagement {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display Students");
            System.out.println("5. Sort by Selection Sort");
            System.out.println("6. Sort by Quick Sort");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent(students, scanner);
                    break;
                case 2:
                    editStudent(students, scanner);
                    break;
                case 3:
                    deleteStudent(students, scanner);
                    break;
                case 4:
                    displayStudents(students);
                    break;
                case 5:
                    selectionSort(students);
                    displayStudents(students);
                    break;
                case 6:
                    quickSort(students, 0, students.size() - 1);
                    System.out.println("List sorted using Quick Sort!");
                    displayStudents(students);
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Thêm sinh viên vào danh sách
    private static void addStudent(List<Student> students, Scanner scanner) {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Student Marks: ");
        double marks = scanner.nextDouble();
        students.add(new Student(id, name, marks));
        System.out.println("Student added successfully!");
    }

    // Sửa thông tin sinh viên dựa trên ID
    private static void editStudent(List<Student> students, Scanner scanner) {
        System.out.print("Enter Student ID to edit: ");
        int id = scanner.nextInt();
        for (Student student : students) {
            if (student.getId() == id) {
                scanner.nextLine();
                System.out.print("Enter new name: ");
                String name = scanner.nextLine();
                System.out.print("Enter new marks: ");
                double marks = scanner.nextDouble();
                student.setName(name);
                student.setMarks(marks);
                System.out.println("Student updated successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    // Xóa sinh viên dựa trên ID
    private static void deleteStudent(List<Student> students, Scanner scanner) {
        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();
        students.removeIf(student -> student.getId() == id);
        System.out.println("Student deleted successfully!");
    }

    // Hiển thị danh sách sinh viên
    private static void displayStudents(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students found!");
        } else {
            System.out.println("--- Student List ---");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    // Sắp xếp danh sách bằng Selection Sort
    private static void selectionSort(List<Student> students) {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (students.get(j).getMarks() > students.get(maxIndex).getMarks()) {
                    maxIndex = j;
                }
            }
            // Hoán đổi hai sinh viên
            Student temp = students.get(i);
            students.set(i, students.get(maxIndex));
            students.set(maxIndex, temp);
        }
        System.out.println("List sorted using Selection Sort!");
    }

    // Sắp xếp danh sách bằng Quick Sort
    private static void quickSort(List<Student> students, int low, int high) {
        if (low < high) {
            int pi = partition(students, low, high);
            quickSort(students, low, pi - 1);
            quickSort(students, pi + 1, high);
        }
    }

    // Hàm chia danh sách theo pivot
    private static int partition(List<Student> students, int low, int high) {
        double pivot = students.get(high).getMarks();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (students.get(j).getMarks() > pivot) {
                i++;
                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }
        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high));
        students.set(high, temp);
        return i + 1;
    }
}
