package src.ui;

import src.controllers.UserController;
import src.models.User;
import src.services.AuthService;
import src.utils.Console;

public class MainConsole {
    private AuthService auth = AuthService.getInstance();
    private UserController userController = new UserController();

    public void offMenu() {
        while (!auth.isLogged()) {
            this.offMenuSwitch();
            String opt = Console.ask("");
            switch (opt) {
                case "0":
                    this.exit(0);

                case "1":
                    userController.register();
                    break;

                case "2":
                    userController.login();
                    break;

                default:
                    Console.error("Invalid option!");
            }

        }
        this.onMenu();
    }

    public void onMenu() {
        while (auth.isLogged()) {
            this.onMenuSwitch();
            String opt = Console.ask("");
            switch (opt) {
                case "0":
                    this.exit(0);
                    break;

                case "1":
                    userController.logout();
                    break;

                case "2":
                    userController.profile(this.auth.user());
                    break;

                case "3":
                    this.profileUpdateSwitch();
                    break;

                default:
                    Console.error("Invalid option! Please select a valid menu item.");
            }
        }
        this.offMenu();
    }

    private void offMenuSwitch() {
        Console.line();
        Console.info("Welcome to the Reservation System!");
        Console.info("Please choose an option:");
        Console.info("  1) Register a new account");
        Console.info("  2) Login to your account");
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
        Console.info("Role: " + user.getRole());
        Console.info("Choose an option:");
        Console.info("  1) Logout");
        Console.info("  2) View Profile");
        Console.info("  3) Update Your Profile");
        Console.info("  0) Exit application");
        Console.line();
    }

    public void exit(Integer code) {
        Console.info("Thank you for using the Reservation System. Goodbye!");
        Console.close();
        System.exit(code);
    }

    public void profileUpdateSwitch() {
        boolean updateSession = true;
        while (updateSession) {
            this.profileUpdateMenu();
            String opt = Console.ask("");
            switch (opt) {
                case "0":
                    this.exit(0);
                    break;

                case "1":
                    this.userController.update("fullName");
                    break;

                case "2":
                    this.userController.update("email");
                    break;

                case "3":
                    this.userController.update("password");
                    break;

                case "4":
                    updateSession = false;
                    break;

                default:
                    Console.error("Invalid option! Please select a valid menu item.");
            }
        }
    }

    private void profileUpdateMenu() {
        Console.line();
        Console.info("Choose an option:");
        Console.info("  1) Update Your fullName");
        Console.info("  2) Update Your Email");
        Console.info("  3) Update Your Password");
        Console.info("  4) Return To The Main Menu");
        Console.info("  0) Exit application");
        Console.line();
    }
}
