/**
<<<<<<< HEAD
 * Abstract class representing a general Room.
 * It defines common properties shared by all room types.
 */

abstract class Room {

    protected int beds;
    protected int size;
    protected double price;

    public Room(int beds, int size, double price) {
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq ft");
        System.out.println("Price: $" + price);
    }
}
=======
 * HotelBookingApplication
 *
 * This class represents the entry point of the Hotel Booking System.
 * When the program starts, it prints a welcome message along with
 * the application name and version information.
 *
 * @author Abhinav
 * @version 1.0
 */

public class HotelBookingApplication {

    /**
     * Main method - Entry point of the Java application.
     * The JVM starts execution from this method.
     */
    public static void main(String[] args) {

        // Display welcome message
        System.out.println("=====================================");
        System.out.println(" Welcome to the Hotel Booking System ");
        System.out.println(" Application Version: v1.0 ");
        System.out.println("=====================================");

        // Inform user that application started successfully
        System.out.println("Application started successfully.");

        // Program terminates after execution
    }
}




>>>>>>> ccb7031171e873fed8ae1a8f7f62fc458f29ef54
