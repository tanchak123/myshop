package myshop.controllers.user;

import myshop.lib.Injector;
import myshop.model.User;
import myshop.service.UserService;
import myshop.service.security.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.NoSuchElementException;

public class LoginController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("myshop");
    AuthenticationService authenticationService = (AuthenticationService)
            INJECTOR.getInstance(AuthenticationService.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if ((Long)req.getSession().getAttribute("user_id") != null) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        boolean existing = authenticationService.existence(login);
        if (!existing) {
            req.setAttribute("message", "Такого логина не существует :(");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        User user = authenticationService.getByLogin(login);
        if (!user.getPassword().equals(password)) {
            req.setAttribute("message", "Неверный пароль :(");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        HttpSession session = req.getSession();
        session.setAttribute("user_id", user.getId());
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
