package myshop.controllers.order;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myshop.lib.Injector;
import myshop.model.Order;
import myshop.model.Product;
import myshop.model.Role;
import myshop.model.User;
import myshop.service.BucketService;
import myshop.service.OrderService;
import myshop.service.UserService;

public class OrderController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector injector = Injector.getInstance("myshop");
    OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
    UserService userService = (UserService) injector.getInstance(UserService.class);
    BucketService bucketService = (BucketService) injector.getInstance(BucketService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = userService.get((Long) req.getSession().getAttribute(USER_ID));
        for (Role.RoleName roleName : user.getRoles()) {
            if (roleName.toString().equals("ADMIN")) {
                req.setAttribute("roles", roleName.toString());
            }
        }
        List<Product> products = bucketService.getAll().stream()
                .filter(b -> b.getUser().getId().equals(user.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Такой корзины не существует!!!"))
                .getProducts();
        Order order = new Order(user, products);
        isExist(order);
        String keys = order.getProducts().keySet()
                .stream()
                .map(Product::getName)
                .collect(Collectors.joining(","));
        String[] values = order.getProducts().values()
                .stream().map(Object::toString)
                .collect(Collectors.joining(","))
                .split(",");
        String[] prices = order.getProducts().keySet().stream()
                .map(product -> product.getPrice().toString())
                .collect(Collectors.joining(",")).split(",");
        String[] strings = keys.split(",");
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i] + "," + values[i]
                    + "," + prices[i];
        }
        req.setAttribute("strings", Arrays.asList(strings));
        req.setAttribute("sum", order.getAmountPayable());
        req.getRequestDispatcher("/WEB-INF/views/orders/order.jsp").forward(req, resp);
    }

    private void isExist(Order order) {
        for (Order order1 : orderService.getAll()) {
            if (order1.getUser().getId().equals(order.getUser().getId())) {
                orderService.update(order);
                return;
            }
        }
        orderService.create(order);
    }
}
