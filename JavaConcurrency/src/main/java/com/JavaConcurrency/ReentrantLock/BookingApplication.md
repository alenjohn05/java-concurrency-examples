Certainly! Here's an example of a booking application where multiple users can book tickets concurrently, and a thread-safe mechanism is employed using `ReentrantLock` to handle these bookings:

```java
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
```

In this example:

- `TicketBookingSystem` class represents the booking system. It has a `bookTicket` method that allows users to book tickets. The `bookTicket` method is synchronized using `ReentrantLock` to ensure thread safety when multiple users book tickets concurrently.
- `User` class represents a user who books a ticket. Each user is a separate thread. When a user thread runs, it books a ticket using the `TicketBookingSystem`.
- In the `BookingApplication` class, two user threads are created to simulate concurrent ticket bookings.
- The `join` method is used to wait for both user threads to finish their bookings before printing the final bookings.

This example demonstrates how `ReentrantLock` can be used to ensure thread safety in a booking application where multiple users are booking tickets concurrently, preventing race conditions and ensuring data consistency in the booking process.