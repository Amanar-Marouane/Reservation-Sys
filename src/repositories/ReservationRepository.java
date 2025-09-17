package src.repositories;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import src.interfaces.RepositoryBase;
import src.models.Reservation;
import src.utils.Console;

public class ReservationRepository implements RepositoryBase<Reservation> {

    private final List<Reservation> reservations = new ArrayList<>();
    private static ReservationRepository instance;

    private ReservationRepository() {
    }

    public static ReservationRepository getInstance() {
        if (instance == null) {
            instance = new ReservationRepository();
        }
        return instance;
    }

    @Override
    public List<Reservation> all() {
        return new ArrayList<>(reservations);
    }

    @Override
    public Reservation find(String key, Object value) {
        for (Reservation r : reservations) {
            try {
                Field field = Reservation.class.getDeclaredField(key);
                field.setAccessible(true); // allow private fields
                Object fieldValue = field.get(r);
                if (value.equals(fieldValue)) {
                    return r;
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                Console.error("Invalid key '" + key + "' for entity " + Reservation.class.getSimpleName());
            }
        }
        return null;
    }

    @Override
    public void save(Reservation reservation) {
        reservations.add(reservation);
    }

    @Override
    public void delete(String key, String value) {
        Reservation r = this.find(key, value);
        if (r != null) {
            this.reservations.remove(r);
        } else {
            Console.error("Delete failed: No reservation found for key '" + key + "' with value '" + value + "'");
        }
    }
}
