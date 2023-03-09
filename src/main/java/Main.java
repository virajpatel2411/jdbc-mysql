import beans.Item;
import dao.Inventory;
import dao.InventoryImpl;

import java.util.List;
import java.util.Scanner;

import static java.util.Objects.isNull;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inventory = new InventoryImpl();
        String name = null, description = null, itemId = null;
        int quantity = 0;
        System.out.println("Welcome to Item Inventory!");
        while (true) {
            System.out.println("Select any of the below actions to perform : ");
            System.out.println("1. Add Item");
            System.out.println("2. Fetch Item");
            System.out.println("3. Update Item");
            System.out.println("4. Delete Item");
            System.out.println("5. List all Items");
            System.out.println("6. Exit");
            int action = sc.nextInt();
            switch (action) {
                case 1:
                    System.out.println("Please enter details of item to Add : ");
                    System.out.println(" Name : ");
                    sc.nextLine();
                    name = sc.nextLine();
                    System.out.println(" Description : ");
                    description = sc.nextLine();
                    System.out.println(" Quantity : ");
                    quantity = sc.nextInt();
                    inventory.addItem(name, description, quantity);
                    break;
                case 2:
                    System.out.println("Please enter details of item to Fetch : ");
                    System.out.println(" ItemId : ");
                    sc.nextLine();
                    itemId = sc.nextLine();
                    Item item = inventory.getItem(itemId);
                    if (isNull(item)) {
                        System.out.println("No item present with given itemId");
                    } else {
                        System.out.println(item);
                    }
                    break;
                case 3:
                    System.out.println("Please enter details of item to Update : ");
                    System.out.println(" ItemId : ");
                    sc.nextLine();
                    itemId = sc.nextLine();
                    System.out.println(" Name : ");
                    name = sc.nextLine();
                    System.out.println(" Description : ");
                    description = sc.nextLine();
                    System.out.println(" Quantity : ");
                    quantity = sc.nextInt();
                    inventory.updateItem(itemId, name, description, quantity);
                    break;
                case 4:
                    System.out.println("Please enter details of item to Delete : ");
                    System.out.println(" ItemId : ");
                    sc.nextLine();
                    itemId = sc.nextLine();
                    inventory.removeItem(itemId);
                    break;
                case 5:
                    List<Item> items = inventory.getInventory();
                    if (items.isEmpty()) {
                        System.out.println("No items present in inventory");
                    } else {
                        System.out.println(items);
                    }
                    break;
                case 6:
                    inventory.close();
                    System.out.println("Thank you!");
                    return;
            }
        }
    }
}
