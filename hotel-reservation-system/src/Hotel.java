import java.io.*;
import java.util.*;

public class Hotel {
    private List<Room> rooms = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    public Hotel() {
        // Rooms initialize
        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Standard"));
        rooms.add(new Room(201, "Deluxe"));
        rooms.add(new Room(202, "Deluxe"));
        rooms.add(new Room(301, "Suite"));
        rooms.add(new Room(302, "Suite"));

        loadBookings(); // file se bookings load
    }

    // Show available rooms in neat table format
    public void showAvailableRooms() {
        System.out.println("\n---------------------------------------");
        System.out.printf("| %-11s | %-9s | %-11s |\n", "Room Number", "Room Type", "Availability");
        System.out.println("---------------------------------------");

        for (Room room : rooms) {
            String status = room.isBooked() ? "Booked" : "Available";
            System.out.printf("| %-11d | %-9s | %-11s |\n", room.getRoomNumber(), room.getType(), status);
        }
        System.out.println("---------------------------------------");
    }

    // Book room
    public void bookRoom(String customerName, int roomNum) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNum && !room.isBooked()) {
                room.setBooked(true);
                Booking booking = new Booking(customerName, roomNum, room.getType());
                bookings.add(booking);
                System.out.println("\n---------------------------------------");
                System.out.println("Payment successful! Booking confirmed.");
                System.out.println("Customer: " + customerName);
                System.out.println("Room Number: " + roomNum);
                System.out.println("Room Type: " + room.getType());
                System.out.println("---------------------------------------");
                saveBookings();
                return;
            }
        }
        System.out.println("\nRoom not available or invalid room number.");
    }

    // Cancel booking
    public void cancelBooking(String customerName) {
        Booking toRemove = null;
        for (Booking b : bookings) {
            if (b.getCustomerName().equalsIgnoreCase(customerName)) {
                toRemove = b;
                break;
            }
        }
        if (toRemove != null) {
            bookings.remove(toRemove);
            for (Room room : rooms) {
                if (room.getRoomNumber() == toRemove.getRoomNumber()) {
                    room.setBooked(false);
                    break;
                }
            }
            System.out.println("\n---------------------------------------");
            System.out.println("Booking canceled and refund simulated.");
            System.out.println("Customer: " + customerName);
            System.out.println("Room Number: " + toRemove.getRoomNumber());
            System.out.println("Room Type: " + toRemove.getRoomType());
            System.out.println("---------------------------------------");
            saveBookings();
        } else {
            System.out.println("\nNo booking found with this name.");
        }
    }

    // View all bookings in table format
    public void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("\nNo bookings yet.");
            return;
        }
        System.out.println("\n---------------------------------------");
        System.out.printf("| %-15s | %-11s | %-9s |\n", "Customer Name", "Room Number", "Room Type");
        System.out.println("---------------------------------------");

        for (Booking b : bookings) {
            System.out.printf("| %-15s | %-11d | %-9s |\n", b.getCustomerName(), b.getRoomNumber(), b.getRoomType());
        }
        System.out.println("---------------------------------------");
    }

    // Save bookings to file
    private void saveBookings() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("bookings.txt"))) {
            for (Booking b : bookings) {
                bw.write(b.getCustomerName() + "," + b.getRoomNumber() + "," + b.getRoomType());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load bookings from file
    private void loadBookings() {
        try (BufferedReader br = new BufferedReader(new FileReader("bookings.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    int roomNum = Integer.parseInt(parts[1]);
                    String type = parts[2];
                    bookings.add(new Booking(name, roomNum, type));
                    for (Room room : rooms) {
                        if (room.getRoomNumber() == roomNum) {
                            room.setBooked(true);
                            break;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            System.out.println("Error loading bookings: " + e.getMessage());
        }
    }
}
