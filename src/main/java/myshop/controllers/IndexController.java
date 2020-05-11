package myshop.controllers;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myshop.lib.Injector;
import myshop.model.Role;
import myshop.service.UserService;

public class IndexController extends HttpServlet {
    private static final String USER_ID = "user_id";
    Injector injector = Injector.getInstance("myshop");
    UserService userService = (UserService) injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String timeValue = LocalTime.now().toString().substring(0,8);
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        List<Role.RoleName> roles = new ArrayList<>(userService.get(userId).getRoles());
        String role = roles.get(roles.size() - 1).toString();
        req.setAttribute("time", timeValue);
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
