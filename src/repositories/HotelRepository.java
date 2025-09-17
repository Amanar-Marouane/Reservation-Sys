package src.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.interfaces.RepositoryBase;
import src.models.Hotel;

public class HotelRepository implements RepositoryBase<Hotel> {
    private static final Map<String, Hotel> hotels = new HashMap<>(); // key: id
    private static HotelRepository instance;

    private HotelRepository() {
    }

    public static HotelRepository getInstance() {
        if (instance == null) {
            instance = new HotelRepository();
        }
        return instance;
    }

    @Override
    public List<Hotel> all() {
        return new ArrayList<>(hotels.values());
    }

    @Override
    public Hotel find(String key, Object value) {
        // For now, only support lookup by "id"
        if ("id".equalsIgnoreCase(key) && value instanceof String id) {
            return hotels.get(id);
        }
        // Could extend later ...
        return null;
    }

    @Override
    public void save(Hotel hotel) {
        hotels.put(hotel.getId(), hotel);
    }

    @Override
    public void delete(String key, String value) {
        // For now, only support deletion by "id"
        if ("id".equalsIgnoreCase(key)) {
            hotels.remove(value);
        }
    }
}
