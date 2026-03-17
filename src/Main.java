import java.io.*;
import java.util.*;

class Reservation implements Serializable {
    String guestName;
    String roomType;
    String roomId;

    Reservation(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }
}

class RoomInventory implements Serializable {
    Map<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }
}

class BookingHistory implements Serializable {
    List<Reservation> history = new ArrayList<>();

    void add(Reservation r) {
        history.add(r);
    }
}

class SystemState implements Serializable {
    RoomInventory inventory;
    BookingHistory history;

    SystemState(RoomInventory inventory, BookingHistory history) {
        this.inventory = inventory;
        this.history = history;
    }
}

class PersistenceService {

    private static final String FILE_NAME = "system.dat";

    static void save(SystemState state) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(state);
            System.out.println("State saved successfully.");

        } catch (Exception e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    static SystemState load() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            System.out.println("State loaded successfully.");
            return (SystemState) ois.readObject();

        } catch (Exception e) {
            System.out.println("No previous state found. Starting fresh.");
            return new SystemState(new RoomInventory(), new BookingHistory());
        }
    }
}

public class Main {

    public static void main(String[] args) {

        SystemState state = PersistenceService.load();

        state.history.add(new Reservation("Lakshmi", "Single Room", "S-1"));
        state.inventory.inventory.put("Single Room",
                state.inventory.inventory.get("Single Room") - 1);

        PersistenceService.save(state);

        System.out.println("Current Bookings: " + state.history.history.size());
        System.out.println("Single Room Available: " +
                state.inventory.inventory.get("Single Room"));
    }
}