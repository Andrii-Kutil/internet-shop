package internetshop.controllers.product;

import internetshop.lib.Injector;
import internetshop.model.Product;
import internetshop.service.ProductService;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class AddProductController extends HttpServlet {
    private static Injector injector = Injector.getInstance("internetshop");
    private final ProductService productService = (ProductService) injector
            .getInstance(ProductService.class);
    private final Logger logger = Logger.getLogger(AddProductController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        try {
            BigDecimal price = new BigDecimal(req.getParameter("price"));
            productService.create(new Product(name, price));
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            req.setAttribute("message", "Please, use digits in price");
        }
        req.setAttribute("products", productService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/editProducts.jsp").forward(req, resp);
    }
}
