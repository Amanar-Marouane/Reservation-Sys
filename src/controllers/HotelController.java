package src.controllers;

import java.util.List;

import src.models.Hotel;
import src.repositories.HotelRepository;
import src.services.AuthService;
import src.utils.Console;

public class HotelController extends Controller {
    private AuthService auth = AuthService.getInstance();
    private HotelRepository hotelRepository = HotelRepository.getInstance();

    public List<Hotel> all() {
        return hotelRepository.all();
    }

    public void describe(Hotel hotel) {
        if (hotel == null) {
            Console.error("No hotel provided.");
            return;
        }

        Console.line();
        Console.info("Hotel ID       : " + hotel.getId());
        Console.info("Name           : " + hotel.getName());
        Console.info("Address        : " + hotel.getAddress());
        Console.info("Available Rooms: " + hotel.getAvailableRooms());
        Console.info("Rating         : " + hotel.getRating());
        Console.info("Note           : " + hotel.getNote());
        Console.line();
    }

    public void describeAll() {
        Console.line();
        List<Hotel> hotels = this.all();

        if (hotels.isEmpty()) {
            Console.warning("No hotels found in the system.");
            return;
        }

        Console.success("=== Available Hotels (" + hotels.size() + ") ===");
        for (Hotel h : hotels) {
            this.describe(h);
        }
    }

    public void store() {
        if (!this.onlyAdmin(this.auth.user())) {
            Console.warning("You are not authorized to perform this action.");
            Console.line();
            return;
        }
        Console.line();
        Console.success("=== Add New Hotel ===");

        // Get hotel name
        String name = null;
        do {
            name = Console.ask("Enter hotel name");
            if (name.isEmpty()) {
                Console.error("Hotel name cannot be empty");
                name = null;
            }
        } while (name == null);

        // Get hotel address
        String address = null;
        do {
            address = Console.ask("Enter hotel address");
            if (address.isEmpty()) {
                Console.error("Hotel address cannot be empty");
                address = null;
            }
        } while (address == null);

        // Get available rooms
        Integer availableRooms = null;
        do {
            try {
                String input = Console.ask("Enter number of available rooms");
                availableRooms = Integer.parseInt(input);
                if (availableRooms <= 0) {
                    Console.error("Available rooms must be greater than 0");
                    availableRooms = null;
                }
            } catch (NumberFormatException e) {
                Console.error("Please enter a valid number");
            }
        } while (availableRooms == null);

        // Get hotel rating
        Double rating = null;
        do {
            try {
                String input = Console.ask("Enter hotel rating (0.0-5.0)");
                rating = Double.parseDouble(input);
                if (rating < 0 || rating > 5) {
                    Console.error("Rating must be between 0 and 5");
                    rating = null;
                }
            } catch (NumberFormatException e) {
                Console.error("Please enter a valid rating");
            }
        } while (rating == null);

        // Get note
        String note = Console.ask("Enter additional notes (optional)");

        try {
            Hotel hotel = new Hotel(name, address, availableRooms, rating, note);
            hotelRepository.save(hotel);

            Console.success("Hotel added successfully!");
            Console.info("Hotel ID: " + hotel.getId());
        } catch (Exception e) {
            Console.error("Failed to add hotel. Please try again.");
        }

        Console.line();
    }

    public Hotel find() {
        Console.line();
        while (true) {
            Console.info("Available Keys => ID");
            String key = Console.ask("How would you search for a Hotel?");

            switch (key.toLowerCase()) {
                case "id" -> {
                    String id = Console.ask("Enter Hotel ID please: ");
                    Hotel h = hotelRepository.find("id", id);
                    if (h == null) {
                        Console.error("No Hotel found with ID => " + id);
                    } else {
                        return h;
                    }
                }
                case "exit" -> {
                    Console.info("Search aborted by user.");
                    return null;
                }
                default -> Console.warning("Invalid key! Only 'id' is supported. Type 'exit' to cancel.");
            }
        }
    }

    public void delete() {
        if (!this.onlyAdmin(this.auth.user())) {
            Console.warning("You are not authorized to perform this action.");
            Console.line();
            return;
        }

        Hotel h = this.find();
        if (h == null) {
            Console.warning("Delete operation canceled.");
            return;
        }

        // Confirm deletion
        boolean confirm = Console.confirm("Are you sure you want to delete this hotel?");
        if (confirm) {
            hotelRepository.delete("id", h.getId());
            Console.success("Hotel with ID '" + h.getId() + "' deleted successfully.");
        } else {
            Console.warning("Deletion canceled.");
        }
        Console.line();
    }

    public void update() {
        if (!this.onlyAdmin(this.auth.user())) {
            Console.warning("You are not authorized to perform this action.");
            Console.line();
            return;
        }

        Hotel hotel = this.find();
        if (hotel == null) {
            Console.warning("Update operation canceled.");
            return;
        }

        Console.line();
        Console.success("=== Update Hotel: " + hotel.getName() + " ===");
        Console.info("Current Hotel Details:");
        this.describe(hotel);

        while (true) {
            Console.info("What would you like to update?");
            Console.info("1) Name (current: " + hotel.getName() + ")");
            Console.info("2) Address (current: " + hotel.getAddress() + ")");
            Console.info("3) Available Rooms (current: " + hotel.getAvailableRooms() + ")");
            Console.info("4) Rating (current: " + hotel.getRating() + ")");
            Console.info("5) Note (current: " + hotel.getNote() + ")");
            Console.info("0) Exit");

            String choice = Console.ask("Select an option");

            switch (choice) {
                case "1" -> {
                    String name = null;
                    do {
                        name = Console.ask("Enter new hotel name");
                        if (name.isEmpty()) {
                            Console.error("Hotel name cannot be empty");
                            name = null;
                        }
                    } while (name == null);
                    hotel.setName(name);
                    Console.success("Name updated successfully!");
                }
                case "2" -> {
                    String address = null;
                    do {
                        address = Console.ask("Enter new hotel address");
                        if (address.isEmpty()) {
                            Console.error("Hotel address cannot be empty");
                            address = null;
                        }
                    } while (address == null);
                    hotel.setAddress(address);
                    Console.success("Address updated successfully!");
                }
                case "3" -> {
                    Integer availableRooms = null;
                    do {
                        try {
                            String input = Console.ask("Enter new number of available rooms");
                            availableRooms = Integer.parseInt(input);
                            if (availableRooms <= 0) {
                                Console.error("Available rooms must be greater than 0");
                                availableRooms = null;
                            }
                        } catch (NumberFormatException e) {
                            Console.error("Please enter a valid number");
                        }
                    } while (availableRooms == null);
                    hotel.setAvailableRooms(availableRooms);
                    Console.success("Available rooms updated successfully!");
                }
                case "4" -> {
                    Double rating = null;
                    do {
                        try {
                            String input = Console.ask("Enter new hotel rating (0.0-5.0)");
                            rating = Double.parseDouble(input);
                            if (rating < 0 || rating > 5) {
                                Console.error("Rating must be between 0 and 5");
                                rating = null;
                            }
                        } catch (NumberFormatException e) {
                            Console.error("Please enter a valid rating");
                        }
                    } while (rating == null);
                    hotel.setRating(rating);
                    Console.success("Rating updated successfully!");
                }
                case "5" -> {
                    String note = Console.ask("Enter new additional notes");
                    hotel.setNote(note);
                    Console.success("Notes updated successfully!");
                }
                case "0" -> {
                    Console.success("Hotel information saved successfully.");
                    Console.line();
                    return;
                }
                default -> Console.error("Invalid option. Please try again.");
            }
        }
    }
}
