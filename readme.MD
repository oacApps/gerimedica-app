# README for CSV Management Application
Welcome to the CSV Management Application!

This application is designed to allow users to upload CSV files, fetch data, and manage entries using a RESTful API. It uses Java with the Spring framework and an in-memory H2 database to handle data operations.

Here's a brief overview of the implementation and usage:

# Code Overview
## Main Components
- Controller: Handles incoming HTTP requests and directs them to the appropriate service methods.
- Service: Contains business logic for processing CSV files and interacting with the repository.
- Repository: Manages CRUD operations with the in-memory database.

## Limitations
Due to the time constraints of the test, some aspects may be missing:

- Validation: Minimal validation is provided; more robust checks could be added.
- Exception Handling: Basic error handling is implemented; more comprehensive exception handling could be added.
- Unit Tests: Basic unit tests are included; additional tests could be implemented for full coverage.

# Conclusion
This application showcases the capability to manage CSV data using a REST API, Java, and Spring Boot with an in-memory H2 database. Despite the time constraints, the core functionality is operational and demonstrates the ability to handle CSV file uploads and data management effectively.

I hope you find this implementation valuable and appreciate the opportunity to present my solution. Please feel free to reach out with any questions or feedback!

# Project Overview
## Features

- Upload Data: Allows users to upload CSV data which is stored in an in-memory H2 database.
- Fetch All Data: Retrieves all data stored in the database.
- Fetch by Code: Retrieves data by a unique code field.
- Delete All Data: Clears all data from the database.

## Technologies Used
- Java: The primary programming language.
- Spring Boot: Framework used for building the REST API.
- H2 Database: In-memory database for storing data.
- Spring Data JPA: Simplifies database operations with JPA.

## Endpoints
Base url: http://localhost:8080/gerimedica
- Upload CSV
  - URL: /upload
  - Method: POST
  - Request Body: Form-data with file upload (CSV file).
  - Response: Success or error message.

- Fetch All Data
  - URL: 
  - Method: GET
  - Response: JSON array of all records.

- Fetch by Code
  - URL: /{code}
  - Method: GET
  - Response: JSON object of the record with the specified code.

- Delete All Data
  - URL: /delete
  - Method: DELETE
  - Response: Success or error message.

