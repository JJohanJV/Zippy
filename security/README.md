# Proyecto-Login-CRUD

This is a Java project that implements a login system and CRUD (Create, Read, Update, Delete) operations. The project uses Spring Boot, Spring Security, and Spring Data JPA.

## Project Structure

The project follows a standard Maven project structure. The main code is located in the [`src/main/java/com/uis/entornos/proyectologincrud`]("src/main/java/com/uis/entornos/proyectologincrud") directory.

## Building the Project

To build the project, you can use the provided Maven wrapper:

```sh
./mvnw clean install
```

## Running the Project

After building the project, you can run it with:

```sh
./mvnw spring-boot:run
```

## Dependencies

The project uses several dependencies, including:

- Spring Boot Starter Data JPA for database operations
- Spring Boot Starter Security for authentication and authorization
- Spring Boot Starter Web for creating a web application
- Lombok to reduce boilerplate code

These dependencies are managed in the [`pom.xml`]("pom.xml") file.

## Ignored Files

The [`.gitignore`](".gitignore") file is set up to ignore certain files and directories that should not be committed to the repository. These include compiled class files, log files, package files, and certain Eclipse and Maven generated files.

## Authentication Service

The authentication service is implemented in the [`AuthServiceImpl.java`]("src/main/java/com/uis/entornos/proyectologincrud/Auth/AuthServiceImpl.java") file. This service handles the authentication logic for the application.
