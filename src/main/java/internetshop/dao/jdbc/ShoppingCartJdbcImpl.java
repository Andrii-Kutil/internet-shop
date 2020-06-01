package internetshop.dao.jdbc;

import internetshop.dao.ShoppingCartDao;
import internetshop.exceptions.DataProcessingException;
import internetshop.lib.Dao;
import internetshop.model.Product;
import internetshop.model.ShoppingCart;
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
public class ShoppingCartJdbcImpl implements ShoppingCartDao {

    @Override
    public ShoppingCart create(ShoppingCart cart) {
        String query = "INSERT INTO shopping_carts (user_id) VALUES(?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, cart.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                cart.setId(resultSet.getLong(1));
            }
            setProducts(cart);
            return cart;
        } catch (SQLException e) {
            throw new DataProcessingException("Shopping cart was not created", e);
        }
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        String query = "SELECT * from shopping_carts where user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getShoppingCartFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Cart was not got", e);
        }
    }

    @Override
    public ShoppingCart update(ShoppingCart cart) {
        deleteProducts(cart.getId());
        setProducts(cart);
        return cart;
    }

    @Override
    public boolean delete(Long id) {
        deleteProducts(id);
        String query = "DELETE FROM shopping_carts WHERE cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Cart was not deleted", ex);
        }
        return false;
    }

    @Override
    public List<ShoppingCart> getAll() {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        String query = "SELECT * FROM shopping_carts";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                shoppingCarts.add(getShoppingCartFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Carts were not found", ex);
        }
        return shoppingCarts;
    }

    private ShoppingCart getShoppingCartFromResultSet(ResultSet rs) throws SQLException {
        Long cartId = rs.getLong("cart_id");
        Long userId = rs.getLong("user_id");
        ShoppingCart shoppingCart = new ShoppingCart(cartId, userId);
        shoppingCart.setProducts(getProducts(cartId));
        return shoppingCart;
    }

    private List<Product> getProducts(Long cartId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT product_id, name, "
                + "price FROM shopping_carts_products AS cart INNER JOIN products "
                + "ON  cart.product_id = products.id WHERE cart.cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, cartId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("product_id");
                String name = resultSet.getString("name");
                BigDecimal price = resultSet.getBigDecimal("price");
                products.add(new Product(name, price, id));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Products were not given", e);
        }
        return products;
    }

    private void setProducts(ShoppingCart shoppingCart) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO shopping_carts_products"
                    + "(cart_id, product_id) VALUES(?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            for (Product product : shoppingCart.getProducts()) {
                statement.setLong(1, shoppingCart.getId());
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Products were not added", e);
        }
    }

    private void deleteProducts(Long cartId) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM shopping_carts_products WHERE cart_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, cartId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Products were nit deleted", e);
        }
    }
}
