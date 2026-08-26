package com.ulk.sams.model;

import com.ulk.sams.interfaces.Reportable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student extends Person implements Reportable, Comparable<Student> {
    private Programme programme;
    private final List<ExamResult> examResults = new ArrayList<>();

    public Student(String id, String fullName, String email, String phoneNumber) {
        super(id, fullName, email, phoneNumber);
    }

    public Student(String id, String fullName, String email, String phoneNumber, Programme programme) {
        super(id, fullName, email, phoneNumber);
        this.programme = programme;
    }

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    public List<ExamResult> getExamResults() {
        return Collections.unmodifiableList(examResults);
    }

    public void addExamResult(ExamResult result) {
        if (result == null) {
            throw new IllegalArgumentException("Exam result cannot be null");
        }
        examResults.add(result);
    }

    public double calculateGPA() {
        if (examResults.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (ExamResult r : examResults) {
            total += r.getGrade().getGradePoint();
        }
        return total / examResults.size();
    }

    public String getAcademicStanding() {
        double gpa = calculateGPA();
        if (gpa >= 3.5) return "First Class";
        if (gpa >= 3.0) return "Second Class Upper";
        if (gpa >= 2.0) return "Second Class Lower";
        if (gpa >= 1.0) return "Pass";
        return "Fail";
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public String generateReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Student Report ===\n");
        sb.append("Name: ").append(getFullName()).append("\n");
        sb.append("ID: ").append(getId()).append("\n");
        sb.append("Programme: ").append(programme != null ? programme.getName() : "Not enrolled").append("\n");
        sb.append("GPA: ").append(String.format("%.2f", calculateGPA())).append("\n");
        sb.append("Academic Standing: ").append(getAcademicStanding()).append("\n");
        sb.append("Course Results:\n");
        if (examResults.isEmpty()) {
            sb.append("  No results recorded\n");
        } else {
            for (ExamResult r : examResults) {
                sb.append("  ").append(r.getCourse().getName())
                  .append(": ").append(r.getScore())
                  .append(" → ").append(r.getGrade())
                  .append(" (").append(r.getGrade().getGradePoint()).append(")\n");
            }
        }
        return sb.toString();
    }

    @Override
    public int compareTo(Student other) {
        return Double.compare(other.calculateGPA(), this.calculateGPA());
    }

    @Override
    public String toString() {
        return String.format("Student: %s (%s) - GPA: %.2f", 
            getFullName(), getId(), calculateGPA());
    }
}