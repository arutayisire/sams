package com.ulk.sams.service;

import com.ulk.sams.exception.*;
import com.ulk.sams.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class SAMSManager {
    // Data storage
    private final Map<String, Student> students = new HashMap<>();
    private final Map<String, Lecturer> lecturers = new HashMap<>();
    private final Map<String, Programme> programmes = new HashMap<>();
    private final Map<String, Course> courses = new HashMap<>();
    private final Map<String, Department> departments = new HashMap<>();
    private final List<Registration> registrations = new ArrayList<>();
    private final List<ExamResult> examResults = new ArrayList<>();

    // ==================== DEPARTMENT OPERATIONS ====================
    
    public Department createDepartment(String code, String name, String head) 
            throws DuplicateRecordException {
        if (departments.containsKey(code)) {
            throw new DuplicateRecordException("Department with code " + code + " already exists");
        }
        Department dept = new Department(code, name, head);
        departments.put(code, dept);
        return dept;
    }

    public Department getDepartment(String code) throws RecordNotFoundException {
        Department dept = departments.get(code);
        if (dept == null) {
            throw new RecordNotFoundException("Department with code " + code + " not found");
        }
        return dept;
    }

    // ==================== PROGRAMME OPERATIONS ====================
    
    public Programme createProgramme(String code, String name, String departmentCode) 
            throws RecordNotFoundException, DuplicateRecordException {
        if (programmes.containsKey(code)) {
            throw new DuplicateRecordException("Programme with code " + code + " already exists");
        }
        Department dept = getDepartment(departmentCode);
        Programme programme = new Programme(code, name, dept);
        programmes.put(code, programme);
        return programme;
    }

    public Programme getProgramme(String code) throws RecordNotFoundException {
        Programme programme = programmes.get(code);
        if (programme == null) {
            throw new RecordNotFoundException("Programme with code " + code + " not found");
        }
        return programme;
    }

    // ==================== COURSE OPERATIONS ====================
    
    public Course createCourse(String code, String name, int credits) 
            throws DuplicateRecordException {
        if (courses.containsKey(code)) {
            throw new DuplicateRecordException("Course with code " + code + " already exists");
        }
        Course course = new Course(code, name, credits);
        courses.put(code, course);
        return course;
    }

    public Course createCourse(String code, String name, int credits, String programmeCode) 
            throws RecordNotFoundException, DuplicateRecordException {
        Course course = createCourse(code, name, credits);
        Programme programme = getProgramme(programmeCode);
        course.setProgramme(programme);
        programme.addCourse(course);
        return course;
    }

    public Course getCourse(String code) throws RecordNotFoundException {
        Course course = courses.get(code);
        if (course == null) {
            throw new RecordNotFoundException("Course with code " + code + " not found");
        }
        return course;
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }

    // ==================== LECTURER OPERATIONS ====================
    
    public Lecturer registerLecturer(String id, String name, String email, String phone) 
            throws DuplicateRecordException {
        if (lecturers.containsKey(id)) {
            throw new DuplicateRecordException("Lecturer with ID " + id + " already exists");
        }
        Lecturer lecturer = new Lecturer(id, name, email, phone);
        lecturers.put(id, lecturer);
        return lecturer;
    }

    public Lecturer getLecturer(String id) throws RecordNotFoundException {
        Lecturer lecturer = lecturers.get(id);
        if (lecturer == null) {
            throw new RecordNotFoundException("Lecturer with ID " + id + " not found");
        }
        return lecturer;
    }

    public void assignCourseToLecturer(String lecturerId, String courseCode) 
            throws RecordNotFoundException {
        Lecturer lecturer = getLecturer(lecturerId);
        Course course = getCourse(courseCode);
        lecturer.assignCourse(course);
        course.setLecturer(lecturer);
    }

    // ==================== STUDENT OPERATIONS ====================
    
    public Student registerStudent(String id, String name, String email, String phone) 
            throws DuplicateRecordException {
        if (students.containsKey(id)) {
            throw new DuplicateRecordException("Student with ID " + id + " already exists");
        }
        Student student = new Student(id, name, email, phone);
        students.put(id, student);
        return student;
    }

    public Student registerStudent(String id, String name, String email, String phone, String programmeCode) 
            throws DuplicateRecordException, RecordNotFoundException {
        Student student = registerStudent(id, name, email, phone);
        Programme programme = getProgramme(programmeCode);
        student.setProgramme(programme);
        return student;
    }

    public Student getStudent(String id) throws RecordNotFoundException {
        Student student = students.get(id);
        if (student == null) {
            throw new RecordNotFoundException("Student with ID " + id + " not found");
        }
        return student;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    public List<Student> searchStudentsByName(String namePattern) {
        if (namePattern == null || namePattern.trim().isEmpty()) {
            return new ArrayList<>(students.values());
        }
        String pattern = namePattern.toLowerCase().trim();
        return students.values().stream()
            .filter(s -> s.getFullName().toLowerCase().contains(pattern))
            .collect(Collectors.toList());
    }

    // ==================== REGISTRATION OPERATIONS ====================
    
    public Registration registerStudentForCourse(String studentId, String courseCode, String semester) 
            throws RecordNotFoundException, InvalidRegistrationException {
        Student student = getStudent(studentId);
        Course course = getCourse(courseCode);

        // Check if already registered for this course in this semester
        boolean alreadyRegistered = registrations.stream()
            .anyMatch(r -> r.getStudent().equals(student) 
                && r.getCourse().equals(course) 
                && r.getSemester().equals(semester));
        
        if (alreadyRegistered) {
            throw new InvalidRegistrationException(
                "Student " + studentId + " is already registered for " + courseCode + " in " + semester);
        }

        Registration registration = new Registration(student, course, semester);
        registrations.add(registration);
        return registration;
    }

    public List<Registration> getStudentRegistrations(String studentId) throws RecordNotFoundException {
        Student student = getStudent(studentId);
        return registrations.stream()
            .filter(r -> r.getStudent().equals(student))
            .collect(Collectors.toList());
    }

    // ==================== EXAM RESULT OPERATIONS ====================
    
    public ExamResult recordExamResult(String studentId, String courseCode, double score, String semester) 
            throws RecordNotFoundException, InvalidGradeException {
        Student student = getStudent(studentId);
        Course course = getCourse(courseCode);
        Grade grade = Grade.fromScore(score);
        
        ExamResult result = new ExamResult(student, course, score, grade, semester);
        examResults.add(result);
        student.addExamResult(result);
        return result;
    }

    public List<ExamResult> getStudentExamResults(String studentId) throws RecordNotFoundException {
        Student student = getStudent(studentId);
        return examResults.stream()
            .filter(r -> r.getStudent().equals(student))
            .collect(Collectors.toList());
    }

    // ==================== GPA AND PERFORMANCE OPERATIONS ====================
    
    public double calculateStudentGPA(String studentId) throws RecordNotFoundException {
        Student student = getStudent(studentId);
        return student.calculateGPA();
    }

    public List<Student> getAllStudentsSortedByGPA() {
        List<Student> sorted = new ArrayList<>(students.values());
        Collections.sort(sorted);
        return sorted;
    }

    public String generateClassPerformanceReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Class Performance Report ===\n");
        List<Student> sorted = getAllStudentsSortedByGPA();
        int rank = 1;
        for (Student s : sorted) {
            sb.append(String.format("%d. %s (%s) - GPA: %.2f - %s\n", 
                rank++, s.getFullName(), s.getId(), s.calculateGPA(), s.getAcademicStanding()));
        }
        if (sorted.isEmpty()) {
            sb.append("No students registered.\n");
        }
        return sb.toString();
    }

    // ==================== REPORT OPERATIONS ====================
    
    public String generateStudentReport(String studentId) throws RecordNotFoundException {
        Student student = getStudent(studentId);
        return student.generateReport();
    }

    public String generateLecturerReport(String lecturerId) throws RecordNotFoundException {
        Lecturer lecturer = getLecturer(lecturerId);
        return lecturer.generateReport();
    }

    // ==================== UTILITY / HELPER METHODS ====================
    
    private Student getStudentOrThrow(String studentId) throws RecordNotFoundException {
        return getStudent(studentId);
    }

    private Course getCourseOrThrow(String courseCode) throws RecordNotFoundException {
        return getCourse(courseCode);
    }

    // ==================== SEED DATA / DEMO DATA ====================
    
    public void seedSampleData() {
        try {
            // Create departments
            Department compSci = createDepartment("CSCI", "Computer Science", "Prof. John Doe");
            
            // Create programmes
            Programme mis = createProgramme("MIS", "Master of Internet Systems", "CSCI");
            
            // Create courses
            Course mis601 = createCourse("MIS601", "Advanced Object-Oriented Programming", 4, "MIS");
            Course mis602 = createCourse("MIS602", "Database Management Systems", 4, "MIS");
            Course mis603 = createCourse("MIS603", "Network Security", 3, "MIS");
            
            // Register lecturers
            Lecturer lecturer1 = registerLecturer("L001", "Dr. Alice Smith", "alice@ulk.ac.rw", "+250 788 123 456");
            Lecturer lecturer2 = registerLecturer("L002", "Prof. Bob Johnson", "bob@ulk.ac.rw", "+250 788 123 457");
            
            // Assign courses to lecturers
            assignCourseToLecturer("L001", "MIS601");
            assignCourseToLecturer("L002", "MIS602");
            assignCourseToLecturer("L001", "MIS603");
            
            // Register students
            Student student1 = registerStudent("202540237", "Alexis", "alexis@ulk.ac.rw", "+250 788 123 458", "MIS");
            Student student2 = registerStudent("202540150", "Sabine Uwase", "sabine@ulk.ac.rw", "+250 788 123 459", "MIS");
            
            // Register students for courses
            registerStudentForCourse("202540237", "MIS601", "2025A");
            registerStudentForCourse("202540237", "MIS602", "2025A");
            registerStudentForCourse("202540150", "MIS601", "2025A");
            
            // Record exam results
            recordExamResult("202540237", "MIS601", 95, "2025A");
            recordExamResult("202540237", "MIS602", 87, "2025A");
            recordExamResult("202540150", "MIS601", 78, "2025A");
            
            System.out.println("Sample data seeded successfully!");
        } catch (Exception e) {
            System.err.println("Error seeding sample data: " + e.getMessage());
        }
    }
}