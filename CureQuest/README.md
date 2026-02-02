# CureQuest - Healthcare Management System 🏥

A backend healthcare management system built with Spring Boot to manage patients, doctors, diseases, hospitals, medical expenses, and feedback with secure JWT authentication.

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-brightgreen)
![Java](https://img.shields.io/badge/Java-21-orange)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)

## 📋 Overview

CureQuest is a RESTful backend application developed as part of the PG-DAC curriculum at C-DAC ACTS, Pune. It provides APIs for managing healthcare data and includes a medical expense estimation tool.

**Key Highlights:**
- JWT-based authentication with BCrypt password encryption
- 8 REST controllers managing different healthcare modules
- Expense calculator for treatment cost estimation
- CORS-enabled for cross-origin requests

## ✨ Features

### Implemented Modules

**Patient Management**
- Get all patients
- Get patient by ID
- Add new patient
- Delete patient

**Doctor Management**
- Get all doctors
- Add new doctor

**Disease Information**
- Get all diseases
- Add new disease

**Hospital Management**
- Get all hospitals
- Add new hospital
- Delete hospital

**Expense Tracking**
- Get all expenses
- Add new expense

**Feedback System**
- Get all feedback
- Add feedback (with doctor and patient reference)
- Delete feedback

**Medical Expense Estimator**
- Calculate treatment costs based on:
  - Treatment type (general, medication, diagnostics, surgery_minor, surgery_major)
  - Hospital type (govt, private, private_room, deluxe, icu)
  - Doctor type (general, specialist)
  - Days admitted
  - Medication requirements
  - Diagnostic tests
  - Discount percentage

**Authentication & Security**
- User signup with role assignment
- User login with JWT token generation
- Password encryption using BCrypt
- 5-hour JWT token expiration
- Protected API endpoints

## 🛠️ Tech Stack

- **Framework**: Spring Boot 3.5
- **Language**: Java 21
- **Security**: Spring Security + JWT (JJWT)
- **ORM**: Hibernate / JPA
- **Database**: MySQL 8.0
- **Build Tool**: Maven
- **Libraries**: Lombok, ModelMapper

## 🔌 API Endpoints

### Authentication (Public)
```
POST   /auth/signup          - Register new user
POST   /auth/login           - Login and get JWT token
```

### Patient APIs (Protected)
```
GET    /api/patients         - Get all patients
GET    /api/patients/{id}    - Get patient by ID
POST   /api/patients         - Create new patient
DELETE /api/patients/{id}    - Delete patient
```

### Doctor APIs (Protected)
```
GET    /api/doctors          - Get all doctors
POST   /api/doctors          - Add new doctor
```

### Disease APIs (Protected)
```
GET    /api/diseases         - Get all diseases
POST   /api/diseases         - Add new disease
```

### Hospital APIs (Protected)
```
GET    /api/hospitals        - Get all hospitals
POST   /api/hospitals        - Add new hospital
DELETE /api/hospitals/{id}   - Delete hospital
```

### Expense APIs (Protected)
```
GET    /api/expenses         - Get all expenses
POST   /api/expenses         - Add new expense
```

### Feedback APIs (Protected)
```
GET    /api/feedbacks                           - Get all feedback
POST   /api/feedbacks?doctorId={id}&patientId={id}  - Add feedback
DELETE /api/feedbacks/{id}                      - Delete feedback
```

### Expense Estimator API (Protected)
```
POST   /api/estimator/calculate  - Calculate treatment cost
```

**Sample Request:**
```json
{
  "treatmentType": "surgery_major",
  "hospitalType": "private_room",
  "doctorType": "specialist",
  "daysAdmitted": 3,
  "medicationRequired": true,
  "testsRequired": true,
  "discountPercentage": 10
}
```

**Sample Response:**
```json
{
  "consultation": 1000.0,
  "medicine": 1500.0,
  "tests": 2500.0,
  "surgery": 70000.0,
  "bedCharges": 9000.0,
  "grossBill": 84000.0,
  "finalPayable": 75600.0,
  "currency": "INR"
}
```

## 🗄️ Database Schema

### Entities

**User**
- id (Long, Primary Key)
- username (String, Unique)
- email (String, Unique)
- password (String, Encrypted)
- role (String)

**Patient**
- id (Long, Primary Key)
- name (String)
- age (int)
- gender (String)
- contact (String)
- address (String)

**Doctor**
- id (Long, Primary Key)
- name (String)
- specialization (String)
- location (String)
- experience (int)
- rating (double)

**Disease**
- id (Long, Primary Key)
- diseaseName (String)
- symptoms (String)
- precaution (String)
- description (String)

**Hospital**
- id (Long, Primary Key)
- name (String)
- address (String)
- contactNumber (String)
- specialization (String)

**Expense**
- id (Long, Primary Key)
- expenseType (String)
- amount (double)
- date (String)
- description (String)

**Feedback**
- id (Long, Primary Key)
- comment (String)
- rating (int)
- patient (ManyToOne relationship)
- doctor (ManyToOne relationship)

### Relationships
- Feedback has ManyToOne relationship with Patient
- Feedback has ManyToOne relationship with Doctor

## 📦 Installation & Setup

### Prerequisites
- Java 21
- Maven 3.6+
- MySQL 8.0+

### Steps

1. **Clone the repository**
```bash
git clone https://github.com/Eshuv/CureQuest.git
cd CureQuest
```

2. **Create MySQL Database**
```sql
CREATE DATABASE curequest_db;
```

3. **Configure Database**

Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/curequest_db?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

server.port=8080

jwt.secret=your_secret_key_here_min_256_bits
```

4. **Build the project**
```bash
mvn clean install
```

5. **Run the application**
```bash
mvn spring-boot:run
```

Server starts on: `http://localhost:8080`

## 💻 Usage Examples

### 1. Register a New User
```bash
curl -X POST http://localhost:8080/auth/signup \
-H "Content-Type: application/json" \
-d '{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "password123",
  "role": "USER"
}'
```

### 2. Login and Get JWT Token
```bash
curl -X POST http://localhost:8080/auth/login \
-H "Content-Type: application/json" \
-d '{
  "username": "john_doe",
  "password": "password123"
}'
```
Response: JWT token string

### 3. Add a Patient (Requires JWT)
```bash
curl -X POST http://localhost:8080/api/patients \
-H "Authorization: Bearer YOUR_JWT_TOKEN" \
-H "Content-Type: application/json" \
-d '{
  "name": "Jane Smith",
  "age": 35,
  "gender": "Female",
  "contact": "9876543210",
  "address": "123 Main St"
}'
```

### 4. Get All Doctors
```bash
curl -X GET http://localhost:8080/api/doctors \
-H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### 5. Calculate Treatment Cost
```bash
curl -X POST http://localhost:8080/api/estimator/calculate \
-H "Authorization: Bearer YOUR_JWT_TOKEN" \
-H "Content-Type: application/json" \
-d '{
  "treatmentType": "surgery_minor",
  "hospitalType": "private",
  "doctorType": "specialist",
  "daysAdmitted": 2,
  "medicationRequired": true,
  "testsRequired": true,
  "discountPercentage": 5
}'
```

### 6. Add Feedback
```bash
curl -X POST "http://localhost:8080/api/feedbacks?doctorId=1&patientId=1" \
-H "Authorization: Bearer YOUR_JWT_TOKEN" \
-H "Content-Type: application/json" \
-d '{
  "comment": "Excellent treatment",
  "rating": 5
}'
```

## 🔐 Security

### Authentication Flow
1. User signs up via `/auth/signup` → Password is encrypted with BCrypt
2. User logs in via `/auth/login` → Returns JWT token
3. Client includes JWT token in Authorization header: `Bearer <token>`
4. `JwtFilter` validates the token for protected endpoints
5. Token expires after 5 hours

### Security Configuration
- All `/api/*` endpoints require authentication
- `/auth/*` endpoints are public
- CORS enabled for all origins (`@CrossOrigin("*")`)
- CSRF disabled (stateless REST API)

## 🏗️ Project Structure

```
src/main/java/com/curequest/
├── controller/          # REST Controllers (8 files)
│   ├── AuthController.java
│   ├── PatientController.java
│   ├── DoctorController.java
│   ├── DiseaseController.java
│   ├── HospitalController.java
│   ├── ExpenseController.java
│   ├── FeedbackController.java
│   └── ExpenseEstimatorController.java
├── entity/              # JPA Entities (7 files)
│   ├── User.java
│   ├── Patient.java
│   ├── Doctor.java
│   ├── Disease.java
│   ├── Hospital.java
│   ├── Expense.java
│   └── Feedback.java
├── repository/          # Spring Data JPA Repositories
├── service/             # Business Logic Layer
├── security/            # JWT & Security Config
│   ├── JwtUtil.java
│   ├── JwtFilter.java
│   ├── SecurityConfig.java
│   ├── CustomUserDetails.java
│   └── CustomUserDetailsService.java
├── dto/                 # Data Transfer Objects
│   ├── LoginRequest.java
│   └── SignupRequest.java
└── util/
    └── ModelMapperConfig.java
```

## 📝 Notes

- The application uses `spring.jpa.hibernate.ddl-auto=update` which automatically creates/updates database tables
- Default server port is 8080
- All controllers have CORS enabled for development
- JWT secret should be changed in production
- Database credentials should be secured (use environment variables)

## 🚀 Future Enhancements

- Add UPDATE endpoints for all entities
- Implement pagination for list APIs
- Add input validation with Bean Validation
- Create custom exception handling
- Add unit and integration tests
- Implement role-based authorization
- Add API documentation with Swagger
- Create appointment scheduling system
- Add email notifications
- Generate PDF reports

## 👨‍💻 Author

**Abhay Gautam**
- GitHub: [@Eshuv](https://github.com/Eshuv)
- LinkedIn: [Abhay Gautam](https://www.linkedin.com/in/abhay-gautam-134140201/)
- Email: gautameshuv@gmail.com

## 🎓 Academic Project

Developed as part of PG-DAC course at C-DAC ACTS, Pune (August 2025 - February 2026)

---

⭐ If you find this project useful, please consider giving it a star!
