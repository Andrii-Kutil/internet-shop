package internetshop.controllers.order;

import internetshop.lib.Injector;
import internetshop.service.OrderService;
import internetshop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllOrdersController extends HttpServlet {
    private static Injector injector = Injector.getInstance("internetshop");
    private final OrderService orderService = (OrderService) injector
            .getInstance(OrderService.class);
    private final UserService userService = (UserService) injector
            .getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        req.setAttribute("orders", orderService.getUserOrders(userId));
        req.setAttribute("userLogin", userService.get(userId).getLogin());

        req.getRequestDispatcher("/WEB-INF/views/allOrders.jsp").forward(req, resp);
    }
}
