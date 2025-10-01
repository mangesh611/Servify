# Servify
A smart and fast solution for your day to day services
# 🛠️ Servicer - On-Demand Service Provider Platform

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen.svg)](https://spring.io/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

> A Rapido/Uber-like platform connecting users with nearby service providers (plumbers, electricians, etc.) in real-time.

## 🚀 Overview

Servicer is a comprehensive service booking platform that enables users to find and book nearby service providers. The system automatically notifies available providers within the user's vicinity, creating a seamless on-demand service experience.

## ✨ Features

### 🔐 Authentication & Security
- JWT-based authentication
- Role-based access control (USER, PROVIDER, ADMIN)
- Password encryption with BCrypt
- CORS configuration for frontend integration

### 👥 User Management
- User registration and profile management
- Service provider registration
- Role-based dashboards

### 🔍 Service Discovery
- Service catalog management
- Provider search and filtering
- **📍 Nearby provider matching** (Upcoming feature)

### 📅 Booking System
- Service booking with multiple statuses (PENDING, CONFIRMED, COMPLETED, CANCELLED)
- Real-time booking management
- Booking history and tracking

### 💳 Payment Integration
- Payment processing system
- Multiple payment status tracking
- Payment history

### ⭐ Review & Rating System
- Service reviews and ratings (1-5 stars)
- Provider performance tracking
- Service quality metrics

## 🛠️ Technology Stack

### Backend
- **Java 21** - Primary programming language
- **Spring Boot 3.5.6** - Application framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Database operations
- **Hibernate** - ORM implementation

### Database
- **MySQL 8.0** - Primary database
- **Flyway** - Database migrations
- **HikariCP** - Connection pooling

### API & Documentation
- **RESTful APIs** - Clean API design
- **OpenAPI 3** - API documentation
- **Swagger UI** - Interactive API testing

### Security
- **JWT** - Stateless authentication
- **BCrypt** - Password hashing
- **CORS** - Cross-origin resource sharing

## 📁 Project Structure
servicer/
├── src/main/java/com/example/servicer/
│ ├── controller/ # REST API controllers
│ ├── service/ # Business logic layer
│ ├── repository/ # Data access layer
│ ├── entity/ # JPA entities
│ ├── dto/ # Data Transfer Objects
│ ├── security/ # JWT and security config
│ ├── config/ # Application configurations
│ └── exception/ # Custom exception handling
├── src/main/resources/
│ ├── db/migration/ # Flyway migrations
│ └── application.properties
└── pom.xml # Maven dependencies


## 🗄️ Database Schema

### Core Entities
- **Users** - User accounts and authentication
- **ServiceProviders** - Service provider profiles
- **Services** - Service catalog
- **Bookings** - Service booking records
- **Payments** - Payment transactions
- **Reviews** - Customer reviews and ratings

### Key Relationships
- One-to-One: User ↔ ServiceProvider
- Many-to-Many: ServiceProvider ↔ Services
- One-to-Many: User → Bookings
- One-to-One: Booking ↔ Payment ↔ Review

## 🚀 Getting Started

### Prerequisites
- Java 21 or higher
- MySQL 8.0+
- Maven 3.6+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/servicer.git
   cd servicer


   # Create MySQL database
CREATE DATABASE servicer_db;

# Update application.properties with your credentials
spring.datasource.url=jdbc:mysql://localhost:3306/servicer_db
spring.datasource.username=your_username
spring.datasource.password=your_password

mvn spring-boot:run

Access the application

API Base URL: http://localhost:8080

Swagger UI: http://localhost:8080/swagger-ui.html

Health Check: http://localhost:8080/actuator/health


📚 API Documentation
Authentication Endpoints
POST /api/auth/register - User registration

POST /api/auth/login - User login

Service Endpoints
GET /api/services - List all services

POST /api/services - Create new service (Admin only)

Provider Endpoints
GET /api/providers - List all providers

POST /api/providers/register - Register as provider

Booking Endpoints
POST /api/bookings - Create booking

GET /api/bookings - Get user bookings

Payment Endpoints
POST /api/payments - Process payment

🔧 Configuration

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/servicer_db
spring.datasource.username=root
spring.datasource.password=yourpassword

# JWT
app.security.jwt.secret=your-jwt-secret-key-here
app.security.jwt.expiration-ms=3600000

# CORS
app.cors.allowed-origins=http://localhost:3000
