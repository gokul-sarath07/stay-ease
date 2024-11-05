# StayEase

## Problem Statement
Develop and deploy a RESTful API service using Spring Boot to streamline the room booking process for a hotel management aggregator application. MySQL is used for data persistence.

## Key Features
This is a simplified online room booking system focusing on core functionalities. Here are the assumptions and requirements:

- **Assumptions**:
    - The application has a single type of room, and all bookings are for two guests.
    - Any hotel manager can update hotel details without tracking which manager oversees a specific hotel.
    - Another service handles check-in and check-out functionalities.

- **Authentication and Authorization**:
    - JWT tokens are used for stateless authentication.
    - The service defines three roles: `CUSTOMER`, `HOTEL MANAGER`, and `ADMIN`.

- **API Endpoints**:
    - **Public Endpoints**: Accessible to anyone (e.g., Registration, Login).
    - **Private Endpoints**: Accessible only to authenticated users (e.g., Book a room).

> **Note**: Design choices for database schema, resource access based on roles, etc., require thoughtful implementation.

## API Features

### User Registration and Login
- Users can register by providing:
    - **Email**
    - **Password** (hashed and stored with BCrypt)
    - **First Name**
    - **Last Name**
    - **Role** (defaults to “Customer” if not specified)

- Upon successful registration or login, a JWT token is generated for the user.

### Hotel Management
- **Hotel Details**: Store and manage hotel information with fields:
    - **Hotel Name**
    - **Location**
    - **Description**
    - **Number of Available Rooms** (indicates booking availability)

- **Public Access**:
    - Anyone can browse all available hotels.

- **Admin-Only Access**:
    - Only administrators can create and delete hotels.

- **Manager Access**:
    - Hotel managers can update hotel details.

### Booking Management
- **Customer Bookings**:
    - Customers can book rooms, one room per request.

- **Booking Cancellation**:
    - Only hotel managers can cancel bookings.

## Roles & Access Control
- **CUSTOMER**: Can book rooms.
- **HOTEL MANAGER**: Can update hotel details and cancel bookings.
- **ADMIN**: Can create and delete hotels.

## Tech Stack
- **Backend**: Spring Boot (Java)
- **Database**: MySQL
- **Authentication**: JWT (JSON Web Tokens) for stateless security
- **Password Security**: BCrypt hashing for secure password storage

## Running the Application

### Live

- The application is live and accessible at [https://stay-ease-6m1p.onrender.com](https://stay-ease-6m1p.onrender.com).
-  Use the provided Postman collection to interact with the API.

### Local

#### Steps to Build & Run

1. **Clone the Repository**: Clone the repository to your local machine.
    ```bash
    git clone https://github.com/gokul-sarath07/stay-ease.git
    cd stay-ease
    ```

2. **Configure the Database**:
    - Update the `application.properties` or `application.yml` file with your MySQL database credentials.


3. **Build the Project**:
    - Using Gradle:
    ```bash
    ./gradlew build
    ```

4. **Run the Application**:
    - After building the project, run the JAR file:
   ```bash
   java -jar build/libs/stayEase-0.0.1-SNAPSHOT.jar
   ```