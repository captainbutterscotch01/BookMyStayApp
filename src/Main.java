import java.util.*;

class Service {
    String name;
    double cost;

    Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

class AddOnServiceManager {

    private Map<String, List<Service>> serviceMap = new HashMap<>();

    void addService(String reservationId, Service service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    double getTotalCost(String reservationId) {
        double total = 0;

        List<Service> services = serviceMap.get(reservationId);

        if (services != null) {
            for (Service s : services) {
                total += s.cost;
            }
        }

        return total;
    }

    void displayServices(String reservationId) {
        List<Service> services = serviceMap.get(reservationId);

        if (services != null) {
            System.out.println("Services for Reservation " + reservationId + ":");
            for (Service s : services) {
                System.out.println("- " + s.name + " : " + s.cost);
            }
        } else {
            System.out.println("No services selected.");
        }
    }
}

public class Main {

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "SingleRoom-1";

        manager.addService(reservationId, new Service("Breakfast", 200));
        manager.addService(reservationId, new Service("Airport Pickup", 500));
        manager.addService(reservationId, new Service("Extra Bed", 300));

        manager.displayServices(reservationId);

        double total = manager.getTotalCost(reservationId);

        System.out.println("Total Add-On Cost: " + total);
    }
}