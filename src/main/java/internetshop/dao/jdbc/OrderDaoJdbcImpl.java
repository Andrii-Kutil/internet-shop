package internetshop.dao.jdbc;

import internetshop.dao.OrderDao;
import internetshop.exceptions.DataProcessingException;
import internetshop.lib.Dao;
import internetshop.model.Order;
import internetshop.model.Product;
import internetshop.util.ConnectionUtil;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {

    @Override
    public List<Order> getUserOrders(Long userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            throw new DataProcessingException("User's orders were not got", e);
        }
    }

    @Override
    public Order create(Order order) {
        String query = "INSERT INTO orders (user_id) VALUES(?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
            addProductsToOrder(order);
            return order;
        } catch (SQLException e) {
            throw new DataProcessingException("Order was not created", e);
        }
    }

    @Override
    public Optional<Order> get(Long id) {
        String query = "SELECT * FROM orders WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getOrderFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Order was not got", e);
        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            throw new DataProcessingException("Orders were not got", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        deleteProductsFromOrder(id);
        String query = "DELETE FROM orders WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Order was not deleted", e);
        }
    }

    @Override
    public Order update(Order order) {
        deleteProductsFromOrder(order.getId());
        addProductsToOrder(order);
        return order;
    }

    private void addProductsToOrder(Order order) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Product product : order.getProducts()) {
                String query = "INSERT INTO orders_products(order_id, product_id) "
                        + "values(?,?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setLong(1, order.getId());
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Product was not added in order", e);
        }
    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Long orderId = resultSet.getLong("order_id");
        Long userId = resultSet.getLong("user_id");
        return new Order(userId, getProductsOfOrder(orderId), orderId);
    }

    private List<Product> getProductsOfOrder(Long orderId) {
        String query = "SELECT products.id AS id, products.name AS name, products.price AS price "
                + "FROM orders_products INNER JOIN products "
                + "ON  orders_products.product_id = id WHERE orders_products.order_id = ?";
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long productId = resultSet.getLong("id");
                String name = resultSet.getString("name");
                BigDecimal price = resultSet.getBigDecimal("price");
                products.add(new Product(name, price, productId));
            }
            return products;
        } catch (SQLException e) {
            throw new DataProcessingException("Products were not got from order", e);
        }
    }

    private void deleteProductsFromOrder(Long orderId) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM orders_products WHERE order_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Products were not deleted from order", e);
        }
    }
}
