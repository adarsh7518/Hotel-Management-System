package service;

import model.Inventory;
public class InventoryService {
    public boolean addItem(String itemName, int quantity) {
        return Inventory.addItem(itemName, quantity);
    }

    public void deleteItem(String itemName) {
        Inventory.deleteItem(itemName);
    }

    public String getAllItems() {
        return Inventory.getAllItems();
    }
}
