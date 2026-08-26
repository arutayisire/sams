package com.ulk.sams;

import com.ulk.sams.exception.*;
import com.ulk.sams.model.*;
import com.ulk.sams.service.SAMSManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final SAMSManager sams = new SAMSManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Seed sample data
        sams.seedSampleData();

        System.out.println("\n==================================================");
        System.out.println("   STUDENT ACADEMIC MANAGEMENT SYSTEM (SAMS)");
        System.out.println("   Advanced OOP with Java - Project 1");
        System.out.println("==================================================\n");

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");

            try {
                switch (choice) {
                    case 1: registerStudent(); break;
                    case 2: registerLecturer(); break;
                    case 3: createProgramme(); break;
                    case 4: createCourse(); break;
                    case 5: assignCourseToLecturer(); break;
                    case 6: registerStudentForCourse(); break;
                    case 7: recordExamResult(); break;
                    case 8: calculateStudentGPA(); break;
                    case 9: searchAndDisplayStudents(); break;
                    case 10: generatePerformanceReport(); break;
                    case 11: displayAllStudents(); break;
                    case 12: displayAllCourses(); break;
                    case 0:
                        running = false;
                        System.out.println("Thank you for using SAMS. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (DuplicateRecordException e) {
                System.err.println("❌ Error: " + e.getMessage());
            } catch (RecordNotFoundException e) {
                System.err.println("❌ Error: " + e.getMessage());
            } catch (InvalidGradeException e) {
                System.err.println("❌ Error: " + e.getMessage());
            } catch (InvalidRegistrationException e) {
                System.err.println("❌ Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("❌ Unexpected error: " + e.getMessage());
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
            scanner.nextLine(); // Consume newline
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n=== SAMS MENU ===");
        System.out.println("1. Register Student");
        System.out.println("2. Register Lecturer");
        System.out.println("3. Create Academic Programme");
        System.out.println("4. Create Course");
        System.out.println("5. Assign Course to Lecturer");
        System.out.println("6. Register Student for Course");
        System.out.println("7. Record Examination Result");
        System.out.println("8. Calculate Student GPA");
        System.out.println("9. Search and Display Students");
        System.out.println("10. Generate Performance Report");
        System.out.println("11. Display All Students");
        System.out.println("12. Display All Courses");
        System.out.println("0. Exit");
        System.out.println("==================");
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            scanner.next();
            System.out.print(prompt);
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return input;
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Please enter a valid number.");
            scanner.next();
            System.out.print(prompt);
        }
        double input = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        return input;
    }

    // ==================== MENU OPERATIONS ====================

    private static void registerStudent() throws DuplicateRecordException, RecordNotFoundException {
        System.out.println("\n--- Register Student ---");
        String id = getStringInput("Enter student ID: ");
        String name = getStringInput("Enter full name: ");
        String email = getStringInput("Enter email: ");
        String phone = getStringInput("Enter phone number: ");
        String programmeCode = getStringInput("Enter programme code (or press Enter to skip): ");

        Student student;
        if (programmeCode.isEmpty()) {
            student = sams.registerStudent(id, name, email, phone);
        } else {
            student = sams.registerStudent(id, name, email, phone, programmeCode);
        }
        System.out.println("✅ Student registered successfully: " + student);
    }

    private static void registerLecturer() throws DuplicateRecordException {
        System.out.println("\n--- Register Lecturer ---");
        String id = getStringInput("Enter lecturer ID: ");
        String name = getStringInput("Enter full name: ");
        String email = getStringInput("Enter email: ");
        String phone = getStringInput("Enter phone number: ");

        Lecturer lecturer = sams.registerLecturer(id, name, email, phone);
        System.out.println("✅ Lecturer registered successfully: " + lecturer);
    }

    private static void createProgramme() throws DuplicateRecordException, RecordNotFoundException {
        System.out.println("\n--- Create Academic Programme ---");
        String code = getStringInput("Enter programme code: ");
        String name = getStringInput("Enter programme name: ");
        String deptCode = getStringInput("Enter department code: ");

        Programme programme = sams.createProgramme(code, name, deptCode);
        System.out.println("✅ Programme created successfully: " + programme);
    }

    private static void createCourse() throws DuplicateRecordException, RecordNotFoundException {
        System.out.println("\n--- Create Course ---");
        String code = getStringInput("Enter course code: ");
        String name = getStringInput("Enter course name: ");
        int credits = getIntInput("Enter credits: ");
        String programmeCode = getStringInput("Enter programme code (or press Enter to skip): ");

        Course course;
        if (programmeCode.isEmpty()) {
            course = sams.createCourse(code, name, credits);
        } else {
            course = sams.createCourse(code, name, credits, programmeCode);
        }
        System.out.println("✅ Course created successfully: " + course);
    }

    private static void assignCourseToLecturer() throws RecordNotFoundException {
        System.out.println("\n--- Assign Course to Lecturer ---");
        String lecturerId = getStringInput("Enter lecturer ID: ");
        String courseCode = getStringInput("Enter course code: ");

        sams.assignCourseToLecturer(lecturerId, courseCode);
        System.out.println("✅ Course assigned successfully!");
    }

    private static void registerStudentForCourse() throws RecordNotFoundException, InvalidRegistrationException {
        System.out.println("\n--- Register Student for Course ---");
        String studentId = getStringInput("Enter student ID: ");
        String courseCode = getStringInput("Enter course code: ");
        String semester = getStringInput("Enter semester (e.g., 2025A): ");

        Registration registration = sams.registerStudentForCourse(studentId, courseCode, semester);
        System.out.println("✅ Student registered for course: " + registration);
    }

    private static void recordExamResult() throws RecordNotFoundException, InvalidGradeException {
        System.out.println("\n--- Record Examination Result ---");
        String studentId = getStringInput("Enter student ID: ");
        String courseCode = getStringInput("Enter course code: ");
        double score = getDoubleInput("Enter score (0-100): ");
        String semester = getStringInput("Enter semester (e.g., 2025A): ");

        ExamResult result = sams.recordExamResult(studentId, courseCode, score, semester);
        System.out.println("✅ Exam result recorded: " + result);
    }

    private static void calculateStudentGPA() throws RecordNotFoundException {
        System.out.println("\n--- Calculate Student GPA ---");
        String studentId = getStringInput("Enter student ID: ");

        double gpa = sams.calculateStudentGPA(studentId);
        Student student = sams.getStudent(studentId);
        System.out.printf("✅ GPA for %s: %.2f (%s)%n",
                student.getFullName(), gpa, student.getAcademicStanding());
    }

    private static void searchAndDisplayStudents() {
        System.out.println("\n--- Search Students ---");
        String namePattern = getStringInput("Enter name to search (or press Enter for all): ");

        List<Student> results = sams.searchStudentsByName(namePattern);
        System.out.println("\nFound " + results.size() + " student(s):");
        for (Student s : results) {
            System.out.println("  " + s);
        }
    }

    private static void generatePerformanceReport() {
        System.out.println("\n" + sams.generateClassPerformanceReport());
    }

    private static void displayAllStudents() {
        System.out.println("\n--- All Students ---");
        List<Student> students = sams.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students registered.");
        } else {
            for (Student s : students) {
                System.out.println("  " + s);
            }
        }
    }

    private static void displayAllCourses() {
        System.out.println("\n--- All Courses ---");
        List<Course> courses = sams.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses created.");
        } else {
            for (Course c : courses) {
                System.out.println("  " + c);
            }
        }
    }
}