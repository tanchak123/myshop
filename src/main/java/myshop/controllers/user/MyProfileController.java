package myshop.controllers.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myshop.lib.Injector;
import myshop.model.Role;
import myshop.service.UserService;

public class MyProfileController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static Injector injector = Injector.getInstance("myshop");
    private final UserService userService = (UserService)
            injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        List<Role.RoleName> roles = new ArrayList<>(userService.get(userId).getRoles());
        String role = roles.get(roles.size() - 1).toString();
        req.setAttribute("ses_id", userId);
        req.setAttribute("user", userService.get(userId));
        req.setAttribute("roles", role);
        req.getRequestDispatcher("WEB-INF/views/users/myprofile.jsp").forward(req, resp);
    }
}
