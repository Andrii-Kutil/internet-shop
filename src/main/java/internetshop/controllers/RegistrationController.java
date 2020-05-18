package internetshop.controllers;

import internetshop.lib.Injector;
import internetshop.model.Role;
import internetshop.model.ShoppingCart;
import internetshop.model.User;
import internetshop.service.ShoppingCartService;
import internetshop.service.UserService;
import internetshop.util.HashUtil;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationController extends HttpServlet {
    private static Injector injector = Injector.getInstance("internetshop");
    private final UserService userService = (UserService) injector.getInstance(UserService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String pwd = req.getParameter("pwd");
        String repeatPwd = req.getParameter("pwd-repeat");
        if (pwd.equals(repeatPwd)) {
            if (userService.findByLogin(login).isEmpty()) {
                User user = new User(name, login);
                user.setSalt(HashUtil.getSalt());
                user.setPassword(HashUtil.hashPassword(pwd, user.getSalt()));
                user.setRole(Set.of(Role.of("USER")));
                userService.create(user);
                shoppingCartService.create(new ShoppingCart(user.getId()));
                resp.sendRedirect(req.getContextPath() + "/");
            } else {
                req.setAttribute("message", "This login has already existed. "
                        + "Please try again!");
                req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("message", "Your password and repeat password aren't the same."
                    + "Please try again!");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        }
    }
}
