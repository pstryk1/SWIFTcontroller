# SWIFT Controller

SWIFT Controller is a REST API application that provides access to a database of SWIFT codes. The application is containerized using Docker and supports CRUD operations for managing SWIFT codes.

## Prerequisites

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

## Installation and Running the Application

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/YourUsername/SWIFTcontroller.git
   cd SWIFTcontroller
2. **Run the Application with Docker Compose:**
   ```bash
   docker-compose up --build

4. **Access the Application: Once the containers are running, the API is available at:**
   ```bash
   http://localhost:8080
   
# API Endpoints
1. Get a Single SWIFT Code
Endpoint: GET /v1/swift-codes/{swift-code}

Description: Retrieves details of a single SWIFT code. If the code represents a headquarter, the response includes a list of its branches.

Example Response (for a headquarter):
