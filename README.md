# Microservices Budget Tracking Project

• Budget Tracking System is a money management platform where users can track their income and expenses , 
categorize their spending , and generate reports about their financial helath.

• This repository provide a hands-on understanding microservices architecture using spring boot3 by 
building CRUD APIs for the backend, implementing three microservices utilizing OpenFeign as an inter-service communication and applying some Spring Cloud technologies.

• Technologies used: Spring boot3, Spring Data JPA, API Gateway, Config Server, Discovery Server, REST client OpenFeign, PostgreSQL , Docker.

## Table of Contents
- [Functional Requirements](#Functional-Requirements)
- [Architecture Design](#Architecture-Design)
- [UML Diagrams](#UML-Diagrams)
- [Postman](#Postman)

## Functional Requirements
- Users can register and create accounts.
- Users have full control on their budgets.
- The system allows user to add income and expences transactions.
- Users can track the spending progress of the targets they created.
- Users can view the transaction history and filter transactions by wallet account.
- The system offers basic reporting features (e.g., income vs. expenses by month) and filter by date or wallet account.

## Architecture Design
![Arch](https://github.com/SamaMostafa03/Budget-Tracking-System/blob/main/Arch.PNG?raw=true)

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

### OpenFeign

This project demonstrates inter-service communication using OpenFeign, a declarative REST client that simplifies service-to-service communication within the microservices ecosystem.

## UML Diagrams
- Use Case Diagram:
![usecase](https://github.com/SamaMostafa03/Budget-Tracking-System/blob/main/Images/usecase.PNG?raw=true)

- Class Diagram:
![class](https://github.com/SamaMostafa03/Budget-Tracking-System/blob/main/Images/class.PNG?raw=true)

## Postman
