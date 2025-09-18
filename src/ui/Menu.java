package src.ui;

import src.Bootstrap;
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

    public static void InUserMenu() {
        User user = AuthHelper.shouldBeIn();
        if (user.getRole() == Roles.ADMIN) {
            Console.warning("User session detected, but you have administrator privileges!");
            Console.warning("Exiting...");
            Bootstrap.exit(0);
        }

        Console.line();
        Console.info("Choose an option:");
        Console.info("  1) Logout");
        Console.info("  2) View Your Profile");
        Console.info("  3) Manage Your Profile Settings");
        Console.info("  4) List All Hotels");
        Console.info("  5) Search for a Hotel");
        Console.info("  6) Manage Your Reservations");
        Console.info("  0) Exit Application");
        Console.line();
    }

    public static void InAdminMenu() {
        User user = AuthHelper.shouldBeIn();
        if (user.getRole() == Roles.USER) {
            Console.warning("Admin session detected, but you only have user privileges!");
            Console.warning("Exiting...");
            Bootstrap.exit(0);
        }

        Console.line();
        Console.info("Choose an option:");
        Console.info("  1) Logout");
        Console.info("  2) View Your Profile");
        Console.info("  3) Manage Your Profile Settings");
        Console.info("  4) Manage Hotels");
        Console.info("  5) See All Reservations");
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

    public static void reservationMenu() {
        AuthHelper.shouldBeIn();

        Console.line();
        Console.info("Reservation Management:");
        Console.info("  1) View All Your Reservations");
        Console.info("  2) Make a New Reservation");
        Console.info("  3) Cancel a Reservation");
        Console.info("  4) Return to the Main Menu");
        Console.info("  0) Exit Application");
        Console.line();
    }

    public static void self(User u) {
        Console.success("Hello, " + u.getFullName() + "!");
        Console.info("Current Role: " + u.getRole());
    }
}
