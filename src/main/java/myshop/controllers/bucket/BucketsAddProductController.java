package myshop.controllers.bucket;

import myshop.lib.Injector;
import myshop.service.BucketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BucketsAddProductController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static Injector injector = Injector.getInstance("myshop");
    private BucketService bucketService = (BucketService) injector.getInstance(BucketService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long user_id = (long) req.getSession().getAttribute(USER_ID);
        Long bucket_id = bucketService.get(user_id).getId();
        Long product_id = Long.valueOf(req.getParameter("product_id"));
        bucketService.add(product_id, bucket_id);
        resp.sendRedirect(req.getContextPath() + "/products");
    }
}
