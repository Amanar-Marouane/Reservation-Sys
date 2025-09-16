package seeders;

import java.util.*;

import models.Hotel;
import models.User;
import enums.Roles;

public class Seeder {
    public static void run(Map<String, User> users, Map<String, Hotel> hotels) {
        System.out.println("Seeding data...");

        // Users
        User admin = new User(
                UUID.randomUUID(),
                "Amanar Marouane",
                "marouane@gmail.com",
                "mmMM00!!",
                Roles.ADMIN);
        users.put(admin.getEmail(), admin);

        User normalUser = new User(
                UUID.randomUUID(),
                "Omar Ouyacho",
                "omar@gmail.com",
                "llLL00!!",
                Roles.USER);
        users.put(normalUser.getEmail(), normalUser);

        // Hotels
        Hotel h1 = new Hotel("H001", "Hotel Atlas", "Marrakech", 10, 4.3);
        hotels.put(h1.getId(), h1);

        Hotel h2 = new Hotel("H002", "Hotel Sahara", "Agadir", 15, 4.0);
        hotels.put(h2.getId(), h2);

        System.out.println("Seeding complete.");
    }
}
