package internetshop.service.impl;

import internetshop.dao.ShoppingCartDao;
import internetshop.lib.Inject;
import internetshop.lib.Service;
import internetshop.model.Product;
import internetshop.model.ShoppingCart;
import internetshop.service.ShoppingCartService;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return shoppingCartDao.create(shoppingCart);
    }

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCart.getProducts().add(product);
        shoppingCartDao.update(shoppingCart);
        return shoppingCart;
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {

        if (shoppingCart.getProducts().remove(product)) {
            shoppingCartDao.update(shoppingCart);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getProducts().clear();
        shoppingCartDao.update(shoppingCart);
    }

    @Override

    public ShoppingCart getByUserId(Long userId) {
        return shoppingCartDao.get(userId).get();
    }

    @Override
    public List<Product> getAllProducts(ShoppingCart shoppingCart) {
        return shoppingCartDao.getAll().stream()
                .filter(s -> s.getId().equals(shoppingCart.getId()))
                .findAny().get().getProducts();
    }
}
