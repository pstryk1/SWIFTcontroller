# SWIFT Controller

SWIFT Controller is a REST API application that provides access to a database of SWIFT codes. The application is containerized using Docker and supports CRUD operations for managing SWIFT codes.

## Prerequisites

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

## Installation and Running the Application

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/pstryk1/SWIFTcontroller.git
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
   ```json
   {
    "address": string,
    "bankName": string,
    "countryISO2": string,
    "countryName": string,
    "isHeadquarter": bool,
    "swiftCode": string
    “branches”: [
      {
      "address": string,
      "bankName": string,
      "countryISO2": string,
      "isHeadquarter": bool,
      "swiftCode": string
      },
      {
      "address": string,
      "bankName": string,
      "countryISO2": string,
      "isHeadquarter": bool,
      "swiftCode": string
      }, . . .
   ]
   }
   ```
Response Structure for branch swift code: 
   ```json
   {
       "address": string,
       "bankName": string,
       "countryISO2": string,
       "countryName": string,
       "isHeadquarter": bool,
       "swiftCode": string
   }
   ```
2. Get SWIFT Codes for a Specific Country
   Endpoint: GET /v1/swift-codes/country/{countryISO2code}
   
   Description: Retrieves details for a specific country along with a list of all SWIFT codes (both headquarters and branches) associated with that country.
   
   Example Response:

   ```json
   
      {
          "countryISO2": string,
          "countryName": string,
          "swiftCodes": [
              {
                  "address": string,
          		 "bankName": string,
          		 "countryISO2": string,
          		 "isHeadquarter": bool,
          		 "swiftCode": string
              },
              {
                  "address": string,
          		 "bankName": string,
          		 "countryISO2": string,
          		 "isHeadquarter": bool,
          		 "swiftCode": string
              }, . . .
          ]
      }
   ```
3. Add a New SWIFT Code
   Endpoint: POST /v1/swift-codes
   
   Description: Adds a new SWIFT code entry to the database for a specific country.
   
   Request Body:
      ```json
   {
       "address": "UL. EXAMPLE 1, WARSAW",
       "bankName": "EXAMPLE BANK",
       "countryISO2": "PL",
       "countryName": "POLAND",
       "isHeadquarter": true,
       "swiftCode": "EXAMPLEBANKXXX"
   }
      ```
   Response:
   ```json
   {
    "message": string,
   }
   ```
4. Delete a SWIFT Code
Endpoint: DELETE /v1/swift-codes/{swift-code}

Description: Deletes the SWIFT code entry from the database that matches the provided SWIFT code.

Response:
```json
{
    "message": "SWIFT code deleted successfully."
}
```

Author
Patryk Kusper
   
   
   

