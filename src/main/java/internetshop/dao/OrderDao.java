package internetshop.dao;

import internetshop.model.Order;
import internetshop.model.Product;
import internetshop.model.User;
import java.util.List;
import java.util.Optional;

public interface OrderDao {
    Order completeOrder(List<Product> products, User user);

    List<Order> getUserOrders(User user);

    Optional<Order> get(Long id);

    List<Order> getAll();

    boolean delete(Long id);
}
