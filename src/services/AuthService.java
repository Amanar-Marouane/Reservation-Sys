package src.services;

import src.enums.Roles;
import src.interfaces.AuthInterface;
import src.models.User;
import src.repositories.UserRepository;
import src.utils.Console;

public class AuthService implements AuthInterface {
    private User loggedInUser;
    private boolean isLogged;
    private static AuthService instance;
    private static UserRepository userRepository;

    private AuthService() {
        userRepository = UserRepository.getInstance();
    }

    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    public boolean register(String fullName, String email, String password) {
        if (this.isLogged) {
            Console.warning("You must log out first before registering a new account.");
            return false;
        }

        if (userRepository.find("email", email) != null) {
            Console.error("Registration failed: Email already exists.");
            return false;
        }

        User user = new User(fullName, email, password, Roles.USER);
        userRepository.save(user);
        Console.success("Registration successful!");
        return true;
    }

    public boolean login(String email, String password) {
        if (this.isLogged) {
            Console.warning("You are already logged in. Please log out first.");
            return false;
        }

        User user = userRepository.find("email", email);
        if (user == null) {
            Console.error("Login failed: User not found.");
            return false;
        }

        if (!user.getPassword().equals(password)) {
            Console.error("Login failed: Incorrect password.");
            return false;
        }

        // Set login state
        this.loggedInUser = user;
        this.isLogged = true;
        Console.success("Login successful!");
        return true;
    }

    public void logout() {
        Console.info("Processing logout...");
        this.loggedInUser = null;
        this.isLogged = false;
        Console.success("Logout successful!");
    }

    public User user() {
        return this.loggedInUser;
    }

    public boolean isLogged() {
        return this.isLogged;
    }
}
