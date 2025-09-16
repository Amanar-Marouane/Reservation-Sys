package controllers;

import java.util.HashMap;

import models.User;
import repositories.UserRepository;
import services.AuthService;
import utils.Console;
import utils.Validator;

public class UserController {
    private AuthService auth = AuthService.getInstance();

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
        HashMap<String, String> register = this.registerAttempt();
        this.auth.register(
                UserRepository.all(),
                register.get("fullName"),
                register.get("email"),
                register.get("password"));
    }

    public void login() {
        HashMap<String, String> login = this.loginAttempt();
        this.auth.login(UserRepository.all(),
                login.get("email"),
                login.get("password"));
    }

    public void logout() {
        this.logout();
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
}
