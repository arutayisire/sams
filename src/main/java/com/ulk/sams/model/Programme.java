package com.ulk.sams.model;

import com.ulk.sams.interfaces.Identifiable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Programme implements Identifiable {
    private final String code;
    private String name;
    private Department department;
    private final List<Course> courses = new ArrayList<>();

    public Programme(String code, String name, Department department) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Programme code cannot be null or empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Programme name cannot be null or empty");
        }
        this.code = code;
        this.name = name;
        this.department = department;
    }

    @Override
    public String getId() {
        return code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Programme name cannot be null or empty");
        }
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Course> getCourses() {
        return Collections.unmodifiableList(courses);
    }

    public void addCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }
        if (!courses.contains(course)) {
            courses.add(course);
        }
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    public int getTotalCredits() {
        int total = 0;
        for (Course c : courses) {
            total += c.getCredits();
        }
        return total;
    }

    @Override
    public String toString() {
        return String.format("Programme: %s (%s) - %d courses", name, code, courses.size());
    }
}