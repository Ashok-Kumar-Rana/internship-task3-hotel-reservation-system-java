// Booking.java
public class Booking {
    private String customerName;
    private int roomNumber;
    private String roomType;

    public Booking(String customerName, int roomNumber, String roomType) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {   //  getter for roomType
        return roomType;
    }

    @Override
    public String toString() {
        return "Booking: " + customerName + " -> Room " + roomNumber + " (" + roomType + ")";
    }
}
