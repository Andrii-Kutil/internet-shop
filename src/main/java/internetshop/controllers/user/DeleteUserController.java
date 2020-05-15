package internetshop.controllers.user;

import internetshop.lib.Injector;
import internetshop.model.Order;
import internetshop.service.OrderService;
import internetshop.service.ShoppingCartService;
import internetshop.service.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserController extends HttpServlet {
    private static Injector injector = Injector.getInstance("internetshop");
    private final UserService userService = (UserService) injector.getInstance(UserService.class);
    private final ShoppingCartService shoppingCartService = (ShoppingCartService)
            injector.getInstance(ShoppingCartService.class);
    private final OrderService orderService = (OrderService)
            injector.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long userId = Long.parseLong(req.getParameter("id"));
        Long id = shoppingCartService.getByUserId(userId).getId();
        shoppingCartService.delete(id);
        List<Order> orderList = orderService.getUserOrders(userService.get(userId).getId());
        for (Order order : orderList) {
            orderService.delete(order.getId());
        }
        userService.delete(userId);
        resp.sendRedirect(req.getContextPath() + "/users/all");
    }
}
