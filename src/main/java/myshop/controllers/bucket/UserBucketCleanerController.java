package myshop.controllers.bucket;

        import myshop.lib.Injector;
        import myshop.service.BucketService;

        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

public class UserBucketCleanerController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("myshop");
    private static final Long USER_ID = 1L;
    BucketService bucketService = (BucketService) injector.getInstance(BucketService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        bucketService.clearBucket(USER_ID, Long.valueOf(req.getParameter("product_id")));
        resp.sendRedirect(req.getContextPath() + "/mybucket");
    }
}
