package service;

import model.Bill;
import model.Customer;
import model.Room;
import model.FoodOrder;

import java.util.List;

public class BillService {
    public Bill generateBill(int roomNumber) {
        CustomerService customerService=new CustomerService();
        Customer customer = customerService.getCustomerByRoomNumber(roomNumber);
        if (customer != null) {
            Room room = Room.getRoomByNumber(roomNumber);
            if (room != null && room.isBooked()) {
                double roomPrice = room.getPrice(); // Retrieve the room price
                double foodOrdersPrice = calculateTotalFoodOrdersCost(customer.getName()); // Retrieve total cost of food orders

                double totalAmount = roomPrice + foodOrdersPrice; // Calculate total amount

                Bill bill = new Bill(customer.getName(), roomPrice, foodOrdersPrice, totalAmount); // Create bill for the customer
                bill.saveToMongoDB(); // Save the bill to MongoDB
                return bill;
            }
        }
        return null; // Handle case where customer or room is not found or room is not booked
    }

    private double calculateTotalFoodOrdersCost(String customerName) {
        List<FoodOrder> orders = FoodOrder.getOrdersByCustomer(customerName);
        return orders.stream().mapToDouble(FoodOrder::getTotalCost).sum();
    }
}
