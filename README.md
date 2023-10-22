# Forum
Forum is a Java-based web platform built with [Spring Boot](https://spring.io/projects/spring-boot) that allows users to
participate in discussions, share their thoughts, and connect with like-minded individuals.

## Technologies
- Java
- Spring Boot
- Thymeleaf
- Spring Security
- Spring Data JPA
- HTML, CSS(Bootstrap), and JavaScript

## Setup Guide
- Clone this repository.
- Open the project with IntelliJ.
- Allow IntelliJ a moment to finish its setup.
- Open the terminal in IntelliJ and execute the following command:
```sh
./gradlew bootRun
```
- Once the server is up and running, open a web browser and navigate to `localhost:8080/`.
- Register an account, log in, and explore the various features of the application.

## Key Features
- User Registration and Authentication:
     - Users can create accounts by registering with their username, email, and password.
     - Registered users can log in securely to access the platform's features.
     - Users can safely log out of their accounts to protect their session.
- Post Creation and Management:
     - Registered users can create new posts, expressing their thoughts and ideas.
     - Users can edit and delete their own posts.
- Commenting on Posts:
     - Users can engage in discussions by adding comments to posts.
     - Comments can be edited or deleted by the comment owner.

## GIF Walkthrough
### User Registration and Authentication
<p>
<image src = "assets/user.gif" width = 900><br>
</p>

- Users can register with a username, password, and email.
- An error message is displayed if the username or email is already in use.
- An error message is shown if the input strings for password and Password Confirm do not match.
- During login, an error message is displayed if the login credential is incorrect.
- Upon successful login, the user is redirected to the main page.
- Users can log out by clicking the "Log out" button in the navigation bar.

### Post CRUD Operations
<p>
<image src = "assets/PostCRUD.gif" width = 900><br>
</p>

- When a user creates a post, it displays the username and timestamp.
- When a user edits a post, it shows the modified date.
- When a user deletes a post, a confirmation prompt is shown before deletion.
- Upon successful deletion of the post, the user is redirected to the main page.

### Comment CRUD Operations
<p>
<image src = "assets/CommentCRUD.gif" width = 900><br>
</p>

- When a user comments on a post, it shows the username and timestamp.
- When a user edits a comment, it displays the modified date.
- When a user deletes a comment, a confirmation prompt is shown before deletion.
- Upon successful deletion of the comment, the initial post is displayed with the comment removed.
- If the user is not logged in, they can view posts but cannot create comments.


## Contact
Elliott Larsen
* Email elliottlrsn@gmail.com
* GitHub [@elliottlarsen](https://github.com/elliottlarsen)
* LinkedIn [@elliottlarsen](https://www.linkedin.com/in/elliottlarsen)