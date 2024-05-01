# Microservices Budget Tracking Project

This repository contains an API Gateway, Config Server, Discovery Server, and three microservices: User, Budget and Transaction

## Table of Contents

- [Project Components](#project-components)
    - [API Gateway](#api-gateway)
    - [Config Server](#config-server)
    - [Discovery Server](#discovery-server)
    - [Transaction Microservice](#transaction-microservice)


## Project Components

### API Gateway

The API Gateway serves as the single entry point for all client requests, managing and routing them to the appropriate microservices.

### Config Server

The Config Server centralizes configuration management for all microservices, simplifying application maintenance and consistency across environments.

### Discovery Server

The Discovery Server provides service registration and discovery, enabling seamless service-to-service communication within the microservices ecosystem.

### Transaction Microservice

The Transaction Microservice is responsible for managing transaction-related data and operations, such as adding, updating, and retrieving transactions records.
