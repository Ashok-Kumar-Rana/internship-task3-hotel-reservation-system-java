import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        // Create a Scanner object to take input from the user
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();
        int choice; 

        do {
            System.out.println("\n===== Welcome to Hotel Reservation System =====");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel Your Booking");
            System.out.println("4. View All Current Bookings");
            System.out.println("5. Exit");
            System.out.print("Please enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 
            switch (choice) {
                case 1 -> {
                    System.out.println("\n--- Available Rooms ---");
                    hotel.showAvailableRooms();
                }
                case 2 -> {
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter the room number you want to book: ");
                    int roomNum = sc.nextInt();
                    sc.nextLine(); 
                    // Call method to book the selected room
                    hotel.bookRoom(name, roomNum);
                }
                case 3 -> {
                    System.out.print("Enter your name to cancel your booking: ");
                    String name = sc.nextLine();

                    // Call method to cancel the booking
                    hotel.cancelBooking(name);
                }
                case 4 -> {
                    System.out.println("\n--- All Current Bookings ---");
                    hotel.viewBookings();
                }
                case 5 -> {
                    System.out.println("\nThank you for using the Hotel Reservation System. Goodbye!");
                }
                default -> {
                    // If the user enters an invalid option
                    System.out.println("\nInvalid choice! Please enter a number between 1 and 5.");
                }
            }
        } while (choice != 5);
        sc.close();
    }
}
