package internetshop.controllers.order;

import internetshop.lib.Injector;
import internetshop.service.OrderService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteOrderController extends HttpServlet {
    private static Injector injector = Injector.getInstance("internetshop");
    private final OrderService orderService = (OrderService) injector
            .getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long orderId = Long.valueOf(req.getParameter("id"));
        orderService.delete(orderId);
        resp.sendRedirect(req.getContextPath() + "/orders/all");
    }
}
