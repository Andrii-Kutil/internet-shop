package internetshop.controllers;

import internetshop.lib.Injector;
import internetshop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexController extends HttpServlet {
    private static Injector injector = Injector.getInstance("internetshop");
    private final UserService userService = (UserService) injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        req.setAttribute("userName", userService.get(userId).getName());
        req.getRequestDispatcher("WEB-INF/views/index.jsp").forward(req, resp);
    }
}
