package internetshop.controllers;

import internetshop.lib.Injector;
import internetshop.model.Role;
import internetshop.model.User;
import internetshop.service.UserService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectDataController extends HttpServlet {
    private static Injector injector = Injector.getInstance("internetshop");
    private final UserService userService = (UserService) injector.getInstance(UserService.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        User admin = new User("Admin", "admin", "admin");
        admin.setRoles(Set.of(Role.of("ADMIN")));
        userService.create(admin);
        req.getRequestDispatcher("/WEB-INF/views/injectAdmin.jsp").forward(req, resp);
    }
}
