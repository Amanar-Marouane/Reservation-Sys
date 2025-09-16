import java.util.*;

import models.Hotel;
import models.Reservation;
import models.User;
import seeders.Seeder;

public class Bootstrap {
    public static Map<String, User> users = new HashMap<>(); // key: email
    public static Map<String, Hotel> hotels = new HashMap<>(); // key: hotelId
    public static List<Reservation> reservations = new ArrayList<>(); // chronological list

    public static void run() {
        System.out.println("Bootstrap running...");

        Seeder.run(users, hotels);

        System.out.println("Bootstrap Done.");
    }
}
