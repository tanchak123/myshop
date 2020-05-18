package myshop.dao.jdbc;

import myshop.dao.BucketDao;
import myshop.dao.OrderDao;
import myshop.dao.ProductDao;
import myshop.dao.UserDao;
import myshop.lib.Dao;
import myshop.lib.Inject;
import myshop.model.Order;
import myshop.model.Product;
import myshop.model.User;
import myshop.util.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {

    @Inject
    UserDao userDao;

    @Inject
    BucketDao bucketDao;

    @Inject
    ProductDao productDao;
    private static final Logger LOGGER = LogManager.getLogger(ConnectionUtil.class);
    @Override
    public void create(Order order) {
        String query = "INSERT INTO orders(product_name, sum, count, user_id) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query,
        PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, createStringFromProductForDb
                    (order.getProducts().keySet()));
            preparedStatement.setBigDecimal(2, order.getAmountPayable());
            preparedStatement.setString(3, createCountsStringForDb(order.getProducts().values()));
            preparedStatement.setLong(4, order.getUser().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
            LOGGER.info("Чек успешно создан");
        } catch (SQLException throwables) {
            LOGGER.error("Невозможно создать чек", throwables);
            throw new RuntimeException("Невозможно создать чек", throwables);
        }
    }

    @Override
    public Order get(Long id) {
        Order order = null;
        String query = "SELECT * FROM orders WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User user = userDao.get(resultSet.getLong("user_id"));
            order = new Order(user, bucketDao.get(user.getId()).getProducts());
            order.setId(resultSet.getLong(1));
        }
            LOGGER.info("Чек успешно получен");
            return order;
    } catch (SQLException throwables) {
        LOGGER.error("Невозможно получить чек", throwables);
        throw new RuntimeException("Невозможно получить чек", throwables);
    }
    }

    @Override
    public void update(Order order) {
        String query = "UPDATE orders SET product_name = ?, price = ?, count = ?, sum = ? "
                + "WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, createStringFromProductForDb(order
                    .getProducts().keySet()));
            preparedStatement.setBigDecimal(2, order.getAmountPayable());
            preparedStatement.setString(3, createCountsStringForDb(order.getProducts().values()));
            preparedStatement.setBigDecimal(4, order.getAmountPayable());
            preparedStatement.setLong(5, order.getUser().getId());
            preparedStatement.executeUpdate();
            LOGGER.info("Чек успешно обновлён");
        } catch (SQLException throwables) {
            LOGGER.error("Невозможно обновить чек", throwables);
            throw new RuntimeException("Невозможно обновить чек", throwables);
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM orders WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            LOGGER.info("Чек успешно удалён");
        } catch (SQLException throwables) {
            LOGGER.error("Невозможно удалить заказ", throwables);
            throw new RuntimeException("Невозможно удалить заказ", throwables);
        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order(userDao.get(resultSet.getLong("user_id")),
                        createProductsFromStrings(resultSet));
                orders.add(order);
            }
            LOGGER.info("Список всех заказов успешно получен");
            return orders;
        } catch (SQLException throwables) {
            LOGGER.error("Невозможно получить список всех заказов", throwables);
            throw new RuntimeException("Невозможно получить список всех заказов", throwables);
        }
    }

    private List<Product> createProductsFromStrings(ResultSet resultSet) throws SQLException {
        List<Product> products = productDao.getAll();
        List<Product> result = new ArrayList<>();
        for (String name : resultSet.getString("product_name").replaceAll(" ", "")
        .split(",")) {
            for (Product product : products) {
                if (product.getName().equals(name)) {
                    result.add(product);
                }
            }
        }
        return result;
    }

    private String createCountsStringForDb(Collection<Integer> counts) {
        return counts.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    private String createStringFromProductForDb(Set<Product> products) {
        return products.stream()
                .map(Product::getName)
                .collect(Collectors.joining(", "));
    }

    private String createStringFromPriceForDb(Set<Product> counts) {
        return counts.stream()
                .map(product -> product.getPrice().toString())
                .collect(Collectors.joining(", "));
    }
}
