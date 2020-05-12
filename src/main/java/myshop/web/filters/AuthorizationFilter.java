package myshop.web.filters;

import static myshop.model.Role.RoleName.ADMIN;
import static myshop.model.Role.RoleName.USER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myshop.lib.Injector;
import myshop.model.Role;
import myshop.model.Role.RoleName;
import myshop.model.User;
import myshop.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthorizationFilter implements Filter {
    private static final String USER_ID = "user_id";
    private static final Logger LOGGER = LogManager.getLogger(AuthorizationFilter.class);
    private final Injector injector = Injector.getInstance("myshop");
    private final UserService userService = (UserService) injector.getInstance(UserService.class);
    private Map<String, List<Role.RoleName>> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/buckets", List.of(ADMIN));
        protectedUrls.put("/users/delete", List.of(ADMIN));
        protectedUrls.put("/injection", List.of(ADMIN));
        protectedUrls.put("/mybucket", List.of(USER, ADMIN));
        protectedUrls.put("/products", List.of(USER, ADMIN));
        protectedUrls.put("/", List.of(USER, ADMIN));
        protectedUrls.put("/denied", List.of(USER, ADMIN));
        protectedUrls.put("/products/add", List.of(USER, ADMIN));
        protectedUrls.put("/products/create", List.of(ADMIN));
        protectedUrls.put("/mybucket/clean", List.of(USER, ADMIN));
        protectedUrls.put("/logout", List.of(USER, ADMIN));
        protectedUrls.put("/registraion", List.of());
        protectedUrls.put("/users", List.of(ADMIN));
        protectedUrls.put("/myprofile", List.of(USER, ADMIN));
        protectedUrls.put("/order", List.of(USER, ADMIN));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String requestedUrl = req.getServletPath();
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        if (userId == null) {
            filterChain.doFilter(req, resp);
            return;
        }
        User user = userService.get(userId);
        if (!isRole(user, requestedUrl)) {
            LOGGER.warn("Кто-то без статуса 'ADMIN' пытаеться зайти на ограниченную страницу#"
                    + userId);
            resp.sendRedirect(req.getContextPath() + "/denied");
            return;
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }

    private boolean isRole(User user, String requestedUrl) {
        for (RoleName role : protectedUrls.get(requestedUrl)) {
            for (RoleName userRole : user.getRoles()) {
                if (userRole.equals(role)) {
                    return true;
                }
            }
        }
        return false;
    }
}
