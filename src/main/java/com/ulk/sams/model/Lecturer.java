package com.ulk.sams.model;

import com.ulk.sams.interfaces.Reportable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lecturer extends Person implements Reportable {
    private final List<Course> assignedCourses = new ArrayList<>();

    public Lecturer(String id, String fullName, String email, String phoneNumber) {
        super(id, fullName, email, phoneNumber);
    }

    public List<Course> getAssignedCourses() {
        return Collections.unmodifiableList(assignedCourses);
    }

    public void assignCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }
        if (!assignedCourses.contains(course)) {
            assignedCourses.add(course);
        }
    }

    public void removeCourse(Course course) {
        assignedCourses.remove(course);
    }

    public int getCourseCount() {
        return assignedCourses.size();
    }

    @Override
    public String getRole() {
        return "Lecturer";
    }

    @Override
    public String generateReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Lecturer Report ===\n");
        sb.append("Name: ").append(getFullName()).append("\n");
        sb.append("ID: ").append(getId()).append("\n");
        sb.append("Email: ").append(getEmail()).append("\n");
        sb.append("Assigned Courses:\n");
        if (assignedCourses.isEmpty()) {
            sb.append("  No courses assigned\n");
        } else {
            for (Course c : assignedCourses) {
                sb.append("  ").append(c.getCode()).append(": ").append(c.getName()).append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("Lecturer: %s (%s)", getFullName(), getId());
    }
}