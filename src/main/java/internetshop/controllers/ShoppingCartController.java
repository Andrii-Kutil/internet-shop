package internetshop.controllers;

import internetshop.lib.Injector;
import internetshop.model.ShoppingCart;
import internetshop.model.User;
import internetshop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShoppingCartController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static Injector injector = Injector.getInstance("internetshop");
    private final ShoppingCartService shoppingCartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (shoppingCartService.getByUserId(USER_ID).isEmpty()) {
            shoppingCartService.create(new ShoppingCart(new User(USER_ID)));
        }
        req.setAttribute("productsInShoppingCart", shoppingCartService
                .getAllProducts(shoppingCartService.getByUserId(USER_ID).get()));
        req.getRequestDispatcher("/WEB-INF/views/allProductsInShopCart.jsp").forward(req, resp);
    }
}
