package dao;

import beans.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class InventoryTest {
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new InventoryImpl();
    }

    @Test
    void addItemTest() {
        List<Item> itemsBeforeAdd = inventory.getInventory();
        inventory.addItem("grips", "these are bat grips", 20);
        List<Item> itemsAfterAdd = inventory.getInventory();
        Assertions.assertEquals(itemsAfterAdd.size(), itemsBeforeAdd.size() + 1);
    }

    @Test
    void deleteItemTest_ItemNotPresent_CountRemainsSame() {
        List<Item> itemsBeforeAdd = inventory.getInventory();
        inventory.removeItem("random");
        List<Item> itemsAfterAdd = inventory.getInventory();
        Assertions.assertEquals(itemsAfterAdd.size(), itemsBeforeAdd.size());
    }
}
