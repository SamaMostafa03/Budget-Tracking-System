# Microservices Budget Tracking Project

• Budget Tracking System is a money management platform where users can track their income and expenses , 
categorize their spending , and generate reports about their financial helath.

• This repository demonstrates a real-world microservices architecture focusing on secure communication, secure API design, centralized configuration, clean separation of concerns and reusable Spring Boot starter modules.

• Technologies used: Spring boot3, Spring Security(JWT, OAuth2), Spring Data JPA, Spring Cloud (API Gateway, Config Server, Netflix Eureka, OpenFeign), PostgreSQL , Spring AOP, Docker.

## Key Features
- Users can register and authenticate using email/password or Google OAuth2.
- Secure access to APIs using JWT authentication.
- Users can create and manage wallets.
- Users can create categories (e.g. Bills, Loans).
- Each category can contain multiple budgets (e.g. Rent, Water Bills).
- Users can add income and expense transactions.
- Users can track the spending progress of the budgets they created.
- Automatic wallet and budget updates on each expense transaction.
- Transaction history with filtering by date, wallet, or category.
- Stateless microservices architecture with API Gateway and service discovery.
- Centralized configuration management for all services.
- Inter-service communication using OpenFeign.
- Cross-cutting concerns extracted into reusable Spring Boot starter modules.

## Architecture Design
![Arch](https://github.com/SamaMostafa03/Budget-Tracking-System/blob/main/Images/Arch.png?raw=true)

### Discovery Server

Discovery Server provides service registration and discovery via “Spring Cloud Netflix Eureka.”, enabling seamless service-to-service communication within the microservices ecosystem.

### API Gateway

API Gateway serves as the single entry point for all user requests, managing and routing them to the appropriate microservices.

### Config Server

Config Server centralizes configuration management for all microservices, simplifying application maintenance and consistency across environments.

### OpenFeign
OpenFeign is a declarative REST client used for inter-service communication between: Transaction ↔ Wallet and Transaction ↔ budget

### User Microservice
Responsible for:
- User account management
- Registration and authentication
- JWT and refresh token generation
- OAuth2 Google login

### Budget Microservice
Responsible for:
- Category management
- Budget creation and tracking

### Wallet Microservice
Responsible for:
- Wallet creation and management for each user

### Transaction Microservice
Responsible for:
- Recording income and expense transactions
- Automatically updating wallet balances 
- Automatically updating budget spending
- Filtering transaction history.

### Security & Authentication
- JWT-based authentication for secured REST APIs
- Refresh tokens for session continuity
- Logout & token revocation
- OAuth2 Google Login
- JWT validation handled at API Gateway level

### Cross-Cutting Concerns
- Logging (execution time & exceptions)
- Global exception handling
- Shared DTOs
These concerns are extracted into custom reusable Spring Boot starter modules and reused across all microservices.

## Postman
[Postman-Collection-URL](https://www.postman.com/sama-03/budget-tracking-system/collection/apwbvc3/budget-tracking-system?action=share&creator=34266999)

