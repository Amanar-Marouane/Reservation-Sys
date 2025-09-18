package src;

import src.seeders.Seeder;
import src.utils.Console;

public class Bootstrap {

    public static void run() {
        System.out.println("Bootstrap running...");
        Seeder.run();
        System.out.println("Bootstrap Done.");
    }

    public static void exit(Integer code) {
        Console.info("Thank you for using the Reservation System. Goodbye!");
        Console.close();
        System.exit(code);
    }
}
