package controller;

import model.Bill;
import model.Room;
import service.*;

import java.util.List;

public class HotelController {
    private final RoomService roomService;
    private final CustomerService customerService;
    private final BillService billService;
    private final FoodItemService foodItemService;
    private final InventoryService inventoryService;
    private final FoodOrderService foodOrderService;

    public HotelController() {
//      Facade i.e Sub Services
        this.roomService = new RoomService();
        this.customerService = new CustomerService();
        this.billService = new BillService();
        this.foodItemService = new FoodItemService();
        this.inventoryService = new InventoryService();
        this.foodOrderService = new FoodOrderService();
    }

    public boolean checkIn(String name, String id, String phoneNumber, String address, int roomNumber) {
        boolean checkedIn = roomService.checkIn(roomNumber);
        if (checkedIn) {
            customerService.createCustomer(name, id, phoneNumber, address, roomNumber);
        }
        return checkedIn;
    }

    public void checkOut(int roomNumber) {
        roomService.checkOut(roomNumber);
        customerService.deleteCustomerByRoomNumber(roomNumber); // Delete customer
    }

    public void orderFood(String foodName, int quantity, String customerName) {
        foodOrderService.orderFood(foodName, quantity, customerName);
    }

    public Bill getBill(int roomNumber) {
        return billService.generateBill(roomNumber);
    }

    public void addInventoryItem(String itemName, int quantity) {
        inventoryService.addItem(itemName, quantity);
    }

    public void deleteInventoryItem(String itemName) {
        inventoryService.deleteItem(itemName);
    }

    public boolean checkRoomAvailability(int roomNumber) {
        return roomService.isRoomAvailable(roomNumber);
    }

    public List<Room> getAllAvailableRooms() {
        return roomService.getAllAvailableRooms();
    }

    public String getAllFoodItems() {
        return foodItemService.getAllItems();
    }

    public String getAllInventoryItems() {
        return inventoryService.getAllItems();
    }

    public void deleteFoodItem(String foodName) {
        foodItemService.deleteFoodItem(foodName);
    }

    public void addFoodItem(String foodName, double price) {
        foodItemService.addFoodItem(foodName, price);
    }
}
