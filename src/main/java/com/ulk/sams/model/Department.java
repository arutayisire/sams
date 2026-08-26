package com.ulk.sams.model;

import com.ulk.sams.interfaces.Identifiable;

public class Department implements Identifiable {
    private final String code;
    private String name;
    private String headOfDepartment;

    public Department(String code, String name, String headOfDepartment) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Department code cannot be null or empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Department name cannot be null or empty");
        }
        this.code = code;
        this.name = name;
        this.headOfDepartment = headOfDepartment;
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
            throw new IllegalArgumentException("Department name cannot be null or empty");
        }
        this.name = name;
    }

    public String getHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(String headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    @Override
    public String toString() {
        return String.format("Department: %s (%s)", name, code);
    }
}