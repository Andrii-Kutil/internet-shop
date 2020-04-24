package internetshop.dao;

import internetshop.model.Order;
import internetshop.model.User;
import java.util.List;
import java.util.Optional;

public interface OrderDao {

    Order create(Order order);

    List<Order> getUserOrders(User user);

    Optional<Order> get(Long id);

    List<Order> getAll();

    boolean delete(Long id);
}
