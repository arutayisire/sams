package com.ulk.sams.model;

import com.ulk.sams.exception.InvalidGradeException;

public enum Grade {
    A(4.0, 80, 100),
    B(3.0, 70, 79),
    C(2.0, 60, 69),
    D(1.0, 50, 59),
    F(0.0, 0, 49);

    private final double gradePoint;
    private final int minScore;
    private final int maxScore;

    Grade(double gradePoint, int minScore, int maxScore) {
        this.gradePoint = gradePoint;
        this.minScore = minScore;
        this.maxScore = maxScore;
    }

    public double getGradePoint() {
        return gradePoint;
    }

    public int getMinScore() {
        return minScore;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public static Grade fromScore(double score) throws InvalidGradeException {
        if (score < 0 || score > 100) {
            throw new InvalidGradeException("Score must be between 0 and 100. Received: " + score);
        }
        for (Grade grade : values()) {
            if (score >= grade.minScore && score <= grade.maxScore) {
                return grade;
            }
        }
        throw new InvalidGradeException("Unable to determine grade for score: " + score);
    }

    public String getDescription() {
        switch (this) {
            case A: return "First Class";
            case B: return "Second Class Upper";
            case C: return "Second Class Lower";
            case D: return "Pass";
            case F: return "Fail";
            default: return "Unknown";
        }
    }
}