package myshop.controllers.bucket;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myshop.lib.Injector;
import myshop.service.BucketService;

public class BucketsAddProductController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static Injector injector = Injector.getInstance("myshop");
    private BucketService bucketService = (BucketService) injector.getInstance(BucketService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long userId = (long) req.getSession().getAttribute(USER_ID);
        Long bucketId = bucketService.get(userId).getId();
        Long productId = Long.valueOf(req.getParameter("product_id"));
        bucketService.add(productId, bucketId);
        resp.sendRedirect(req.getContextPath() + "/products");
    }
}
