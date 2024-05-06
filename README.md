# Microservices Budget Tracking Project

This repository contains an API Gateway, Config Server, Discovery Server, and three microservices: User, Budget and Transaction

## Table of Contents

- [Project Components](#project-components)
    - [API Gateway](#api-gateway)
    - [Config Server](#config-server)
    - [Discovery Server](#discovery-server)
    - [User Microservice](#user-microservice)
    - [Budget Microservice](#budget-microservice)
    - [Transaction Microservice](#transaction-microservice)
  
## Project Components

### API Gateway

The API Gateway serves as the single entry point for all user requests, managing and routing them to the appropriate microservices.

### Config Server

The Config Server centralizes configuration management for all microservices, simplifying application maintenance and consistency across environments.

### Discovery Server

The Discovery Server provides service registration and discovery via “Spring Cloud Netflix Eureka.”, enabling seamless service-to-service communication within the microservices ecosystem.

### User Microservice

The User Microservice is responsible for user management and authentication. 
It ensures robust user authentication and administrative control over user accounts and system information.

### Budget Microservice

The Budget Microservice is responsible for managing
financial targets-related data and operations such as creating, 
updating, and deleting targets, while also tracking spend, 
assignments, and total required funds for a given period of time.

### Transaction Microservice

The Transaction Microservice is responsible for managing transaction-related data and operations, such as adding, updating, and retrieving transactions records.

