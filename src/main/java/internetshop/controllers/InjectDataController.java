package internetshop.controllers;

import internetshop.lib.Injector;
import internetshop.model.Product;
import internetshop.service.ProductService;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectDataController extends HttpServlet {

    private static Injector injector = Injector.getInstance("internetshop");
    private final ProductService productService = (ProductService)
            injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Product banana = new Product("banana", new BigDecimal(45));
        Product meat = new Product("meat", new BigDecimal(120));
        Product dress = new Product("dress", new BigDecimal(499));
        Product mobile = new Product("mobile", new BigDecimal(9999));
        productService.create(banana);
        productService.create(meat);
        productService.create(dress);
        productService.create(mobile);
        req.getRequestDispatcher("/WEB-INF/views/injectData.jsp").forward(req, resp);
    }
}
