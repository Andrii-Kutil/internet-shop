package internetshop.controllers.shoppingcart;

import internetshop.lib.Injector;
import internetshop.service.ProductService;
import internetshop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductFromShoppingCart extends HttpServlet {
    private static final Long USER_ID = 0L;
    private static Injector injector = Injector.getInstance("internetshop");
    private final ShoppingCartService shoppingCartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);
    private final ProductService productService = (ProductService) injector
            .getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long productId = Long.valueOf(req.getParameter("id"));
        shoppingCartService.deleteProduct(shoppingCartService.getByUserId(USER_ID),
                productService.get(productId));
        resp.sendRedirect(req.getContextPath() + "/shoppingcart/products/all");
    }
}
