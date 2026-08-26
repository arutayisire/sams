package com.ulk.sams.model;

import com.ulk.sams.exception.InvalidGradeException;

public class ExamResult {
    private final Student student;
    private final Course course;
    private final double score;
    private final Grade grade;
    private final String semester;

    public ExamResult(Student student, Course course, double score, String semester) 
            throws InvalidGradeException {
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
        this.score = score;
        this.grade = Grade.fromScore(score);
        this.semester = semester;
    }

    // For testing/direct instantiation with pre-calculated grade
    public ExamResult(Student student, Course course, double score, Grade grade, String semester) {
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
        this.score = score;
        this.grade = grade;
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public double getScore() {
        return score;
    }

    public Grade getGrade() {
        return grade;
    }

    public String getSemester() {
        return semester;
    }

    @Override
    public String toString() {
        return String.format("ExamResult: %s - %s: %.1f → %s", 
            student.getFullName(), course.getName(), score, grade);
    }
}