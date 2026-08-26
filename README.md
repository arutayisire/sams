\# Student Academic Management System (SAMS)



\## 📋 Overview



The Student Academic Management System (SAMS) is a Java-based application designed to manage student academic information for a university. It demonstrates advanced object-oriented programming principles including encapsulation, inheritance, abstraction, polymorphism, interfaces, the Java Collections Framework, and exception handling.



\## 🎯 Features



\### Core Features

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



\## 🛠️ Technologies Used



\- \*\*Java 17+\*\* (OpenJDK / Eclipse Temurin)

\- \*\*JUnit 5\*\* for unit testing

\- \*\*Git\*\* for version control

\- \*\*IntelliJ IDEA\*\* (recommended IDE)



\## 📁 Project Structure

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

\## 🚀 Installation and Setup



\### Prerequisites



\- \*\*Java Development Kit (JDK)\*\* 17 or higher

\- \*\*Git\*\* (for version control)

\- \*\*IntelliJ IDEA\*\* (recommended) or any Java IDE



\### Step 1: Clone the Repository



```bash

git clone https://github.com/yourusername/sams.git

cd sams



