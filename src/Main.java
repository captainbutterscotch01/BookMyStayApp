import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingQueue {
    Queue<Reservation> queue = new LinkedList<>();

    void addRequest(Reservation r) {
        queue.add(r);
    }

    Reservation getNextRequest() {
        return queue.poll();
    }

    boolean isEmpty() {
        return queue.isEmpty();
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    void decrement(String type) {
        inventory.put(type, getAvailability(type) - 1);
    }
}

class BookingService {

    private RoomInventory inventory;
    private HashMap<String, Set<String>> allocatedRooms = new HashMap<>();
    private int idCounter = 1;

    BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    void process(BookingQueue queue) {

        while (!queue.isEmpty()) {

            Reservation r = queue.getNextRequest();

            int available = inventory.getAvailability(r.roomType);

            if (available > 0) {

                String roomId = r.roomType + "-" + idCounter++;

                allocatedRooms.putIfAbsent(r.roomType, new HashSet<>());

                Set<String> roomSet = allocatedRooms.get(r.roomType);

                if (!roomSet.contains(roomId)) {
                    roomSet.add(roomId);
                    inventory.decrement(r.roomType);

                    System.out.println("Booking Confirmed:");
                    System.out.println("Guest: " + r.guestName);
                    System.out.println("Room Type: " + r.roomType);
                    System.out.println("Room ID: " + roomId);
                    System.out.println();
                }

            } else {
                System.out.println("Booking Failed (No Availability): " + r.guestName + " - " + r.roomType);
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {

        BookingQueue queue = new BookingQueue();

        queue.addRequest(new Reservation("Lakshmi", "Single Room"));
        queue.addRequest(new Reservation("Rahul", "Single Room"));
        queue.addRequest(new Reservation("Anita", "Single Room"));
        queue.addRequest(new Reservation("Kiran", "Suite Room"));

        RoomInventory inventory = new RoomInventory();

        BookingService bookingService = new BookingService(inventory);

        bookingService.process(queue);
    }
}