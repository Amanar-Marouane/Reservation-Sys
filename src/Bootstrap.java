package src;
import src.seeders.Seeder;

public class Bootstrap {

    public static void run() {
        System.out.println("Bootstrap running...");

        Seeder.run();

        System.out.println("Bootstrap Done.");
    }
}
