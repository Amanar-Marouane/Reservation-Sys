# Hotel Reservation System

A Java-based console application for managing hotel reservations with features for both users and administrators.

## Architecture Overview

This application follows object-oriented design principles and implements several design patterns to ensure efficient and maintainable code.

### Singleton Design Pattern

A key architectural decision in this application is the use of the Singleton pattern for several core services and repositories. This pattern ensures that only one instance of a class exists throughout the application lifecycle.

#### Why Singleton for AuthService?

The AuthService is implemented as a Singleton for the following reasons:

- **Shared Login State**: Maintains a single source of truth for the current authentication state across the entire application
- **Menu Management**: Enables seamless transitions between login-required and public menus
- **Session Consistency**: Ensures the logged-in user context is consistently available wherever needed

```java
// Example of Singleton implementation in AuthService
public class AuthService implements AuthInterface {
    private static AuthService instance;
    
    private AuthService() { }
    
    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }
    
    // Rest of the service...
}
```

#### Why Singleton for Repositories?

All repository classes (UserRepository, HotelRepository, ReservationRepository) use the Singleton pattern because:

- **Shared Data Access**: Ensures all components access the same data collection
- **Object Reference Sharing**: Allows entities to be shared by reference across the application
- **Simplified Object Manipulation**: Makes it easier to update objects in one place and have those changes reflected everywhere since objects are shared by reference
- **In-Memory Data Consistency**: Maintains consistency for our in-memory data store without needing a database

## Getting Started

To run the application:

1. Compile the Java files: ```javac ./src/Main.java```
2. Run the Main class: ```java ./src/Main.java```
3. Default admin credentials:
   - Email: marouane@gmail.com
   - Password: mmMM00!!