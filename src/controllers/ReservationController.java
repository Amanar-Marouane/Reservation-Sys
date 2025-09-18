package src.controllers;

import java.util.List;

import src.helpers.AuthHelper;
import src.models.Hotel;
import src.models.Reservation;
import src.models.User;
import src.repositories.ReservationRepository;
import src.utils.Console;

public class ReservationController extends Controller {
    private ReservationRepository reservationRepository;

    public ReservationController() {
        this.reservationRepository = ReservationRepository.getInstance();
    }

    public List<Reservation> all() {
        return reservationRepository.all();
    }

    public void store(Hotel h) {
        User u = AuthHelper.shouldBeIn();

        if (h.getAvailableRooms() <= 0) {
            Console.error("The selected hotel has no available rooms at the moment. Please try again later.");
            return;
        }

        int nights = -1;
        do {
            try {
                String input = Console.ask("How many nights would you like to stay?");
                nights = Integer.parseInt(input);
                if (nights <= 0) {
                    Console.error("Number of nights must be greater than 0");
                    nights = -1;
                }
            } catch (NumberFormatException e) {
                Console.error("Please enter a valid number");
                nights = -1;
            }
        } while (nights == -1);

        Reservation r = new Reservation(u.getId(), h.getId(), nights);
        h.setAvailableRooms((h.getAvailableRooms() - 1));
        reservationRepository.save(r);
        this.describe(r);
    }

    public void describe(Reservation r) {
        if (r == null) {
            Console.error("No reservation provided.");
            return;
        }

        Console.line();
        Console.info("Reservation ID : " + r.getId());
        Console.info("User ID        : " + r.getUserId());
        Console.info("Hotel ID       : " + r.getHotelId());
        Console.info("Nights         : " + r.getNights());
        Console.info("TimeStamp      : " + r.getTimestamp());
        Console.line();
    }

    public void describeAll() {
        Console.line();
        List<Reservation> reservations = this.all();

        if (reservations.isEmpty()) {
            Console.warning("No reservations found in the system.");
            return;
        }

        Console.success("=== All Reservations (" + reservations.size() + ") ===");
        for (Reservation r : reservations) {
            this.describe(r);
        }
    }

    public void describeUserRes() {
        User u = AuthHelper.shouldBeIn();
        List<Reservation> reservations = this.all();
        Console.line();

        int userReservationCount = 0;
        for (Reservation r : reservations) {
            if (u.getId().equals(r.getUserId())) {
                if (userReservationCount == 0) {
                    Console.success("=== Your Reservations ===");
                }
                describe(r);
                userReservationCount++;
            }
        }

        if (userReservationCount == 0) {
            Console.warning("You don't have any reservations yet.");
        }
    }

    public Reservation find() {
        Console.line();
        while (true) {
            String id = Console.ask("Enter Reservation ID please: ");
            Reservation r = reservationRepository.find("id", id);
            if (r == null) {
                Console.error("No Reservation found with ID => " + id);
            } else {
                return r;
            }
        }
    }

    public void delete() {
        Reservation r = this.find();
        if (r == null) {
            Console.warning("Cancel operation canceled.");
            return;
        }

        // Confirm deletion
        boolean confirm = Console.confirm("Are you sure you want to cancel this reservation? (yes/no)");
        if (confirm) {
            reservationRepository.delete("id", r.getId().toString());
            Console.success("Reservation with ID '" + r.getId() + "' canceled successfully.");
        } else {
            Console.warning("Operation canceled.");
        }
        Console.line();
    }
}
