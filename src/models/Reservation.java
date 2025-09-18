package src.models;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class Reservation {
    private UUID id;
    private Date timestamp;
    private UUID userId;
    private String hotelId;
    private Integer nights;

    public Reservation(UUID userId, String hotelId, Integer nights) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.hotelId = hotelId;
        this.nights = nights;
        this.timestamp = Date.from(Instant.now());
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
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