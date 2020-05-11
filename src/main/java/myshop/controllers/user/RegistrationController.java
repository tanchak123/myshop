package myshop.controllers.user;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myshop.lib.Injector;
import myshop.model.User;
import myshop.service.UserService;

public class RegistrationController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("myshop");
    private final UserService userService = (UserService)
            injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getSession().getAttribute("user_id") != null) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        req.getRequestDispatcher("WEB-INF/views/registration.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String repPassword = req.getParameter("password-repeat");
        if (!password.equals(repPassword)) {
            req.setAttribute("message", "Пароли не совпадают!!!");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        }
        if (password.length() == 0 || login.length() == 0) {
            req.setAttribute("message", "Поля не должны быть пустыми :(");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
            resp.sendRedirect(req.getContextPath() + "/");
        }
        if (password.length() > 8) {
            req.setAttribute("message", "Пароль должен иметь хотя бы 8 символов!");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
            resp.sendRedirect(req.getContextPath() + "/");
        }
        if (!Pattern.compile("[A-Za-z]").matcher(password).find()) {
            req.setAttribute("message", "Пароль должен иметь хотя бы 1 латинскую букву!");
        }
        User user = new User(login, password);
        userService.create(user);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
