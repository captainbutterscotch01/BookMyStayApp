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

    void display() {
        System.out.println("Guest: " + guestName +
                ", Room Type: " + roomType +
                ", Room ID: " + roomId);
    }
}

class BookingHistory {

    private List<Reservation> history = new ArrayList<>();

    void addReservation(Reservation r) {
        history.add(r);
    }

    List<Reservation> getAllReservations() {
        return history;
    }
}

class BookingReportService {

    void displayAll(List<Reservation> reservations) {
        System.out.println("Booking History:");
        for (Reservation r : reservations) {
            r.display();
        }
    }

    void summary(List<Reservation> reservations) {
        Map<String, Integer> countMap = new HashMap<>();

        for (Reservation r : reservations) {
            countMap.put(r.roomType,
                    countMap.getOrDefault(r.roomType, 0) + 1);
        }

        System.out.println("\nSummary Report:");
        for (String type : countMap.keySet()) {
            System.out.println(type + " Bookings: " + countMap.get(type));
        }
    }
}

public class Main {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("Lakshmi", "Single Room", "Single-1"));
        history.addReservation(new Reservation("Rahul", "Double Room", "Double-1"));
        history.addReservation(new Reservation("Anita", "Single Room", "Single-2"));

        BookingReportService reportService = new BookingReportService();

        List<Reservation> reservations = history.getAllReservations();

        reportService.displayAll(reservations);
        reportService.summary(reservations);
    }
}