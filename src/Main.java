import java.util.*;

class Reservation {
    String guestName;
    String roomType;
    String roomId;

    Reservation(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }
}

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
    }

    int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    void increment(String type) {
        inventory.put(type, getAvailability(type) + 1);
    }

    void decrement(String type) {
        inventory.put(type, getAvailability(type) - 1);
    }
}

class BookingHistory {
    private Map<String, Reservation> confirmed = new HashMap<>();

    void add(Reservation r) {
        confirmed.put(r.roomId, r);
    }

    Reservation get(String roomId) {
        return confirmed.get(roomId);
    }

    void remove(String roomId) {
        confirmed.remove(roomId);
    }

    boolean exists(String roomId) {
        return confirmed.containsKey(roomId);
    }
}

class CancellationService {

    private RoomInventory inventory;
    private BookingHistory history;
    private Stack<String> rollbackStack = new Stack<>();

    CancellationService(RoomInventory inventory, BookingHistory history) {
        this.inventory = inventory;
        this.history = history;
    }

    void cancel(String roomId) {

        if (!history.exists(roomId)) {
            System.out.println("Cancellation Failed: Invalid or already cancelled booking");
            return;
        }

        Reservation r = history.get(roomId);

        rollbackStack.push(roomId);

        inventory.increment(r.roomType);

        history.remove(roomId);

        System.out.println("Cancellation Successful: " + roomId);
    }

    void showRollbackStack() {
        System.out.println("Rollback Stack: " + rollbackStack);
    }
}

public class Main {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();

        Reservation r1 = new Reservation("Lakshmi", "Single Room", "Single-1");
        Reservation r2 = new Reservation("Rahul", "Double Room", "Double-1");

        inventory.decrement(r1.roomType);
        inventory.decrement(r2.roomType);

        history.add(r1);
        history.add(r2);

        CancellationService service = new CancellationService(inventory, history);

        service.cancel("Single-1");
        service.cancel("Single-1");

        service.showRollbackStack();
    }
}