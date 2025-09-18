package src.ui;

import src.enums.Roles;
import src.helpers.AuthHelper;
import src.models.User;
import src.utils.Console;

public class Menu {
    public static void offMenu() {
        AuthHelper.shouldBeOut();

        Console.line();
        Console.info("Welcome to the Reservation System!");
        Console.info("Please choose an option:");
        Console.info("  1) Register a new account");
        Console.info("  2) Login to your existing account");
        Console.info("  0) Exit application");
        Console.line();
    }

    public static void onMenu() {
        User user = AuthHelper.shouldBeIn();

        Console.line();
        Console.success("Hello, " + user.getFullName() + "!");
        Console.info("Current Role: " + user.getRole());
        Console.info("Choose an option:");
        Console.info("  1) Logout");
        Console.info("  2) View Your Profile");
        Console.info("  3) Manage Your Profile Settings");
        if (user.getRole() == Roles.ADMIN)
            Console.info("  4) Manage Hotels");
        Console.info("  0) Exit Application");
        Console.line();
    }

    public static void profileMenu() {
        AuthHelper.shouldBeIn();

        Console.line();
        Console.info("Profile Management:");
        Console.info("  1) Update Your Full Name");
        Console.info("  2) Update Your Email Address");
        Console.info("  3) Update Your Password");
        Console.info("  4) Return to the Main Menu");
        Console.info("  0) Exit Application");
        Console.line();
    }

    public static void hotelMenu() {
        AuthHelper.shouldBeIn();

        Console.line();
        Console.info("Hotel Management:");
        Console.info("  1) List All Hotels");
        Console.info("  2) Search for a Hotel");
        Console.info("  3) Add a New Hotel");
        Console.info("  4) Update Hotel Information");
        Console.info("  5) Delete a Hotel");
        Console.info("  6) Return to the Main Menu");
        Console.info("  0) Exit Application");
        Console.line();
    }
}
