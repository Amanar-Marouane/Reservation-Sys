package services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import enums.Roles;
import interfaces.AuthInterface;
import models.User;
import utils.Console;
import utils.Validator;

public class AuthService implements AuthInterface {
    private User loggedInUser;
    private boolean isLogged;

    public boolean register(Map<String, User> users, String fullName, String email, String password) {
        if (this.isLogged) {
            Console.warning("Logout First!!");
            return false;
        }

        Console.info("Processing registration...");
        if (!Validator.isValidEmail(email)) {
            Console.error("Registration failed: Invalid email.");
            return false;
        }

        if (!Validator.isValidFullName(fullName)) {
            Console.error("Registration failed: Invalid full name.");
            return false;
        }

        if (!Validator.isValidPassword(password)) {
            Console.error("Registration failed: Invalid password.");
            return false;
        }

        if (users.containsKey(email)) {
            Console.error("Registration failed: Email already exists.");
            return false;
        }

        User user = new User(UUID.randomUUID(), fullName, email, password, Roles.USER);
        users.put(email, user);
        Console.success("Registration successful!");
        return true;
    }

    public boolean login(Map<String, User> users, String email, String password) {
        if (this.isLogged) {
            Console.warning("You Already Logged In, Logout First!!");
            return false;
        }
        Console.info("Processing login...");
        if (!Validator.isValidEmail(email)) {
            Console.error("Login failed: Invalid email.");
            return false;
        }
        User user = users.get(email);
        if (user == null) {
            Console.error("Login failed: User not found.");
            return false;
        }

        if (!user.getPassword().equals(password)) {
            Console.error("Login failed: Incorrect password.");
            return false;
        }

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

    public HashMap<String, String> registerAttempt() {
        HashMap<String, String> registry = new HashMap<>();

        registry.put("email", this.emailAttempt());
        registry.put("fullName", this.fullNameAttempt());
        registry.put("password", this.passwordAttempt());

        return registry;
    }

    public HashMap<String, String> loginAttempt() {
        HashMap<String, String> registry = new HashMap<>();

        registry.put("email", this.emailAttempt());
        registry.put("password", this.passwordAttempt());

        return registry;
    }

    private String emailAttempt() {
        String email;
        do {
            email = Console.ask("=> Enter an email");
            if (!Validator.isValidEmail(email))
                Console.error("Invalid Email");
        } while (!Validator.isValidEmail(email));
        return email;
    }

    private String fullNameAttempt() {
        String fullName;
        do {
            fullName = Console.ask("=> Enter a full name");
            if (!Validator.isValidFullName(fullName))
                Console.error("Invalid full name");
        } while (!Validator.isValidFullName(fullName));
        return fullName;
    }

    private String passwordAttempt() {
        String password;
        do {
            password = Console.ask("=> Enter a password");
            if (!Validator.isValidPassword(password))
                Console.error("Invalid password");
        } while (!Validator.isValidPassword(password));

        return password;
    }
}
