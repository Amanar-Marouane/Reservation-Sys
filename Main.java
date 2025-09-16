import java.util.HashMap;

import services.AuthService;
import utils.Console;

public class Main {
    public AuthService auth = new AuthService();

    public static void main(String[] args) {
        Bootstrap.run();

        Main app = new Main();

        app.offMenu();
    }

    private void offMenu() {
        while (!auth.isLogged()) {
            this.offMenuSwitch();
            String opt = Console.ask("");
            switch (opt) {
                case "0":
                    this.exit();
                case "1":
                    HashMap<String, String> register = this.auth.registerAttempt();
                    this.auth.register(
                            Bootstrap.users,
                            register.get("fullName"),
                            register.get("email"),
                            register.get("password"));
                    break;

                case "2":
                    HashMap<String, String> login = this.auth.loginAttempt();
                    this.auth.login(Bootstrap.users,
                            login.get("email"),
                            login.get("password"));
                    break;

                default:
                    Console.error("Invalid option!");
            }

        }
        this.onMenu();
    }

    private void onMenu() {
        while (auth.isLogged()) {
            this.onMenuSwitch();
            String opt = Console.ask("");
            switch (opt) {
                case "0":
                    this.exit();
                    break;

                case "1":
                    this.auth.logout();
                    break;

                default:
                    Console.error("Invalid option!");
            }
        }
        this.offMenu();
    }

    private void offMenuSwitch() {
        Console.line();
        Console.info("=== Welcome ===");
        Console.info("1) Register a new account");
        Console.info("2) Login to your account");
        Console.info("0) Exit application");
        Console.line();
    }

    private void onMenuSwitch() {
        Console.line();
        Console.info("=== User Menu ===");
        Console.info("1) Logout");
        Console.info("0) Exit application");
        Console.line();
    }

    private void exit() {
        Console.close();
        System.exit(0);
    }
}
