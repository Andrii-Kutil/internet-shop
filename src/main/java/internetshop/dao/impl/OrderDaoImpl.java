package internetshop.dao.impl;

import internetshop.dao.OrderDao;
import internetshop.db.Storage;
import internetshop.lib.Dao;
import internetshop.model.Order;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order create(Order order) {
        Storage.addOrder(order);
        return order;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return Storage.orders.stream()
                .filter(o -> o.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Order> get(Long id) {
        return Storage.orders.stream()
                .filter(o -> o.getId().equals(id))
                .findAny();
    }

    @Override
    public Order update(Order order) {
        IntStream.range(0, Storage.orders.size())
                .filter(i -> order.getId().equals(Storage.products.get(i).getId()))
                .forEach(i -> Storage.orders.set(i, order));
        return order;
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.orders.remove(get(id).get());
    }
}
