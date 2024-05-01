### Employee Bonus Project

This project provides functionalities to manage employee data and calculate bonuses for eligible employees based on their joining and exit dates.

#### Table of Contents
1. [Introduction](#introduction)
2. [Technologies Used](#technologies-used)
3. [Setup](#setup)
4. [Endpoints](#endpoints)
5. [Data Models](#data-models)
6. [Service Layer](#service-layer)
7. [Controller Layer](#controller-layer)
8. [Repository Layer](#repository-layer)
9. [Custom Date Format](#custom-date-format)

---

#### Introduction

The Employee Bonus Project is designed to manage employee data, calculate bonuses, and provide a RESTful API to interact with the system.

#### Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Gradle

#### Setup

To set up the project, follow these steps:

1. Clone the repository.
2. Ensure you have Java and Gradle installed.
3. Build the project using `./gradlew build`.
4. Run the project using `./gradlew bootRun`.

#### Endpoints

- **POST /tci/employee-bonus/**: Store employees in the system.
  - Request Body: JSON array of `BonusEmployee` objects.

- **GET /tci/employee-bonus/**: Retrieve eligible employees for bonus calculation.
  - Query Parameter: `date` (Format: yyyy-MM-dd)

#### Data Models

- **BonusEmployee**: Represents an employee with bonus information.
- **EmployeeWrapper**: Wraps a list of `BonusEmployee` objects.
- **BonusResponse**: Represents the response object for eligible employees.
- **Employee**: JPA entity representing an employee.

#### Service Layer

- **EmployeeService**: Defines methods for managing employees and calculating bonuses.
- **EmployeeServiceImpl**: Implements `EmployeeService` with methods to store employees and calculate bonuses.

#### Controller Layer

- **EmployeeController**: Contains endpoints for interacting with employees and bonuses.

#### Repository Layer

- **EmployeeRepository**: JpaRepository for CRUD operations on employees.

#### Custom Date Format

The project uses a custom date format (MMM-dd-yyyy) for parsing and displaying dates.

---

This README.md provides an overview of the Employee Bonus Project, its components, and how to set it up. For detailed information on classes, methods, and implementations, refer to the source code.
