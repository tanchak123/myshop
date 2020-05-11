package myshop.controllers.bucket;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myshop.lib.Injector;
import myshop.service.BucketService;

public class UserBucketCleanerController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("myshop");
    private static final String USER_ID = "user_id";
    BucketService bucketService = (BucketService) injector.getInstance(BucketService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        bucketService.clearBucket((Long) req.getSession().getAttribute(USER_ID),
                Long.valueOf(req.getParameter("product_id")));
        resp.sendRedirect(req.getContextPath() + "/mybucket");
    }
}
