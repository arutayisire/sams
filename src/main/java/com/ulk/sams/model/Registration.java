package com.ulk.sams.model;

import java.time.LocalDateTime;

public class Registration {
    private final Student student;
    private final Course course;
    private final String semester;
    private final LocalDateTime registrationDate;
    private RegistrationStatus status;

    public enum RegistrationStatus {
        ENROLLED, COMPLETED, DROPPED, WITHDRAWN
    }

    public Registration(Student student, Course course, String semester) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }
        if (semester == null || semester.trim().isEmpty()) {
            throw new IllegalArgumentException("Semester cannot be null or empty");
        }
        this.student = student;
        this.course = course;
        this.semester = semester;
        this.registrationDate = LocalDateTime.now();
        this.status = RegistrationStatus.ENROLLED;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getSemester() {
        return semester;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Registration: %s - %s (%s) [%s]", 
            student.getFullName(), course.getName(), semester, status);
    }
}