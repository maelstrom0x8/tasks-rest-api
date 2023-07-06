# tasks-rest-api

A REST API built with Spring Boot to create tasks. The API manages a collection of task list and for each
list, their respective tasks.

## Getting Started

### Prerequisites

* Java
* Maven
* Docker

### Installing

1. Start the application: `./mvnw clean install`
2. Start the application: `./mvnw spring-boot:run`

The application should now be running on the default `http://localhost:8080`.

Configuration
The application requires the following environment variables to be set:

* `SPRING_DATASOURCE_URL`: the URL of the PostgreSQL database
* `SPRING_DATASOURCE_USERNAME`: the username of the PostgreSQL user
* `SPRING_DATASOURCE_PASSWORD`: the password of the PostgreSQL user
* `AUTH_SERVER_URL`: the URL of the OAuth2 authorization server
* `AUTH_SERVER_CLIENT_ID`: the client ID registered with the authorization server
* `AUTH_SERVER_CLIENT_SECRET`: the client secret registered with the authorization server

## API Reference

### Endpoints

All endpoints are accessed via the base URL: `http://localhost:8080/api`.

The API exposes the following endpoints:

* `GET /list` : retrieve all lists for the authenticated user.
* `POST /list?=title`: creates a list with the `title` for the authenticated user.
