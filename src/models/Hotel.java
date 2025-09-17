package src.models;

import src.utils.Str;

public class Hotel {
    private String id;
    private String name;
    private String address;
    private Integer availableRooms;
    private Double rating;
    private String note;

    public Hotel(String name, String address, Integer availableRooms, Double rating, String note) {
        this.id = Str.random(10);
        this.name = name;
        this.address = address;
        this.availableRooms = availableRooms;
        this.rating = rating;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(Integer availableRooms) {
        this.availableRooms = availableRooms;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}