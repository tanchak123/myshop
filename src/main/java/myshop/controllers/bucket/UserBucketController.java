package myshop.controllers.bucket;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myshop.lib.Injector;
import myshop.model.Role;
import myshop.model.User;
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
        User user = userService.get((Long) req.getSession().getAttribute(USER_ID));
        for (Role.RoleName roleName : user.getRoles()) {
            if (roleName.toString().equals("ADMIN")) {
                req.setAttribute("roles", roleName.toString());
            }
        }
        if (userService.getAll().size() == 0
                || user.getId() == null) {
            resp.sendRedirect(req.getContextPath() + "/registration");
        } else {
            req.setAttribute("bucket", bucketService.get(user.getId()));
            req.setAttribute("sum", bucketService.getSum(user.getId()));
            req.getRequestDispatcher("/WEB-INF/views/buckets/bucket/myBucket.jsp")
                    .forward(req, resp);
        }
    }
}
