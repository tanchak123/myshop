package myshop.controllers.images;

import myshop.lib.Injector;
import myshop.model.Product;
import myshop.service.ProductService;
import myshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ImageCreateController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("myshop");
    private final ProductService productService = (ProductService) injector
            .getInstance(ProductService.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("product_id", req.getParameter("product_id"));
        req.getRequestDispatcher("/WEB-INF/views/products/create/imgCreator.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Product product = productService.get(Long.valueOf(req.getParameter("product_id")));
        product.setImage(req.getParameter("img_url"));
        productService.update(product);
        req.setAttribute("message", "Продукт успешно добавлен");
        resp.sendRedirect(req.getContextPath() + "/products");
    }
}
