package internetshop.controller.order;

import internetshop.lib.Injector;
import internetshop.service.OrderService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetailsOrderController extends HttpServlet {
    private static Injector injector = Injector.getInstance("internetshop");
    private final OrderService orderService = (OrderService) injector
            .getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long orderId = Long.valueOf(req.getParameter("id"));
        req.setAttribute("order", orderService.get(orderId));
        req.getRequestDispatcher("/WEB-INF/views/detailsOrder.jsp").forward(req, resp);
    }
}
