package src.seeders;

import src.enums.Roles;
import src.models.Hotel;
import src.models.User;
import src.repositories.HotelRepository;
import src.repositories.UserRepository;

public class Seeder {
        private static UserRepository userRepository = UserRepository.getInstance();
        private static HotelRepository hotelRepository = HotelRepository.getInstance();

        public static void run() {
                System.out.println("Seeding data...");

                // Users
                User admin = new User(
                                "Amanar Marouane",
                                "marouane@gmail.com",
                                "mmMM00!!",
                                Roles.ADMIN);
                userRepository.save(admin);

                User normalUser = new User(
                                "Omar Ouyacho",
                                "omar@gmail.com",
                                "llLL00!!",
                                Roles.USER);
                userRepository.save(normalUser);

                // Hotels
                Hotel h1 = new Hotel(
                                "Hotel Atlas",
                                "Marrakech",
                                10,
                                4.3,
                                "Perfect For Nature Lovers!!");
                hotelRepository.save(h1);

                Hotel h2 = new Hotel(
                                "Hotel Sahara",
                                "Agadir",
                                15,
                                4.0,
                                "Romantic & Beautiful Nights? You Are At The Right Place!");
                hotelRepository.save(h2);

                System.out.println("Seeding complete.");
        }
}
