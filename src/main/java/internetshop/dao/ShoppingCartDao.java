package internetshop.dao;

import internetshop.model.Product;
import internetshop.model.ShoppingCart;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartDao {
    ShoppingCart create(ShoppingCart shoppingCart);

    boolean delete(ShoppingCart shoppingCart, Product product);

    Optional<ShoppingCart> get(Long userId);

    List<Product> getAllProducts(ShoppingCart shoppingCart);

    ShoppingCart update(ShoppingCart shoppingCart);
}
