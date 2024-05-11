package JavaConcurrency.src.main.java.com.JavaConcurrency.ReentrantLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BookingApplication {
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem();
        User user1 = new User(bookingSystem, "Alice", "Flight to Paris");
        User user2 = new User(bookingSystem, "Bob", "Concert ticket");

        user1.start();
        user2.start();

        try {
            user1.join();
            user2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bookingSystem.printBookings();
    }
}

class TicketBookingSystem {
    private Map<String, String> bookings;
    private Lock lock;

    public TicketBookingSystem() {
        bookings = new HashMap<>();
        lock = new ReentrantLock();
    }

    public void bookTicket(String user, String booking) {
        lock.lock();

        try {
            // Simulate booking ticket
            bookings.put(user, booking);
            System.out.println("Booking for " + user + ": " + booking);
        } finally {
            lock.unlock();
        }
    }

    public void printBookings() {
        for (Map.Entry<String, String> entry : bookings.entrySet()) {
            System.out.println("User: " + entry.getKey() + ", Booking: " + entry.getValue());
        }
    }
}

class User extends Thread {
    private TicketBookingSystem bookingSystem;
    private String username;
    private String booking;

    public User(TicketBookingSystem bookingSystem, String username, String booking) {
        this.bookingSystem = bookingSystem;
        this.username = username;
        this.booking = booking;
    }

    @Override
    public void run() {
        bookingSystem.bookTicket(username, booking);
    }
}
