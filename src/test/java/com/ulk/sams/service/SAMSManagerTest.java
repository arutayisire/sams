package com.ulk.sams.service;

import com.ulk.sams.exception.*;
import com.ulk.sams.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SAMSManagerTest {
    private SAMSManager sams;

    @BeforeEach
    void setUp() throws Exception {
        sams = new SAMSManager();
        // Set up test data
        sams.createDepartment("CSCI", "Computer Science", "Prof. John");
        sams.createProgramme("MIS", "Master of Internet Systems", "CSCI");
        sams.createCourse("MIS601", "Advanced OOP", 4, "MIS");
        sams.createCourse("MIS602", "Database Systems", 4, "MIS");
        sams.registerLecturer("L001", "Dr. Alice", "alice@test.com", "123");
        sams.registerStudent("S001", "John Doe", "john@test.com", "456");
    }

    // ==================== STUDENT TESTS ====================

    @Test
    void registerStudent_succeeds_withValidData() throws Exception {
        Student student = sams.registerStudent("S002", "Jane Smith", "jane@test.com", "789");
        assertNotNull(student);
        assertEquals("S002", student.getId());
        assertEquals("Jane Smith", student.getFullName());

        Student retrieved = sams.getStudent("S002");
        assertEquals(student, retrieved);
    }

    @Test
    void registerStudent_throwsDuplicateRecordException_whenIdAlreadyExists() {
        assertThrows(DuplicateRecordException.class, () -> {
            sams.registerStudent("S001", "Duplicate", "dup@test.com", "000");
        });
    }

    @Test
    void registerStudent_throwsRecordNotFoundException_forUnknownProgramme() {
        assertThrows(RecordNotFoundException.class, () -> {
            sams.registerStudent("S002", "Jane Smith", "jane@test.com", "789", "UNKNOWN");
        });
    }

    // ==================== REGISTRATION TESTS ====================

    @Test
    void registerStudentForCourse_succeeds_andCreatesRegistration() throws Exception {
        Registration reg = sams.registerStudentForCourse("S001", "MIS601", "2025A");
        assertNotNull(reg);
        assertEquals("S001", reg.getStudent().getId());
        assertEquals("MIS601", reg.getCourse().getCode());
        assertEquals("2025A", reg.getSemester());
        assertEquals(Registration.RegistrationStatus.ENROLLED, reg.getStatus());
    }

    @Test
    void registerStudentForCourse_throwsInvalidRegistrationException_onDuplicateEnrolment() throws Exception {
        sams.registerStudentForCourse("S001", "MIS601", "2025A");
        assertThrows(InvalidRegistrationException.class, () -> {
            sams.registerStudentForCourse("S001", "MIS601", "2025A");
        });
    }

    // ==================== EXAM RESULT TESTS ====================

    @Test
    void recordExamResult_calculatesCorrectGrade() throws Exception {
        sams.registerStudentForCourse("S001", "MIS601", "2025A");
        ExamResult result = sams.recordExamResult("S001", "MIS601", 88, "2025A");
        assertEquals(Grade.A, result.getGrade());
        assertEquals(4.0, result.getGrade().getGradePoint());
    }

    @Test
    void recordExamResult_throwsInvalidGradeException_forOutOfRangeScore() throws Exception {
        sams.registerStudentForCourse("S001", "MIS601", "2025A");
        assertThrows(InvalidGradeException.class, () -> {
            sams.recordExamResult("S001", "MIS601", 150, "2025A");
        });
        assertThrows(InvalidGradeException.class, () -> {
            sams.recordExamResult("S001", "MIS601", -5, "2025A");
        });
    }

    // ==================== GPA TESTS ====================

    @Test
    void calculateGPA_returnsMeanGradePoint_acrossMultipleResults() throws Exception {
        sams.registerStudentForCourse("S001", "MIS601", "2025A");
        sams.registerStudentForCourse("S001", "MIS602", "2025A");
        sams.recordExamResult("S001", "MIS601", 88, "2025A"); // A = 4.0
        sams.recordExamResult("S001", "MIS602", 74, "2025A"); // B = 3.0

        double gpa = sams.calculateStudentGPA("S001");
        assertEquals(3.5, gpa, 0.001);
    }

    @Test
    void calculateGPA_throwsRecordNotFoundException_forUnknownStudent() {
        assertThrows(RecordNotFoundException.class, () -> {
            sams.calculateStudentGPA("UNKNOWN");
        });
    }

    // ==================== SEARCH TESTS ====================

    @Test
    void searchStudentsByName_isCaseInsensitive_andMatchesSubstrings() throws Exception {
        sams.registerStudent("S002", "Jane Smith", "jane@test.com", "789");
        sams.registerStudent("S003", "Johnny Bravo", "johnny@test.com", "000");

        var results = sams.searchStudentsByName("john");
        assertEquals(2, results.size());

        results = sams.searchStudentsByName("brav");
        assertEquals(1, results.size());
        assertEquals("Johnny Bravo", results.get(0).getFullName());
    }

    // ==================== LECTURER TESTS ====================

    @Test
    void assignCourseToLecturer_throwsRecordNotFoundException_forUnknownCourse() {
        assertThrows(RecordNotFoundException.class, () -> {
            sams.assignCourseToLecturer("L001", "UNKNOWN");
        });
    }

    @Test
    void assignCourseToLecturer_succeeds_withValidData() throws Exception {
        sams.assignCourseToLecturer("L001", "MIS601");
        Lecturer lecturer = sams.getLecturer("L001");
        assertEquals(1, lecturer.getCourseCount());
        assertEquals("MIS601", lecturer.getAssignedCourses().get(0).getCode());
    }

    // ==================== COURSE TESTS ====================

    @Test
    void createCourse_throwsDuplicateRecordException_whenCodeAlreadyExists() {
        assertThrows(DuplicateRecordException.class, () -> {
            sams.createCourse("MIS601", "Duplicate Course", 3);
        });
    }

    @Test
    void createCourse_succeeds_withValidData() throws Exception {
        Course course = sams.createCourse("MIS603", "Network Security", 3);
        assertNotNull(course);
        assertEquals("MIS603", course.getCode());
        assertEquals(3, course.getCredits());
    }

    // ==================== PERFORMANCE REPORT TESTS ====================

    @Test
    void getAllStudentsSortedByGPA_ordersHighestFirst() throws Exception {
        sams.registerStudent("S002", "Alice", "alice@test.com", "111");
        sams.registerStudent("S003", "Bob", "bob@test.com", "222");

        sams.registerStudentForCourse("S001", "MIS601", "2025A");
        sams.registerStudentForCourse("S002", "MIS601", "2025A");
        sams.registerStudentForCourse("S003", "MIS601", "2025A");

        sams.recordExamResult("S001", "MIS601", 88, "2025A");
        sams.recordExamResult("S002", "MIS601", 75, "2025A");
        sams.recordExamResult("S003", "MIS601", 60, "2025A");

        var sorted = sams.getAllStudentsSortedByGPA();
        assertEquals(3, sorted.size());
        assertTrue(sorted.get(0).calculateGPA() >= sorted.get(1).calculateGPA());
        assertTrue(sorted.get(1).calculateGPA() >= sorted.get(2).calculateGPA());
    }
}