package internetshop.controllers.product;

import internetshop.lib.Injector;
import internetshop.service.ProductService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductController extends HttpServlet {
    private static Injector injector = Injector.getInstance("internetshop");
    private final ProductService productService = (ProductService) injector
            .getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        Long productId = Long.valueOf(req.getParameter("id"));
        productService.delete(productId);
        req.setAttribute("products", productService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/editProducts.jsp").forward(req, resp);
    }
}
