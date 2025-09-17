package src.seeders;

import java.util.*;

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
                UUID.randomUUID(),
                "Amanar Marouane",
                "marouane@gmail.com",
                "mmMM00!!",
                Roles.ADMIN);
        userRepository.save(admin);

        User normalUser = new User(
                UUID.randomUUID(),
                "Omar Ouyacho",
                "omar@gmail.com",
                "llLL00!!",
                Roles.USER);
        userRepository.save(normalUser);

        // Hotels
        Hotel h1 = new Hotel("H001", "Hotel Atlas", "Marrakech", 10, 4.3);
        hotelRepository.save(h1);

        Hotel h2 = new Hotel("H002", "Hotel Sahara", "Agadir", 15, 4.0);
        hotelRepository.save(h2);

        System.out.println("Seeding complete.");
    }
}
