package src.ui;

import src.controllers.HotelController;
import src.controllers.UserController;
import src.enums.Roles;
import src.models.Hotel;
import src.models.User;
import src.services.AuthService;
import src.utils.Console;

public class MainConsole {
    private AuthService auth = AuthService.getInstance();
    private UserController userController = new UserController();
    private HotelController hotelController = new HotelController();

    public void offMenu() {
        while (!auth.isLogged()) {
            this.offMenuSwitch();
            String opt = Console.ask("");
            switch (opt) {
                case "0" -> this.exit(0);
                case "1" -> userController.register();
                case "2" -> userController.login();
                default -> Console.error("Invalid option!");
            }

        }
        this.onMenu();
    }

    public void onMenu() {
        while (auth.isLogged()) {
            this.onMenuSwitch();
            String opt = Console.ask("");
            switch (opt) {
                case "0" -> this.exit(0);
                case "1" -> userController.logout();
                case "2" -> userController.profile(this.auth.user());
                case "3" -> this.profileSwitch();
                case "4" -> this.hotelMenuSwitch();
                default -> Console.error("Invalid option! Please select a valid menu item.");
            }
        }
        this.offMenu();
    }

    private void offMenuSwitch() {
        Console.line();
        Console.info("Welcome to the Reservation System!");
        Console.info("Please choose an option:");
        Console.info("  1) Register a new account");
        Console.info("  2) Login to your existing account");
        Console.info("  0) Exit application");
        Console.line();
    }

    private void onMenuSwitch() {
        User user = this.auth.user();
        if (user == null) {
            Console.error("No user is currently logged in. Exiting...");
            this.exit(1);
        }

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

    public void exit(Integer code) {
        Console.info("Thank you for using the Reservation System. Goodbye!");
        Console.close();
        System.exit(code);
    }

    public void profileSwitch() {
        while (true) {
            this.profileMenu();
            String opt = Console.ask("");
            switch (opt) {
                case "0" -> this.exit(0);
                case "1" -> this.userController.update("fullName");
                case "2" -> this.userController.update("email");
                case "3" -> this.userController.update("password");
                case "4" -> {
                    return;
                }
                default -> Console.error("Invalid option! Please select a valid menu item.");
            }
        }
    }

    private void profileMenu() {
        Console.line();
        Console.info("Profile Management:");
        Console.info("  1) Update Your Full Name");
        Console.info("  2) Update Your Email Address");
        Console.info("  3) Update Your Password");
        Console.info("  4) Return to the Main Menu");
        Console.info("  0) Exit Application");
        Console.line();
    }

    private void hotelMenu() {
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

    private void hotelMenuSwitch() {
        if (this.auth.user().getRole() != Roles.ADMIN) {
            Console.warning("You don't have administrator privileges to access hotel management.");
            return;
        }

        while (true) {
            this.hotelMenu();
            String opt = Console.ask("Enter your choice");
            switch (opt) {
                case "0" -> this.exit(0);
                case "1" -> this.hotelController.describeAll();
                case "2" -> {
                    Hotel h = hotelController.find();
                    if (h != null) {
                        hotelController.describe(h);
                    }
                }
                case "3" -> this.hotelController.store();
                case "4" -> this.hotelController.update();
                case "5" -> this.hotelController.delete();
                case "6" -> {
                    return;
                }
                default -> Console.error("Invalid option! Please select a valid menu item.");
            }
        }
    }
}
