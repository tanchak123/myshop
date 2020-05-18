package myshop.controllers.user;

import myshop.lib.Injector;
import myshop.model.Role;
import myshop.model.User;
import myshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class UserAdminController extends HttpServlet {
    Injector injector = Injector.getInstance("myshop");
    UserService userService = (UserService) injector.getInstance(UserService.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = userService.get(Long.valueOf(req.getParameter("user_id")));
        for (Role.RoleName roleName : user.getRoles()) {
            if (roleName.equals(Role.RoleName.ADMIN)) {
                req.setAttribute("message", "Пользователя с логином '" + user.getLogin()
                        + "'больше не администратор");
                user.setRoles(new HashSet<>(List.of(Role.RoleName.USER)));
                userService.update(user);
                resp.sendRedirect(req.getContextPath() + "/users");
                return;
            }
        }
        user.setRoles(new HashSet<>(List.of(Role.RoleName.USER,
                Role.RoleName.ADMIN)));
        userService.update(user);
        req.setAttribute("message", "Пользователь с логином '" + user.getLogin()
                + "' успешно получил должность администратора");
        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
