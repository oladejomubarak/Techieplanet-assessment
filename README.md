# Student Management Spring Boot Project

This is a Spring Boot application for managing students, their subjects, and scores. The project includes API endpoints for creating students and generating reports on their performance.
The project was designed by Mubarak Oladejo Adeshina, email: oladejomubarakade@gmail.com. The application uses Java 17, Spring Boot 3.2.1, and follows Test-Driven Development (TDD) principles with JUnit 5.9.3 for testing functionality.


## Table of Contents
- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- - [Installing dependecies](#installing dependencies)
- [Running the Application](#running-the-application)
- [Testing](#Testing)
- [API Documentation](#api-documentation)

## Getting Started

These instructions will help you set up and run the project on your local machine for development and testing purposes.

### Prerequisites

- **Java 17** or later
- **Maven** (for building the project)
- **Docker** (optional, for containerized deployment)
- **Postman** or **cURL** (for testing APIs)
- **Git** (for version control)

### Installation

Clone the repository to your local machine:

To get started, clone this repository from GitHub using the following command on your terminal:

git clone https://github.com/oladejomubarak/Techieplanet-assessment.git

### Install Dependencies
Navigate to the project directory and install the necessary dependencies using Maven:

Windows (Run the following commands on your terminal):
- cd techieplanet
- mvn clean install

macOS/Linux (Run the following commands on your terminal):
- cd techieplanet
- ./mvnw clean install

Make sure your system is connected to the internet as Maven will download required dependencies.

### Running the Application
You can run the Spring Boot application using the following Maven command on your terminal:

Windows:
- mvn spring-boot:run

macOS/Linux:
- ./mvnw spring-boot:run

You can also run the Student Score Report Backend using popular Integrated Development Environments (IDEs) like IntelliJ IDEA and Eclipse.

The application will start locally on port 8080.

## Testing
All functionalities in this application have been tested using JUnit 5.9.3. Integration testing was also provided.
To run the tests, you can use the following Maven command:
The test files can be found inside the test directory.

Windows:
- mvn test

macOS/Linux:
- ./mvnw test

## API Documentation
The API provides two endpoints, one for collecting student details and score, the other is for generating student score report by supplying the student's ID.
Below is the API request and response sample:
1. Creating Student and scores in each subject
- Endpoint: http://localhost:8080/core/techieplanet/students/create
- Method: POST
- Request Body: JSON object with three properties: firstName, lastName and subjectScores.
- Example Request Body:
  {
  "firstName": "John",
  "lastName": "Doe",
  "subjectScores": {
  "Mathematics": 85,
  "English": 90,
  "Science": 78,
  "History": 76,
  "Art": 90
  }
  }

ApiResponse

The application's RESTful API returns responses in the form of an `ApiResponse` object. Below is the sample of a successful response:
{
"code": 200,
"status": true,
"message": "SUCCESSFUL",
"error": null,
"data": 3
}
The value of data in the above response is the student's Id, which will be used to test the second endpoint.

2. Generating score report for already created student
- Endpoint: http://localhost:8080/core/techieplanet/students/report?id=3
- Method: GET
 Example of API response:
  {
  "code": 200,
  "status": true,
  "message": "SUCCESSFUL",
  "error": null,
  "data": {
  "firstName": "John",
  "lastName": "Doe",
  "subjectScores": {
  "English": 90.0,
  "Art": 90.0,
  "Science": 78.0,
  "Mathematics": 85.0,
  "History": 76.0
  },
  "meanScore": 83.8,
  "medianScore": 85.0,
  "modeScores": [
  90.0
  ]
  }
  }