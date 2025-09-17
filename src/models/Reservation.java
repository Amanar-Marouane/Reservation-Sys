package src.models;

import java.sql.Date;
import java.util.UUID;

public class Reservation {
    private UUID id;
    private Date timestamp;
    private String userId;
    private String hotelId;
    private Integer nights;

    public Reservation(UUID id, Date timestamp, String userId, String hotelId, Integer nights) {
        this.id = id;
        this.timestamp = timestamp;
        this.userId = userId;
        this.hotelId = hotelId;
        this.nights = nights;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getNights() {
        return nights;
    }

    public void setNights(Integer nights) {
        this.nights = nights;
    }
}