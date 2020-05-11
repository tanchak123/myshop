package myshop.controllers.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myshop.lib.Injector;
import myshop.service.UserService;

public class UserController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static Injector injector = Injector.getInstance("myshop");
    private final UserService userService = (UserService)
            injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        if (userId == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        req.setAttribute("users", userService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/users/all.jsp").forward(req, resp);
    }
}

