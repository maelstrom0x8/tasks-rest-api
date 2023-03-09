# Rhine-API
The Rhine-API is a RESTful API built with Spring Boot that serves as a resource server in the OAuth2 flow. It allows clients to access and manipulate task items for authenticated users. The API manages a collection of task list and for each list, their respective tasks.

## Getting Started
### Prerequisites
* JDK 18 or later
* Gradle 7.5.1 (for Maven, 3.8.6) or later
* PostgreSQL 14.5 or later

### Installing
#### Gradle
1. Clone the repository: `https://github.com/maelstrom0x8/rhine-api.git`
2. Change into the project directory: `cd rhine-api`
3. Start the application: `./gradlew bootRun`

#### Maven
1. Clone the repository: `https://github.com/maelstrom0x8/rhine-api.git`
2. Change into the project directory: `cd rhine-api`
3. Start the application: `mvn clean install`
4. Start the application: `mvn spring-boot:run`

The application should now be running on `http://localhost:8080`.

Configuration
The application requires the following environment variables to be set:

* `SPRING_DATASOURCE_URL`: the URL of the PostgreSQL database
* `SPRING_DATASOURCE_USERNAME`: the username of the PostgreSQL user
* `SPRING_DATASOURCE_PASSWORD`: the password of the PostgreSQL user
* `AUTH_SERVER_URL`: the URL of the OAuth2 authorization server
* `AUTH_SERVER_CLIENT_ID`: the client ID registered with the authorization server
* `AUTH_SERVER_CLIENT_SECRET`: the client secret registered with the authorization server

## API Reference
### Authentication
The Rhine-API uses OAuth2 for authentication. Clients must obtain an access token from the authorization server and include it in the `Authorization` header of each request to the API.

The access token should be obtained using the `authorization_code` grant type.

### Endpoints
All endpoints are accessed via the base URL: `http://localhost:8080/api`.

The API exposes the following endpoints:

* `GET /list` : retrieve all lists for the authenticated user.
* `POST /list?=title`: creates a list with the `title` for the authenticated user.