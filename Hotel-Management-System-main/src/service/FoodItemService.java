package service;

import model.FoodItem;
public class FoodItemService {
    public void addFoodItem(String foodName, double price) {
        FoodItem foodItem = new FoodItem(foodName, price);
        foodItem.saveToDatabase();
    }

    public void deleteFoodItem(String foodName) {
        FoodItem.deleteItem(foodName);
    }

    public String getAllItems() {
        return FoodItem.getAllItems();
    }

}
