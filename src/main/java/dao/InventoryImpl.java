package dao;

import beans.Item;
import utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InventoryImpl implements Inventory {
    private final Connection connection;
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String QUANTITY = "quantity";

    public InventoryImpl() {
        connection = ConnectionUtils.getConnection();
    }

    @Override
    public void addItem(String name, String description, int quantity) {
        final String query = "insert into items values (?, ?, ?, ?)";
        String itemId = UUID.randomUUID().toString();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, itemId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4, quantity);
            int rows = preparedStatement.executeUpdate();
            if (rows != 0) {
                System.out.println("Item added with id : " + itemId);
            } else {
                System.out.println("Failed to add item");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item getItem(String itemId) {
        final String query = "select * from items where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getString(ID));
                item.setName(resultSet.getString(NAME));
                item.setDescription(resultSet.getString(DESCRIPTION));
                item.setQuantity(resultSet.getInt(QUANTITY));
                return item;
            } else {
                return null;
            }
        } catch (SQLException e) {
            // no need to print stack strace as we are throwing exception which in turn will print stack trace
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateItem(String itemId, String name, String description, int quantity) {
        final String query = "update items set name=?, description=?, quantity=? where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setString(4, itemId);
            int rows = preparedStatement.executeUpdate();
            if (rows != 0) {
                System.out.println("Item with id : " + itemId + " updated");
            } else {
                System.out.println("Failed to update item with id : " + itemId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeItem(String itemId) {
        final String query = "delete from items where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, itemId);
            int rows = preparedStatement.executeUpdate();
            if (rows != 0) {
                System.out.println("Deleted item with id : " + itemId);
            } else {
                System.out.println("Failed to delete item with id : " + itemId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Item> getInventory() {
        final String query = "select * from items";
        final List<Item> items = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getString(ID));
                item.setName(resultSet.getString(NAME));
                item.setDescription(resultSet.getString(DESCRIPTION));
                item.setQuantity(resultSet.getInt(QUANTITY));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return items;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
