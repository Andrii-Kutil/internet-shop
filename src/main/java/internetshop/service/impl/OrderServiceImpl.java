package internetshop.service.impl;

import internetshop.dao.OrderDao;
import internetshop.lib.Inject;
import internetshop.lib.Service;
import internetshop.model.Order;
import internetshop.model.Product;
import internetshop.service.OrderService;
import internetshop.service.ShoppingCartService;
import internetshop.service.UserService;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Inject
    private ShoppingCartService cartService;

    @Inject
    private UserService userService;

    @Override
    public Order completeOrder(List<Product> products, Long userId) {
        List<Product> newListOProd = List.copyOf(products);
        Order order = new Order(newListOProd, userId);
        cartService.getByUserId(userId)
                .getProducts().clear();
        return orderDao.create(order);
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderDao.getUserOrders(userId);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).get();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
