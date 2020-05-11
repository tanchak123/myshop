package myshop.controllers.product;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myshop.lib.Injector;
import myshop.model.Product;
import myshop.service.ProductService;

public class ProductCreateController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector injector = Injector.getInstance("myshop");
    private final ProductService productService = (ProductService)
            injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/products/create/creater.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String pricer = req.getParameter("price");
        if (Pattern.compile("[A-Za-z]").matcher(pricer).find()
                || pricer.length() == 0) {
            req.setAttribute("message", "Неправильно введены данные :(");
            req.getRequestDispatcher("/WEB-INF/views/products/create/creater.jsp")
                    .forward(req, resp);
        }
        if (!productService.checkProducts(name)) {
            productService.create(new Product(name, pricer));
            resp.sendRedirect(req.getContextPath() + "/products");
        } else {
            req.setAttribute("message", "Неправильно введены данные :(");
            req.getRequestDispatcher("/WEB-INF/views/products/create/creater.jsp")
                    .forward(req, resp);
        }
    }
}
