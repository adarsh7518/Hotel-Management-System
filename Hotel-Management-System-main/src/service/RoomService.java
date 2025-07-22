package service;

import model.Bill;
import model.Customer;
import model.Room;
import java.util.List;

public class RoomService {
    public boolean checkIn(int roomNumber) {
        Room room = Room.getRoomByNumber(roomNumber);
        if (room != null && !room.isBooked()) {
            room.setBookedStatus(true);
            room.updateRoomStatusInDatabase();
            return true;
        }
        return false;
    }

    public void checkOut(int roomNumber) {
        Room room = Room.getRoomByNumber(roomNumber);
        if (room != null && room.isBooked()) {
            // Check out the customer
            room.setBookedStatus(false);
            room.updateRoomStatusInDatabase();

            // Get customer name
            CustomerService customerService = new CustomerService();
            Customer customer = customerService.getCustomerByRoomNumber(roomNumber);
            if (customer != null) {
                String customerName = customer.getName();

                // Delete bill for the customer from MongoDB
                Bill.deleteBillByCustomerName(customerName);
            }
        }
    }

    public boolean isRoomAvailable(int roomNumber) {
        Room room = Room.getRoomByNumber(roomNumber);
        return room != null && !room.isBooked();
    }

    public List<Room> getAllAvailableRooms() {
        return Room.getAllAvailableRooms();
    }
}
