package internetshop.dao;

import internetshop.model.Order;
import internetshop.model.User;
import java.util.List;

public interface OrderDao extends GenericDao<Order, Long> {
    List<Order> getUserOrders(User user);
}
