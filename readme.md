# Social Media API Backend

## Overview

This repository contains the backend code for a social media application. The backend is developed in Java, utilizing the Javalin framework, and includes features for user account management and message handling. This API does not include a frontend interface; its primary purpose is to deliver a robust backend supporting functionalities such as user registration, login, message creation, retrieval, updating, and deletion. The application incorporates JUnit for testing, Maven for dependency management, and Mockito for mocking, and relies on an SQL database for data storage.

## Features

- **User Account Management:**
  - User Registration
  - User Login

- **Message Handling:**
  - Create Messages
  - Retrieve All Messages
  - Retrieve Messages by User
  - Update Messages
  - Delete Messages

## Technologies Used

- **Java:** The backend is developed in Java, a versatile and widely-used programming language.

- **Javalin:** This lightweight web framework for Java is used to build the RESTful API.

- **Maven:** The project uses Maven for easy management of dependencies and project build.

- **JUnit:** Unit testing is implemented using JUnit to ensure the reliability of the codebase.

- **Mockito:** Mockito is employed for effective mocking during testing, enhancing the test coverage.

- **SQL Database:** User account information and message data are stored in an SQL database for structured data storage.

## Getting Started

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/social-media-api-java.git
   cd social-media-api-java
   
2. **Build the Project
     ```bash
     mvn clean install
     
3. **Test the Project
   ```bash
   mvn test

## Requirements

## 1: The API should process new User registrations.

As a user, I should be able to create a new Account on the endpoint POST localhost:8080/register. The body will contain a representation of a JSON Account, but will not contain an account_id.

- The registration will be successful if and only if the username is not blank, the password is at least 4 characters long, and an Account with that username does not already exist. If all these conditions are met, the response body should contain a JSON of the Account, including its account_id. The response status should be 200 OK, which is the default. The new account should be persisted to the database.
- If the registration is not successful, the response status should be 400. (Client error)

## 2: The API should process User logins.

As a user, I should be able to verify my login on the endpoint POST localhost:8080/login. The request body will contain a JSON representation of an Account, not containing an account_id. In the future, this action may generate a Session token to allow the user to securely use the site. We will not worry about this for now.

- The login will be successful if and only if the username and password provided in the request body JSON match a real account existing on the database. If successful, the response body should contain a JSON of the account in the response body, including its account_id. The response status should be 200 OK, which is the default.
- If the login is not successful, the response status should be 401. (Unauthorized)


## 3: The API should process the creation of new messages.

As a user, I should be able to submit a new post on the endpoint POST localhost:8080/messages. The request body will contain a JSON representation of a message, which should be persisted to the database, but will not contain a message_id.

- The creation of the message will be successful if and only if the message_text is not blank, is under 255 characters, and posted_by refers to a real, existing user. If successful, the response body should contain a JSON of the message, including its message_id. The response status should be 200, which is the default. The new message should be persisted to the database.
- If the creation of the message is not successful, the response status should be 400. (Client error)

## 4: The API should retrieve all messages.

As a user, I should be able to submit a GET request on the endpoint GET localhost:8080/messages.

- The response body should contain a JSON representation of a list containing all messages retrieved from the database. It is expected for the list to simply be empty if there are no messages. The response status should always be 200, which is the default.

## 5: The API should retrieve a message by its ID.

As a user, I should be able to submit a GET request on the endpoint GET localhost:8080/messages/{message_id}.

- The response body should contain a JSON representation of the message identified by the message_id. It is expected for the response body to simply be empty if there is no such message. The response status should always be 200, which is the default.

## 6: The API should delete a message identified by a message ID.

As a User, I should be able to submit a DELETE request on the endpoint DELETE localhost:8080/messages/{message_id}.

- The deletion of an existing message should remove an existing message from the database. If the message existed, the response body should contain the now-deleted message. The response status should be 200, which is the default.
- If the message did not exist, the response status should be 200, but the response body should be empty. This is because the DELETE verb is intended to be idempotent, ie, multiple calls to the DELETE endpoint should respond with the same type of response.

## 7: The API should update a message text identified by a message ID.

As a user, I should be able to submit a PATCH request on the endpoint PATCH localhost:8080/messages/{message_id}. The request body should contain a new message_text values to replace the message identified by message_id. The request body can not be guaranteed to contain any other information.

- The update of a message should be successful if and only if the message id already exists and the new message_text is not blank and is not over 255 characters. If the update is successful, the response body should contain the full updated message (including message_id, posted_by, message_text, and time_posted_epoch), and the response status should be 200, which is the default. The message existing on the database should have the updated message_text.
- If the update of the message is not successful for any reason, the response status should be 400. (Client error)

## 8: The API should retrieve all messages written by a particular user.

As a user, I should be able to submit a GET request on the endpoint GET localhost:8080/accounts/{account_id}/messages.

- The response body should contain a JSON representation of a list containing all messages posted by a particular user, which is retrieved from the database. It is expected for the list to simply be empty if there are no messages. The response status should always be 200, which is the default.


##License
This project is licensed by Revature


