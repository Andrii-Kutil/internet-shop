package internetshop.controllers;

import internetshop.lib.Injector;
import internetshop.model.Product;
import internetshop.model.User;
import internetshop.service.ProductService;
import internetshop.service.UserService;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectDataController extends HttpServlet {
    private static Injector injector = Injector.getInstance("internetshop");
    private final UserService userService = (UserService) injector.getInstance(UserService.class);
    private final ProductService productService = (ProductService)
            injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User bob = new User("Bob");
        User alisa = new User("Alisa");
        userService.create(bob);
        userService.create(alisa);
        Product banana = new Product("banana", new BigDecimal(45));
        Product car = new Product("car", new BigDecimal(500000));
        Product flat = new Product("flat", new BigDecimal(3000000));
        productService.create(banana);
        productService.create(car);
        productService.create(flat);
        req.getRequestDispatcher("/WEB-INF/views/injectData.jsp").forward(req, resp);
    }
}
