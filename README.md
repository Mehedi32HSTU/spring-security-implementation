# Spring Security Implementation
## A spring boot application that shows how to implement role based access control. It has also dynamic menu permission based access control functionalities.
### Simple Spring boot project developed with:
1. Java 17
2. Spring Boot v3.3.0-SNAPSHOT
3. Spring Web
4. Spring Data JPA
5. Spring Security
6. Postgres database
7. Jwt based authorization
8. Maven

### What's new?
The application has implemented role based access control with dynamic and configurable menu permissions.

How to start:
1. Clone the repository to your local machine.
2. Update the project as maven project.
3. Create a database in postgres with name: `spring_jwt_security`
4. Update database credentials in the `application.properties` file.
5. Update default users (`ADMIN` and `USER`) properties.
6. Run the project.

## Notes :
1. Dependencies used:

   a. Spring Web

   b. Spring security

   c. postgresql

2. Default users (Admin and User) used for demonstration with following properties:
    ```
   com.javabeans.username.admin=default_admin
   com.javabeans.password.admin=admin_1234
   com.javabeans.firstname.admin=Admin
   com.javabeans.lastname.admin=Admin
   com.javabeans.email.admin=admin@admin.com

   com.javabeans.username.user=default_user
   com.javabeans.password.user=user_1234
   com.javabeans.firstname.user=User
   com.javabeans.lastname.user=User
   com.javabeans.email.user=user@user.com

    ```

