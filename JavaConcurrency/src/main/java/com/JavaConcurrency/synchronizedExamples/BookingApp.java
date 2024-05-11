package JavaConcurrency.src.main.java.com.JavaConcurrency.synchronizedExamples;

import java.util.HashMap;
import java.util.Map;

public class BookingApp {
    private Map<String, Boolean> bookings;
    private Map<String, String> users;

    public BookingApp() {
        bookings = new HashMap<>();
        users = new HashMap<>();
    }

    public synchronized boolean book(String username, String bookingId) {
        if (!users.containsKey(username)) {
            System.out.println("User not authenticated.");
            return false;
        }

        if (bookings.containsKey(bookingId)) {
            System.out.println("Booking already taken.");
            return false;
        }

        bookings.put(bookingId, true);
        System.out.println("Booking successful for " + bookingId + " by " + username);
        return true;
    }

    public synchronized void cancelBooking(String username, String bookingId) {
        if (!users.containsKey(username)) {
            System.out.println("User not authenticated.");
            return;
        }

        if (!bookings.containsKey(bookingId)) {
            System.out.println("No booking found with ID " + bookingId);
            return;
        }

        bookings.remove(bookingId);
        System.out.println("Booking cancelled for " + bookingId + " by " + username);
    }

    public synchronized boolean authenticate(String username, String password) {
        // Simulated authentication
        if (password.equals("password")) {
            users.put(username, password);
            System.out.println("User " + username + " authenticated.");
            return true;
        } else {
            System.out.println("Authentication failed for " + username);
            return false;
        }
    }

    public static void main(String[] args) {
        BookingApp bookingApp = new BookingApp();

        // Simulated user authentication
        bookingApp.authenticate("user1", "password");

        // Simulated booking
        bookingApp.book("user1", "booking1");

        // Simulated cancel booking
        bookingApp.cancelBooking("user1", "booking1");
    }
}
