import repositories.HotelRepository;
import repositories.UserRepository;
import seeders.Seeder;

public class Bootstrap {

    public static void run() {
        System.out.println("Bootstrap running...");

        Seeder.run(UserRepository.all(), HotelRepository.all());

        System.out.println("Bootstrap Done.");
    }
}
