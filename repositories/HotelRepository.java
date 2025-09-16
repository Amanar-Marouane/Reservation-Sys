package repositories;

import java.util.HashMap;
import java.util.Map;

import models.Hotel;

public class HotelRepository {
    private static Map<String, Hotel> hotels = new HashMap<>(); // key: hotelId

    public static  Map<String, Hotel> all(){
        return hotels;
    }
}
