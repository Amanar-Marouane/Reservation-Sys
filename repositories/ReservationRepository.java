package repositories;

import java.util.ArrayList;
import java.util.List;

import models.Reservation;

public class ReservationRepository {
    private static List<Reservation> reservations = new ArrayList<>(); // chronological list

    public static List<Reservation> all() {
        return reservations;
    }
}
