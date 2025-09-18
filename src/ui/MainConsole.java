package src.ui;

import src.Bootstrap;
import src.controllers.HotelController;
import src.controllers.ReservationController;
import src.controllers.UserController;
import src.enums.Roles;
import src.helpers.AuthHelper;
import src.models.Hotel;
import src.models.User;
import src.services.AuthService;
import src.utils.Console;

public class MainConsole {
    private AuthService auth;
    private UserController userController;
    private HotelController hotelController;
    private ReservationController reservationController;

    public MainConsole() {
        this.auth = AuthService.getInstance();
        this.userController = new UserController();
        this.hotelController = new HotelController();
        this.reservationController = new ReservationController();
    }

    public void offMenu() {
        while (!auth.isLogged()) {
            Menu.offMenu();
            String opt = Console.ask("");
            switch (opt) {
                case "0" -> Bootstrap.exit(0);
                case "1" -> userController.register();
                case "2" -> userController.login();
                default -> Console.error("Invalid option!");
            }
        }
        this.onMenu();
    }

    public void onMenu() {
        User u = AuthHelper.shouldBeIn();
        Menu.self(u);

        switch (u.getRole()) {
            case ADMIN -> this.InAdminMenu();
            case USER -> this.InUserMenu();
            default -> Console.warning("No Access For Sneakes LOL");
        }
    }

    public void InUserMenu() {
        while (auth.isLogged()) {
            Menu.InUserMenu();
            String opt = Console.ask("");
            switch (opt) {
                case "0" -> Bootstrap.exit(0);
                case "1" -> userController.logout();
                case "2" -> userController.profile(this.auth.user());
                case "3" -> this.profileSwitch();
                case "4" -> this.hotelController.describeAll();
                case "5" -> {
                    Hotel h = hotelController.find();
                    if (h != null) {
                        hotelController.describe(h);
                    }
                }
                case "6" -> this.reservationMenuSwitch();
                default -> Console.error("Invalid option! Please select a valid menu item.");
            }
        }
        this.offMenu();
    }

    public void InAdminMenu() {
        while (auth.isLogged()) {
            Menu.InAdminMenu();
            String opt = Console.ask("");
            switch (opt) {
                case "0" -> Bootstrap.exit(0);
                case "1" -> userController.logout();
                case "2" -> userController.profile(this.auth.user());
                case "3" -> this.profileSwitch();
                case "4" -> this.hotelMenuSwitch();
                case "5" -> this.reservationController.describeAll();
                default -> Console.error("Invalid option! Please select a valid menu item.");
            }
        }
        this.offMenu();
    }

    public void profileSwitch() {
        while (true) {
            Menu.profileMenu();
            String opt = Console.ask("");
            switch (opt) {
                case "0" -> Bootstrap.exit(0);
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

    private void hotelMenuSwitch() {
        if (this.auth.user().getRole() != Roles.ADMIN) {
            Console.warning("You don't have administrator privileges to access hotel management.");
            return;
        }

        while (true) {
            Menu.hotelMenu();
            String opt = Console.ask("Enter your choice");
            switch (opt) {
                case "0" -> Bootstrap.exit(0);
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

    private void reservationMenuSwitch() {
        while (true) {
            Menu.reservationMenu();
            String opt = Console.ask("Enter your choice");
            switch (opt) {
                case "0" -> Bootstrap.exit(0);
                case "1" -> this.reservationController.describeUserRes();
                case "2" -> {
                    Hotel h = hotelController.find();
                    if (h != null) {
                        reservationController.store(h);
                    }
                }
                case "3" -> reservationController.delete();
                case "4" -> {
                    return;
                }
                default -> Console.error("Invalid option! Please select a valid menu item.");
            }
        }
    }
}
