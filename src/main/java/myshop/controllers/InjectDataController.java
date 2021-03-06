package myshop.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myshop.lib.Injector;
import myshop.model.Role;
import myshop.model.User;
import myshop.service.UserService;

public class InjectDataController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("myshop");
    private final UserService userService = (UserService) injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (userService.userExisting("a")) {
            resp.sendRedirect("/login");
            return;
        }
        User sanaya = new User("Sanaya", "Vasya");
        userService.create(sanaya);
        User admin = new User("a", "a");
        admin.setRoles(new HashSet<>(List.of(Role.RoleName.USER, Role.RoleName.ADMIN)));
        userService.create(admin);
        userService.create(new User("Sanya", "Vasya"));
        userService.create(new User("u", "u"));
        List<User> users = userService.getAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/views/injectData.jsp").forward(req, resp);
    }
}
