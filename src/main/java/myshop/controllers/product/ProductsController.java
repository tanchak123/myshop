package myshop.controllers.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myshop.lib.Injector;
import myshop.model.Product;
import myshop.model.Role;
import myshop.model.User;
import myshop.service.ProductService;
import myshop.service.UserService;

public class ProductsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("myshop");
    private final ProductService productService = (ProductService)
            injector.getInstance(ProductService.class);
    private final UserService userService = (UserService)
            injector.getInstance(UserService.class);
    private static final String USER_ID = "user_id";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<String> strings = new ArrayList<>();
        strings.add("Штаны");
        strings.add("Носки");
        strings.add("Автомобиль");
        strings.add("Велосипед");
        strings.add("Арбуз");
        strings.add("Монитор");
        boolean isElemet = false;
        for (String test1 : strings) {
            isElemet = productService.checkProducts(test1);
            if (isElemet) {
                break;
            }
        }

        if (!isElemet) {
            productService.create(new Product(strings.get(0), "40"));
            productService.create(new Product(strings.get(1), "20"));
            productService.create(new Product(strings.get(2), "2000000"));
            productService.create(new Product(strings.get(3), "5321"));
            productService.create(new Product(strings.get(4), "115"));
            productService.create(new Product(strings.get(5), "9999"));
        }
        User user = userService.get((Long) req.getSession().getAttribute(USER_ID));
        for (Role.RoleName roleName : user.getRoles()) {
            if (roleName.toString().equals("ADMIN")) {
                req.setAttribute("roles", roleName.toString());
            }
        }
        req.setAttribute("products", productService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/products/products.jsp").forward(req, resp);
    }
}
