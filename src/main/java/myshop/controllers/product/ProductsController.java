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
import myshop.service.ProductService;
import myshop.service.UserService;

public class ProductsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("myshop");
    private final ProductService productService = (ProductService)
            injector.getInstance(ProductService.class);
    private final UserService userService = (UserService)
            injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<String> strings = new ArrayList<>();
        strings.add("wtani");
        strings.add("noski");
        strings.add("Honda");
        strings.add("CallGoti");
        strings.add("Mazda");
        strings.add("Roliki");
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
            productService.create(new Product(strings.get(3), "200"));
            productService.create(new Product(strings.get(4), "3000000"));
            productService.create(new Product(strings.get(5), "1679"));
        }
        List<Role.RoleName> roles = new ArrayList<>(userService
                .get((Long)req.getSession().getAttribute("user_id")).getRoles());
        String role = roles.get(roles.size() - 1).toString();
        req.setAttribute("roles", role);
        req.setAttribute("products", productService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/products/products.jsp").forward(req, resp);
    }
}
