# pfms-user-management

A Spring Boot-based microservice for managing users, providing user registration, authentication, and role-based access control. It is built using modern Spring Boot practices and uses a MySQL database for data persistence.

---

## 🛠️ Tech Stack

- **Spring Boot 3.3.4**
- **Spring Data JPA**
- **Spring Security + OAuth2 Client**
- **JWT (JSON Web Token)**
- **MySQL**
- **OpenAPI (Swagger UI)**
- **Lombok**

---

## 📁 Project Structure

- `src/main/java`: Java source files (controllers, services, models, configs).
- `src/main/resources`: Application properties and resources.
- `db.sql`: SQL script to create and initialize the MySQL database schema.

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/darshafonseka/pfms-user-management.git
cd pfms-user-management
````

### 2. Set Up the Database

Ensure MySQL is running and accessible. Then run the `db.sql`(resources/database/db.sql) file using a MySQL client:

```sql
CREATE DATABASE pfms_auth_service;
USE pfms_auth_service;

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  role ENUM('USER', 'ADMIN') DEFAULT 'USER',
  status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

### 3. Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

---

## 🔐 Security

* Role-based access control using Spring Security.
* JWT for stateless authentication.
* OAuth2 client support (configure if using third-party logins like Google).

---

## 📘 API Documentation

Once the application is running, open:

```
http://localhost:8081/swagger-ui/index.html
```

To explore available endpoints.

---


## 📄 License

This project is licensed under the MIT License.



