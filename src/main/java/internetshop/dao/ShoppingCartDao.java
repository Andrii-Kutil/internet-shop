package internetshop.dao;

import internetshop.model.Product;
import internetshop.model.ShoppingCart;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartDao {
    ShoppingCart create(ShoppingCart shoppingCart);

    ShoppingCart add(ShoppingCart shoppingCart, Product product);

    boolean delete(ShoppingCart shoppingCart, Product product);

    void clear(ShoppingCart shoppingCart);

    Optional<ShoppingCart> get(Long userId);

    List<Product> getAllProducts(ShoppingCart shoppingCart);
}
