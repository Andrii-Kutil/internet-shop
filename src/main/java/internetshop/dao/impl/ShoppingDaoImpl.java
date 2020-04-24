package internetshop.dao.impl;

import internetshop.dao.ShoppingCartDao;
import internetshop.db.Storage;
import internetshop.lib.Dao;
import internetshop.model.Product;
import internetshop.model.ShoppingCart;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class ShoppingDaoImpl implements ShoppingCartDao {

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        Storage.addShoppingCart(shoppingCart);
        return shoppingCart;
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        IntStream.range(0, Storage.shoppingCarts.size())
                .filter(i -> shoppingCart.getId()
                        .equals(Storage.shoppingCarts.get(i).getId()))
                .forEach(i -> Storage.shoppingCarts.set(i, shoppingCart));
        return shoppingCart;
    }

    @Override
    public boolean delete(ShoppingCart shoppingCart, Product product) {
        return Storage.shoppingCarts.stream()
                .filter(s -> s.getId().equals(shoppingCart.getId()))
                .findAny().get().getProducts().remove(product);
    }

    @Override
    public Optional<ShoppingCart> get(Long userId) {
        return Storage.shoppingCarts.stream()
                .filter(s -> s.getUser().getId().equals(userId))
                .findAny();
    }

    @Override
    public List<Product> getAllProducts(ShoppingCart shoppingCart) {
        return Storage.shoppingCarts.stream()
                .filter(s -> s.getId().equals(shoppingCart.getId()))
                .findAny().get().getProducts();
    }
}
