package service;

import model.FoodOrder;

public class FoodOrderService {
    private final FoodItemService foodItemService;

    public FoodOrderService() {
        this.foodItemService = new FoodItemService();
    }

    public void orderFood(String foodName, int quantity, String customerName) {
        // Retrieve the food item from the database
        // Create a new food order with the retrieved price
        FoodOrder foodOrder = new FoodOrder(foodName, quantity, customerName);
        foodOrder.saveToDatabase();
    }
}
