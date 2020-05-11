package myshop;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import myshop.dao.OrderDao;
import myshop.dao.impl.OrderDaoImpl;
import myshop.lib.Injector;
import myshop.model.Bucket;
import myshop.model.Order;
import myshop.model.Product;
import myshop.model.User;
import myshop.service.BucketService;
import myshop.service.ProductService;
import myshop.service.UserService;
import myshop.storage.Storage;

public class Application {
    private static Injector injector = Injector.getInstance("myshop");

    public static void main(String[] args) {
        String a = "fdasfa";
        String b = "[a-zA-Z]";
        System.out.println(b);
        if (Pattern.compile("[A-Za-z]").matcher(a).find()) {
            System.out.println("123");
        }
        final ProductService itemServices =
                (ProductService) injector.getInstance(ProductService.class);
        final UserService users = (UserService) injector
                .getInstance(UserService.class);
        final BucketService buckets = (BucketService) Injector.getInstance("myshop")
                .getInstance(BucketService.class);
        final OrderDao orders = new OrderDaoImpl();
        itemServices.create(new Product("shapka","200"));
        itemServices.create(new Product("Shtani","400"));
        itemServices.create(new Product("Golf","600"));
        itemServices.create(new Product("Kyrtka","1000"));
        itemServices.update(itemServices.get(3L));
        itemServices.delete(3L);
        itemServices.updatePrice("Kyrtka", "5000");
        System.out.println(itemServices.getAll());
        users.create(new User("sanya", "sanya1337"));
        users.create(new User("sanyafdasfas", "sanya1337fdasfsa"));
        users.create(new User("sva", "sanya1337"));
        users.create(new User("petya", "sanya1337fdasfsa"));
        System.out.println(users.getAll());
        buckets.create(new Bucket(users.get(2L)));
        System.out.println(users.getAll());
        System.out.println(Storage.users.stream()
                .map(user -> user.getLogin())
                .collect(Collectors.joining("\n")));
        buckets.addItemByName("Shtani","sanyafdasfas");
        buckets.addItemByName("Shtani","sanyafdasfas");
        buckets.addItemByName("Shtani","sanyafdasfas");
        buckets.addItemByName("Shtani","sva");
        System.out.println("Producti" + buckets.getProducts(1L));
        System.out.println(buckets.getAll());
        List<Product> products = buckets.getAll().stream()
                .filter(bucket -> bucket.getUser().getId().equals(Storage.users.get(1).getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Такой корзины не существует!!!"))
                .getProducts();
        orders.create(new Order(Storage.users.get(1), products));
        orders.getAll();
        /* final UserService userService = (UserService) injector.getInstance(UserService.class);
        final BucketService shoppingCartService = (ShoppingCartService)
                injector.getInstance(ShoppingCartService.class);
        final OrderService orderService = (OrderService) injector.getInstance(OrderService.class);

        final Product iPhoneXI = itemServices.create(
                new Product("iPhone 11", new BigDecimal("999.00")));
        final Product iPhoneXiProMax = itemServices.create(
                new Product("iPhone 11 Pro Max", new BigDecimal("1299.00")));
        final Product iPhoneXr = itemServices.create(
                new Product("iPhone Xr", new BigDecimal("699.00")));

        itemServices.getAll();

        Product productTest = itemServices.get(2L);

        productTest.setPrice(new BigDecimal("2000.00"));
        productTest.setName("iPhone 5S");
        itemServices.update(productTest);

        itemServices.delete(2L);

        User michael = userService.create(new User("Michael", "mich234", "13243546"));
        User anton = userService.create(new User("Anton", "antoine2233", "1111"));
        User eugene = userService.create(new User("Eugene", "yogibear", "123454321"));
        User john = userService.create(new User("John", "j_mm3", "44113468"));

        ShoppingCart shoppingCartMichael = shoppingCartService.getByUserId(michael.getId());
        ShoppingCart shoppingCartAnton = shoppingCartService.getByUserId(anton.getId());
        ShoppingCart shoppingCartEugene = shoppingCartService.getByUserId(eugene.getId());
        ShoppingCart shoppingCartJohn = shoppingCartService.getByUserId(john.getId());

        shoppingCartService.addProduct(shoppingCartMichael, iPhoneXI);
        shoppingCartService.addProduct(shoppingCartService
                .getByUserId(userService.get(1L).getId()), iPhoneXiProMax);
        shoppingCartService.addProduct(shoppingCartAnton, iPhoneXr);
        shoppingCartService.addProduct(shoppingCartEugene, iPhoneXiProMax);
        shoppingCartService.addProduct(shoppingCartJohn, iPhoneXr);

        shoppingCartService.deleteProduct(shoppingCartService
                .getByUserId(eugene.getId()), iPhoneXiProMax);

        shoppingCartService.clear(shoppingCartService.getByUserId(michael.getId()));

        shoppingCartService.addProduct(shoppingCartMichael, iPhoneXr);
        Order order = orderService.completeOrder(shoppingCartMichael.getProducts(), michael);
        shoppingCartService.clear(shoppingCartService.getByUserId(michael.getId()));

        orderService.get(order.getId());

        orderService.getUserOrders(michael);

        orderService.getAll();

        orderService.delete(order.getId());*/
    }
}
