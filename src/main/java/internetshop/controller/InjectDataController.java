package internetshop.controller;

import internetshop.lib.Injector;
import internetshop.model.Product;
import internetshop.model.ShoppingCart;
import internetshop.model.User;
import internetshop.service.ProductService;
import internetshop.service.ShoppingCartService;
import internetshop.service.UserService;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectDataController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static Injector injector = Injector.getInstance("internetshop");
    private final UserService userService = (UserService) injector.getInstance(UserService.class);
    private final ProductService productService = (ProductService)
            injector.getInstance(ProductService.class);
    private final ShoppingCartService shoppingCartService = (ShoppingCartService)
            injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User bob = new User("Bob");
        User alisa = new User("Alisa");
        User defaultUser = new User(USER_ID, "default", "NO_NAME");
        userService.create(bob);
        userService.create(alisa);
        shoppingCartService.create(new ShoppingCart(defaultUser));
        Product banana = new Product("banana", new BigDecimal(45));
        Product car = new Product("car", new BigDecimal(500000));
        Product flat = new Product("flat", new BigDecimal(3000000));
        productService.create(banana);
        productService.create(car);
        productService.create(flat);
        req.getRequestDispatcher("/WEB-INF/views/injectData.jsp").forward(req, resp);
    }
}
