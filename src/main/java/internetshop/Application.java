package internetshop;

import internetshop.lib.Injector;
import internetshop.model.Product;
import internetshop.model.ShoppingCart;
import internetshop.model.User;
import internetshop.service.OrderService;
import internetshop.service.ProductService;
import internetshop.service.ShoppingCartService;
import internetshop.service.UserService;
import java.math.BigDecimal;

public class Application {
    private static Injector injector = Injector.getInstance("internetshop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        UserService userService = (UserService) injector.getInstance(UserService.class);
        Product product1 = new Product("Apple", new BigDecimal(10));
        Product product2 = new Product("Car", new BigDecimal(100000));
        productService.create(product1);
        productService.create(product2);
        User user1 = new User("Andrii");
        userService.create(user1);
        ShoppingCart shoppingCart1 = new ShoppingCart(user1);
        ShoppingCartService shoppingCartService = (ShoppingCartService)
                injector.getInstance(ShoppingCartService.class);
        shoppingCartService.create(shoppingCart1);
        shoppingCartService.addProduct(shoppingCart1, product1);
        shoppingCartService.addProduct(shoppingCart1, product2);
        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        orderService.completeOrder(shoppingCartService.getAllProducts(shoppingCartService
                        .getByUserId(user1.getId())), user1);
        System.out.println(orderService.getAll());
    }
}
