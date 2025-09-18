package src.controllers;

import java.util.HashMap;

import src.models.User;
import src.repositories.UserRepository;
import src.services.AuthService;
import src.utils.Console;
import src.utils.Validator;

public class UserController {
    private AuthService auth = AuthService.getInstance();
    private UserRepository userRepository = UserRepository.getInstance();

    private HashMap<String, String> registerAttempt() {
        HashMap<String, String> registry = new HashMap<>();

        registry.put("email", this.emailAttempt());
        registry.put("fullName", this.fullNameAttempt());
        registry.put("password", this.passwordAttempt());

        return registry;
    }

    private HashMap<String, String> loginAttempt() {
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

    public void register() {
        Console.info("Processing registration...");

        HashMap<String, String> register = this.registerAttempt();
        String fullName = register.get("fullName");
        String email = register.get("email");
        String password = register.get("password");

        if (!Validator.isValidEmail(email)) {
            Console.error("Registration failed: Invalid email format.");
            return;
        }

        if (!Validator.isValidFullName(fullName)) {
            Console.error("Registration failed: Invalid full name format.");
            return;
        }

        if (!Validator.isValidPassword(password)) {
            Console.error("Registration failed: Password does not meet requirements.");
            return;
        }

        this.auth.register(fullName, email, password);
    }

    public void login() {
        Console.info("Processing login...");

        HashMap<String, String> login = this.loginAttempt();
        String email = login.get("email");
        String password = login.get("password");

        if (!Validator.isValidEmail(email)) {
            Console.error("Login failed: Invalid email format.");
            return;
        }

        this.auth.login(email, password);
    }

    public void logout() {
        this.auth.logout();
    }

    public void profile(User user) {
        Console.line();
        Console.success("=== Your Profile ===");
        Console.info("User ID   : " + user.getId());
        Console.info("Full Name : " + user.getFullName());
        Console.info("Email     : " + user.getEmail());
        Console.info("Role      : " + user.getRole());
        Console.line();
    }

    public void update(String key) {
        User u = this.auth.user();
        Console.line();
        Console.info("Updating your " + key + "...");

        switch (key.toLowerCase()) {
            case "email" -> {
                String oldEmail = u.getEmail();
                String email = this.emailAttempt();

                if (email.equals(oldEmail)) {
                    Console.warning("The new email is the same as your current one.");
                    return;
                }

                // Check if email already exists
                if (userRepository.find("email", email) != null) {
                    Console.error("This email is already registered. Please use a different one.");
                    return;
                }

                u.setEmail(email);
                userRepository.save(u);
                Console.success("Your email has been updated successfully.");
            }
            case "fullname" -> {
                String fullName = this.fullNameAttempt();
                u.setFullName(fullName);
                // userRepository.save(u);
                Console.success("Your full name has been updated successfully.");
            }
            case "password" -> {
                String password = this.passwordAttempt();
                u.setPassword(password);
                // userRepository.save(u);
                Console.success("Your password has been updated successfully.");
            }
            case "exit" -> {
                Console.info("Update aborted by user.");
            }
            default -> Console.warning("Property chosen is not fillable. Type 'exit' to cancel.");
        }
        Console.line();
    }
}
