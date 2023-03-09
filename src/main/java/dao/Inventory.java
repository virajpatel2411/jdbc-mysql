package dao;

import beans.Item;

import java.util.List;

public interface Inventory {
    void addItem(String name, String description, int quantity);

    Item getItem(String itemId);

    void updateItem(String itemId, String name, String description, int quantity);

    void removeItem(String itemId);

    List<Item> getInventory();

    void close();
}
