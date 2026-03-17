import java.util.*;

class InvalidBookingException extends Exception {
    InvalidBookingException(String message) {
        super(message);
    }
}

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }

    boolean isValidRoom(String type) {
        return inventory.containsKey(type);
    }

    int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    void decrement(String type) {
        inventory.put(type, getAvailability(type) - 1);
    }
}

class BookingValidator {

    static void validate(Reservation r, RoomInventory inventory) throws InvalidBookingException {

        if (r.guestName == null || r.guestName.isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty");
        }

        if (!inventory.isValidRoom(r.roomType)) {
            throw new InvalidBookingException("Invalid room type: " + r.roomType);
        }

        if (inventory.getAvailability(r.roomType) <= 0) {
            throw new InvalidBookingException("No availability for " + r.roomType);
        }
    }
}

class BookingService {

    private RoomInventory inventory;

    BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    void book(Reservation r) {
        try {
            BookingValidator.validate(r, inventory);

            inventory.decrement(r.roomType);

            System.out.println("Booking Successful: " + r.guestName + " -> " + r.roomType);

        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }
    }
}

public class Main {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService(inventory);

        service.book(new Reservation("Lakshmi", "Single Room"));
        service.book(new Reservation("", "Single Room"));
        service.book(new Reservation("Rahul", "Suite Room"));
        service.book(new Reservation("Anita", "Double Room"));
        service.book(new Reservation("Kiran", "Double Room"));
    }
}
