package internetshop.dao.impl;

import internetshop.dao.OrderDao;
import internetshop.db.Storage;
import internetshop.lib.Dao;
import internetshop.model.Order;
import internetshop.model.User;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order create(Order order) {
        OptionalInt index = IntStream.range(0, Storage.shoppingCarts.size())
                .filter(i -> Storage.shoppingCarts.get(i).getUser().getId()
                        .equals(order.getUser().getId())).findFirst();
        Storage.shoppingCarts.remove(index.getAsInt());
        Storage.addOrder(order);
        return order;
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return Storage.orders.stream()
                .filter(o -> o.getUser().getId().equals(user.getId()))
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
