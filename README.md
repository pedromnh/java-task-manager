# Java Task Manager

A simple RESTful task management API built with Spring Boot and H2 database.

## Features

- Create, read, update, and delete tasks
- Task status tracking (IN_PROGRESS, COMPLETED)
- Due date management
- In-memory H2 database with web console
- RESTful API endpoints

## Tech Stack

- Java 25
- Spring Boot 3.5.6
- Spring Data JPA
- H2 Database
- Maven
- JUnit 5
- Mockito

## Getting Started

### Prerequisites

- Java 25
- Maven 3.9.9

### Installation

1. Clone the repository:
   ```
   git clone https://github.com/pedromnh/java-task-manager.git
   cd java-task-manager
   ```

2. Build the project:
   ```
   mvn clean install
   ```

3. Run the application:
   ```
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`

## API Endpoints

### Get All Tasks
```http
GET /tasks
```
**Query Parameters:**
- `status` (optional): Filter by status (IN_PROGRESS or COMPLETED)
- `dueBefore` (optional): Filter tasks with due date on or before the specified date (YYYY-MM-DD)

**Example:**
```http
GET /tasks?status=IN_PROGRESS&dueBefore=2025-09-30
```

### Get Task by ID
```http
GET /tasks/{id}
```
**Example:**
```http
GET /tasks/1
```

### Create a New Task
```http
POST /tasks
Content-Type: application/json

{
  "title": "New Task",
  "description": "Task description",
  "status": "IN_PROGRESS",
  "dueDate": "2025-10-15"
}
```

### Update a Task
```http
PUT /tasks/{id}
Content-Type: application/json

{
  "title": "Updated Task",
  "description": "Updated description",
  "status": "COMPLETED",
  "dueDate": "2025-10-20"
}
```

### Delete a Task
```http
DELETE /tasks/{id}
```
**Example:**
```http
DELETE /tasks/1
```

### Sample Response
```json
{
  "id": 1,
  "title": "Task 1",
  "description": "Description of Task 1",
  "status": "IN_PROGRESS",
  "dueDate": "2025-09-26"
}
```

## H2 Database Console

Access the H2 database console at: `http://localhost:8080/h2-console`

- JDBC URL: `jdbc:h2:mem:taskdb`
- Username: `sa`
- Password: (leave empty)

## Sample Task

```json
{
  "title": "Sample Task",
  "description": "This is a sample task",
  "status": "IN_PROGRESS",
  "dueDate": "2025-12-31"
}
```

## Running Tests

```
mvn test
```
