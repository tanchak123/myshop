package myshop.controllers;

import myshop.lib.Injector;
import myshop.model.User;
import myshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class InjectDataController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("myshop");
    private final UserService userService = (UserService) injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        userService.create(new User("Sanya", "Vasya"));
        userService.create(new User("ыыыыыы", "vas123"));
        User sanya = new User("Sanaya", "Vasya");
        userService.create(sanya);
        List<User> users = userService.getAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/views/injectData.jsp").forward(req, resp);
    }
}
