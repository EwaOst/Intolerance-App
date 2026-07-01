# Food Intolerance API

A RESTful backend application built with **Java 21** and **Spring Boot** for managing food intolerances, products, and users.

The application helps users identify products based on their intolerance profiles and provides information about histamine levels in food products. It follows a layered architecture and exposes a documented REST API.

## Features

- User management
- Food product management
- Food intolerance management
- Histamine level classification
- Association between users and intolerances
- Request validation
- Global exception handling
- API documentation with Swagger/OpenAPI
- Unit testing of service layer

## Project Architecture

The application follows a layered architecture:

- **Controllers** – expose REST endpoints.
- **Services** – implement business logic.
- **Repositories** – manage database operations using Spring Data JPA.
- **Models** – represent domain entities.
- **Enums** – define histamine levels and other domain-specific values.
- **Exception Handling** – centralized error handling.
- **Configuration** – application configuration.

## Main Entities

- User
- Product
- Intolerance

## Technologies

- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Hibernate
- Spring Validation
- MySQL
- H2 Database
- Swagger / OpenAPI
- Lombok
- JUnit 5
- Mockito
- Spring Mail
- WebSocket
- JWT
- Maven

## API Documentation

The project integrates **Swagger/OpenAPI**, providing interactive API documentation where available endpoints can be explored and tested directly from the browser.

## Testing

The project includes unit tests using:

- JUnit 5
- Mockito

## Project Goal

The goal of this project was to develop a backend application for managing food intolerances and food products. The application demonstrates REST API development, layered architecture, entity relationships, validation, exception handling, API documentation, database persistence, and unit testing using the Spring ecosystem.
