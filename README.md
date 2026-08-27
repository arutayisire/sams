**# Student Academic Management System (SAMS)**



\[!\[Java](https://img.shields.io/badge/Java-17%2B-orange)](https://adoptium.net/)

\[!\[JUnit](https://img.shields.io/badge/JUnit-5-green)](https://junit.org/junit5/)



**## Overview**



The Student Academic Management System (SAMS) is a Java-based application designed to manage student academic information for a university. It demonstrates advanced object-oriented programming principles including encapsulation, inheritance, abstraction, polymorphism, interfaces, the Java Collections Framework, and exception handling.



**## Features**



**### Core Features**

\- \*\*Student Management\*\*: Register, search, and manage student records

\- \*\*Lecturer Management\*\*: Register lecturers and assign courses

\- \*\*Programme Management\*\*: Create and manage academic programmes

\- \*\*Course Management\*\*: Create courses and associate with programmes

\- \*\*Course Enrolment\*\*: Register students for courses

\- \*\*Exam Results\*\*: Record examination results with automatic grade calculation

\- \*\*GPA Calculation\*\*: Calculate student GPA and academic standing

\- \*\*Performance Reports\*\*: Generate class performance reports

\- \*\*Search Functionality\*\*: Search students by name

\- \*\*Exception Handling\*\*: Comprehensive error handling for invalid operations



**## Technologies Used**



\- \*\*Java 17+\*\* (OpenJDK / Eclipse Temurin)

\- \*\*JUnit 5\*\* for unit testing

\- \*\*Git\*\* for version control

\- \*\*IntelliJ IDEA\*\* (recommended IDE)



**## Project Structure**

sams/

├── src/

│ ├── main/

│ │ └── java/

│ │ └── com/

│ │ └── ulk/

│ │ └── sams/

│ │ ├── Main.java # Console UI entry point

│ │ ├── model/ # Domain model classes

│ │ │ ├── Person.java # Abstract base class

│ │ │ ├── Student.java # Student entity

│ │ │ ├── Lecturer.java # Lecturer entity

│ │ │ ├── Course.java # Course entity

│ │ │ ├── Programme.java # Programme entity

│ │ │ ├── Department.java # Department entity

│ │ │ ├── Registration.java # Enrollment entity

│ │ │ ├── ExamResult.java # Exam result entity

│ │ │ └── Grade.java # Grade enum with logic

│ │ ├── interfaces/ # Custom interfaces

│ │ │ ├── Identifiable.java # ID-based identification

│ │ │ └── Reportable.java # Report generation

│ │ ├── exception/ # Custom exception hierarchy

│ │ │ ├── SAMSException.java # Base exception

│ │ │ ├── DuplicateRecordException.java

│ │ │ ├── RecordNotFoundException.java

│ │ │ ├── InvalidGradeException.java

│ │ │ └── InvalidRegistrationException.java

│ │ └── service/ # Business logic layer

│ │ └── SAMSManager.java # Facade service

│ └── test/

│ └── java/

│ └── com/

│ └── ulk/

│ └── sams/

│ └── service/

│ └── SAMSManagerTest.java # JUnit 5 test cases

├── README.md # This file

└── .gitignore # Git ignore file



**## Installation and Setup**



**### Prerequisites**



\- \*\*Java Development Kit (JDK)\*\* 17 or higher

\- \*\*Git\*\* (for version control)

\- \*\*IntelliJ IDEA\*\* (recommended) or any Java IDE



**### Step 1: Clone the Repository**



```bash

git clone https://github.com/yourusername/sams.git

cd sams

**Step 2: Open in IntelliJ IDEA**

Open IntelliJ IDEA



Click Open or File → Open



Select the sams folder



Click OK



**Step 3: Set Up JDK**

Go to File → Project Structure → Project



Set Project SDK to JDK 17+



Click Apply then OK



**Step 4: Mark Source Roots**

Right-click src/main/java → Mark Directory as → Sources Root



Right-click src/test/java → Mark Directory as → Test Sources Root



▶️ How to Run the Application

Method 1: From IntelliJ IDEA

Navigate to Main.java in the Project panel



Right-click → Run 'Main.main()'



Or click the green ▶️ arrow in the gutter



Method 2: From Command Line

Compile:

javac -d out -encoding UTF-8 src/main/java/com/ulk/sams/\*.java src/main/java/com/ulk/sams/model/\*.java src/main/java/com/ulk/sams/interfaces/\*.java src/main/java/com/ulk/sams/exception/\*.java src/main/java/com/ulk/sams/service/\*.java

Run:

java -cp out com.ulk.sams.Main

Run Tests (requires JUnit):

javac -d out -cp . src/test/java/com/ulk/sams/service/\*.java

java -jar junit-platform-console-standalone.jar -cp out --scan-classpath



**How to Use the System**

Main Menu

When you run the application, you'll see:

=== SAMS MENU ===

1\. Register Student

2\. Register Lecturer

3\. Create Academic Programme

4\. Create Course

5\. Assign Course to Lecturer

6\. Register Student for Course

7\. Record Examination Result

8\. Calculate Student GPA

9\. Search and Display Students

10\. Generate Performance Report

11\. Display All Students

12\. Display All Courses

0\. Exit

==================

Enter your choice:





**Example Workflow**

Register a Student (Option 1)



Enter student ID, name, email, phone, and programme code



Register for Course (Option 6)



Enter student ID, course code, and semester



Record Exam Result (Option 7)



Enter student ID, course code, score (0-100), and semester



System automatically calculates the grade



View Performance (Option 10)



Generates a ranked performance report



**Sample Output**

=== Class Performance Report ===

1\. Alexis (202540237) - GPA: 4.00 - First Class

2\. Sabine Uwase (202540150) - GPA: 3.00 - Second Class Upper



**Running Tests**

In IntelliJ IDEA

Navigate to SAMSManagerTest.java



Right-click → Run 'SAMSManagerTest'



All 13 tests should pass



**Test Coverage**

Test Case	Description

registerStudent\_succeeds\_withValidData	Student registration success

registerStudent\_throwsDuplicateRecordException	Duplicate ID rejection

registerStudentForCourse\_succeeds\_andCreatesRegistration	Course enrolment success

registerStudentForCourse\_throwsInvalidRegistrationException	Duplicate enrolment rejection

recordExamResult\_calculatesCorrectGrade	Grade calculation accuracy

recordExamResult\_throwsInvalidGradeException	Invalid score rejection

calculateGPA\_returnsMeanGradePoint\_acrossMultipleResults	GPA calculation

calculateGPA\_throwsRecordNotFoundException	Unknown student handling

searchStudentsByName\_isCaseInsensitive	Search functionality

assignCourseToLecturer\_succeeds\_withValidData	Course assignment

createCourse\_throwsDuplicateRecordException	Duplicate course rejection

getAllStudentsSortedByGPA\_ordersHighestFirst	Performance ranking



Design Patterns Used

Pattern	Implementation

Facade Pattern	SAMSManager provides a simplified interface to the complex subsystem

Abstract Factory	Person hierarchy with Student and Lecturer subclasses

Template Method	Person abstract class defines common structure

Strategy Pattern	Grade enum encapsulates grading logic



**Exception Handling**

The system uses a hierarchical exception structure:

SAMSException (checked)

├── DuplicateRecordException    # For duplicate entries

├── RecordNotFoundException     # For missing records

├── InvalidGradeException       # For invalid scores

└── InvalidRegistrationException # For invalid enrollments



**UML Diagrams**

The following UML diagrams are included in the project documentation:



Use Case Diagram - Shows system interactions from user perspective



Class Diagram - Shows static structure of the system



Sequence Diagram - Shows "Record Examination Result" flow



Activity Diagram - Shows "Register Student for Course" workflow



**Sample Data**

The system seeds the following sample data automatically:



**Students**

Alexis (202540237) - MIS Programme



Sabine Uwase (202540150) - MIS Programme



**Lecturers**

Dr. Alice Smith (L001) - Teaching MIS601, MIS603



Prof. Bob Johnson (L002) - Teaching MIS602



**Courses**

MIS601: Advanced Object-Oriented Programming (4 credits)



MIS602: Database Management Systems (4 credits)



MIS603: Network Security (3 credits)



Exam Results

Alexis: MIS601 → 90% (A), MIS602 → 95% (A) → GPA: 4.0



Sabine: MIS601 → 79% (B) → GPA: 3.00



**Contributing**



This project was developed for the Advanced Object-Oriented Programming with Java course at Université Libre de Kigali (ULK).



License

This project is for educational purposes only.



**Author**

Alexis



Roll Number: 202540237



Programme: MSc Internet Systems



University: Université Libre de Kigali (ULK)



**References**



Bloch, J. (2018). Effective Java (3rd ed.). Addison-Wesley.



Deitel, P., \& Deitel, H. (2021). Java: How to Program (12th ed.). Pearson.



Gamma, E., et al. (1994). Design Patterns. Addison-Wesley.



Oracle. (2024). The Java Tutorials.



JUnit Team. (2024). JUnit 5 User Guide.



© 2026 - Student Academic Management System Project





