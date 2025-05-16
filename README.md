# Introduction
The Health Information System is a lightweight web application designed to help doctors manage health programs,
register clients, enroll clients into health programs, and retrieve client profiles via an API.

## Tech stack
- Java 17
- Spring Boot 3
- Thymeleaf
- Spring Security
- Tailwind CSS
- MySQL Database

# Features
 - Create a Health Program (e.g., TB, Malaria, HIV)
 - View a list of health programs
 - Register a new client
 - Enroll a client into one or more programs
 - View a list of registered clients
 - View a client's profile and their enrolled programs
 - Expose client profiles via an API
 - Login authentication for doctors
 - Graceful error handling with user-friendly messages

# Getting Started
## Prerequisites
- Java 17
- Maven
- MySQL
- IDE (e.g. Intellij IDEA)

## Running Locally
1. Clone the repository
```git clone https://github.com/Nderitu-Dennis/cema-health-system.git```
2. Create a MySQL database called ```cemadb```
3. Configure your ```application.properties``` with your database credentials
4. Build and run the application. 
   ```./mvnw spring-boot:run```
5. Access the app at: ```http://localhost:8080```

# API Endpoints
| Endpoint                        | Method | Description |
|---------------------------------|--------|-------------|
| ```/clients/api/profile/{id}``` | GET | Fetch a client's profile by ID|

# Security
The system is secured using Spring Security. Doctors must log in before accessing any functionality.
- **Username:**```doctor```
- **Password:**```password123```

## Login page:
```http://localhost:8080/login```

# Deployment
This prototype is deployed on an AWS EC2 instance, leveraging an idle instance that I had available. Access the live app at:
```http://13.53.148.78:8080/```. 

 **NOTE:** This is a   prototype deployment; the URL above will point to the live application once the instance is running,
and it uses HTTP instead of HTTPS because security features have not yet been implemented.

# Presentation
A PowerPoint presentation outlining the approach, design, and solution can be found in the ```docs/``` folder.
 
# Conclusion
This solution successfully enables doctors to create health programs, register and enroll clients, search for clients, view their profiles, and expose client profiles via an API.

# UPDATE- After all this work, unfortunately i got rejected.





