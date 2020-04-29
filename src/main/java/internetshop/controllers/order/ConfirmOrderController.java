package internetshop.controllers.order;

import internetshop.lib.Injector;
import internetshop.model.ShoppingCart;
import internetshop.service.OrderService;
import internetshop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmOrderController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static Injector injector = Injector.getInstance("internetshop");
    private final ShoppingCartService shoppingCartService = (ShoppingCartService)
            injector.getInstance(ShoppingCartService.class);
    private final OrderService orderService = (OrderService)
            injector.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(USER_ID).get();
        if (!shoppingCart.getProducts().isEmpty()) {
            orderService.completeOrder(shoppingCart.getProducts(), shoppingCart.getUser());
        }
        resp.sendRedirect(req.getContextPath() + "/orders/all");
    }
}
