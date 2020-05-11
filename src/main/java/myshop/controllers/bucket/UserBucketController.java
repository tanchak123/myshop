package myshop.controllers.bucket;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myshop.lib.Injector;
import myshop.service.BucketService;
import myshop.service.UserService;

public class UserBucketController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("myshop");
    private static final String USER_ID = "user_id";
    private final UserService userService = (UserService) injector.getInstance(UserService.class);
    private final BucketService bucketService = (BucketService)
            injector.getInstance(BucketService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        if (userService.getAll().size() == 0
                || userId == null) {
            resp.sendRedirect(req.getContextPath() + "/registration");
        } else {
            req.setAttribute("bucket", bucketService.get(userId));
            req.setAttribute("sum", bucketService.getSum(userId));
            req.getRequestDispatcher("/WEB-INF/views/buckets/bucket/myBucket.jsp")
                    .forward(req, resp);
        }
    }
}
