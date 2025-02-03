# Event Registration System with Visitor Badge Generation

## Introduction

This Event Registration System is a Spring Boot-based application designed to facilitate event registrations. The application allows users to fill out a registration form with their details, including a photo upload, and generates a visitor badge containing their photo, name, and a "Visitor" label. The application validates form inputs, stores the data in a PostgreSQL database, and generates the badge either for download as a PDF or for viewing on the browser.

## Prerequisites

Before running the application locally, ensure you have the following installed:

- **Java Development Kit (JDK 1.8)**: The application is configured to use JDK 1.8. Ensure it's installed and properly configured in your environment.
- **PostgreSQL**: Install and configure PostgreSQL for the database.
- **Apache Maven**: Required for building and managing dependencies for the project.
- **An IDE or text editor** (e.g., IntelliJ IDEA, Eclipse, or VS Code).
- **Postman** (for API testing).

## Dependencies

The project uses the following dependencies, managed through Maven:

- **Spring Boot Starter Web**: For building REST APIs.
- **Spring Boot Starter Data JPA**: For database interaction.
- **PostgreSQL Driver**: To connect the Spring Boot application to a PostgreSQL database.
- **Spring Boot Starter Validation**: For validating form inputs.
- **Spring Boot DevTools**: For hot reloading during development.
- **iTextPDF (version 5.5.13.3)**: For generating visitor badges in PDF format.

### Spring Boot Version

This application uses **Spring Boot version 2.7.10**.

These dependencies are defined in the `pom.xml` file, and Maven will automatically download them during the build process.

## How to Run the Application Locally

### 1. Clone the Repository

First, clone the GitHub repository to your local machine:

```bash
git clone <repository-link>
cd <repository-directory>
```

### 2. Configure the Database

1. Open PostgreSQL and create a database for the application:

   ```sql
   CREATE DATABASE EventRegistration;
   ```

2. Update the `application.properties` file located in the `src/main/resources` directory with your PostgreSQL credentials:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/EventRegistration
   spring.datasource.username=<your_username>
   spring.datasource.password=<your_password>

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true

   spring.servlet.multipart.enabled=true
   spring.servlet.multipart.max-file-size=5MB
   spring.servlet.multipart.max-request-size=10MB
   spring.web.resources.add-mappings=true
   ```

### 3. Build the Project

Use Maven to build the project. Run the following command in the root directory of the project:

```bash
mvn clean install
```

This will compile the code, resolve dependencies, and package the application.

### 4. Run the Application

Run the application using your IDE:

- **Using IDE:** Open the project in your IDE, locate the main class (`com.example.EventRegistrationApplication`), and run it.

The application will start on `http://localhost:8080` by default.

### 5. Access the Application

Open your browser and navigate to:

```
http://localhost:8080
```

You should see the event registration form.

### 6. Test the Application

- Fill out the registration form with the required details.
- Upload a photo in JPG/PNG format (up to 5MB).
- Submit the form to generate and view/download the visitor badge.

### 7. Folder for Uploaded Photos

Uploaded photos will be stored in a directory within the project (e.g., `uploads/`).
Ensure this directory exists and has appropriate permissions.

### 8. Shutdown

To stop the application, press `Ctrl+C` in the terminal where it is running.

## Notes

- Ensure that the `uploads/` folder exists in the root directory or the specified path in the code.
- Modify the `application.properties` file as per your PostgreSQL configuration.
- For troubleshooting, refer to the logs in the terminal or IDE console.

## Additional Information

If you encounter issues, verify that all required dependencies are properly configured in the `pom.xml` file and ensure that your JDK version matches the project's requirements.
