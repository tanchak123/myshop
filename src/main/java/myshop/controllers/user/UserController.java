package myshop.controllers.user;

import java.io.IOException;
import java.util.ArrayList;
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

public class UserController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static Injector injector = Injector.getInstance("myshop");
    private final UserService userService = (UserService)
            injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = userService.get((Long) req.getSession().getAttribute(USER_ID));
        for (Role.RoleName roleName : user.getRoles()) {
            if (roleName.toString().equals("ADMIN")) {
                req.setAttribute("roles", roleName.toString());
            }
        }
        req.setAttribute("users", userService.getAll());
        if (user.getId() == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        if ("post".equals(req.getParameter("case"))) {
            user = userService.get(Long.parseLong(req.getParameter("user_id")));
            String message = "Пользователя с логином '" + user.getLogin()
                    + "'больше не администратор";
            for (Role.RoleName roleName : user.getRoles()) {
                if (roleName.equals(Role.RoleName.ADMIN)) {
                    req.setAttribute("message", message);
                    user.setRoles(new HashSet<>(List.of(Role.RoleName.USER)));
                    userService.update(user);
                    req.setAttribute("users", userService.getAll());
                    req.getSession().setAttribute("message", message);
                    resp.sendRedirect(req.getContextPath() + "/users");
                    return;
                }
            }
            user.setRoles(new HashSet<>(List.of(Role.RoleName.USER,
                    Role.RoleName.ADMIN)));
            userService.update(user);
            message = "Пользователь с логином '" + user.getLogin()
                    + "' успешно получил должность администратора";
            req.setAttribute("users", userService.getAll());
            req.getSession().setAttribute("message", message);
            resp.sendRedirect(req.getContextPath() + "/users");
            return;
        }
        req.setAttribute("message", req.getSession().getAttribute("message"));
        req.getSession().removeAttribute("message");
        req.getRequestDispatcher("/WEB-INF/views/users/all.jsp").forward(req, resp);
    }

}

