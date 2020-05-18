package myshop.dao.jdbc;

import myshop.dao.BucketDao;
import myshop.dao.ProductDao;
import myshop.lib.Dao;
import myshop.lib.Inject;
import myshop.model.Bucket;
import myshop.model.Product;
import myshop.model.Role;
import myshop.model.User;
import myshop.util.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Dao
public class BucketDaoJdbcImpl implements BucketDao {
    @Inject
    ProductDao productDao;

    private static final Logger LOGGER = LogManager.getLogger(ConnectionUtil.class);

    @Override
    public void create(Bucket bucket) {
        bucket.setId(bucket.getUser().getId());
        String query = "INSERT INTO buckets (user_id, bucket_id, product_id) VALUES(?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(query)) {
            preparedStatement.setLong(1, bucket.getId());
            preparedStatement.setLong(2, bucket.getId());
            preparedStatement.setString(3, "");
            preparedStatement.executeUpdate();
            LOGGER.info("Корзина успешно создана");
        } catch (SQLException throwables) {
            LOGGER.error("Невозможно создать корзину", throwables);
            throw new RuntimeException("Невозможно создать корзину", throwables);
        }
    }

    @Override
    public Bucket get(Long id) {
        List<Product> products = productDao.getAll();
        Bucket bucket = null;
        User user = null;
        String query = "SELECT * FROM buckets WHERE bucket_id = ?";
        String query1 = "SELECT * FROM users WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        PreparedStatement preparedStatement1 = connection.prepareStatement(query1)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                preparedStatement1.setLong(1, resultSet.getLong("user_id"));
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                while (resultSet1.next()) {
                    user = createUser(resultSet1);
                }
                bucket = new Bucket(user);
                bucket.setId(resultSet.getLong("bucket_id"));
                bucket.setProducts(getProductsForBucket(resultSet
                        .getString("product_id"), products));
            }
            LOGGER.info("Корзина успешно создана");
            return bucket;
        } catch (SQLException throwables) {
            LOGGER.error("Не возможно получить корзину", throwables);
            throw new RuntimeException("Не возможно получить корзину", throwables);
        }
    }

    @Override
    public void update(Bucket bucket) {
        String query = "UPDATE buckets SET product_id = ? WHERE bucket_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, fromProductListToDb(bucket));
            preparedStatement.setLong(2, bucket.getId());
            preparedStatement.executeUpdate();
            LOGGER.info("Корзина успешно обновлена");
        } catch (SQLException throwables) {
            LOGGER.error("Не возможно обновить корзину", throwables);
            throw new RuntimeException("Не возможно обновить корзину", throwables);
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE from buckets WHERE bucket_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            LOGGER.info("Корзина успешно удалена");
        } catch (SQLException throwables) {
            LOGGER.error("Не возможно обновить корзину", throwables);
            throw new RuntimeException("Не возможно обновить корзину", throwables);
        }
    }

    @Override
    public List<Bucket> getAll() {
        List<Product> products = productDao.getAll();
        List<Bucket> buckets = new ArrayList<>();
        String query = "SELECT * FROM buckets";
        String query1 = "SELECT * FROM users";
        try (Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        PreparedStatement preparedStatement1 = connection.prepareStatement(query1)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet.next() && resultSet1.next()) {
                User user = createUser(resultSet1);
                Bucket bucket = new Bucket(user);
                bucket.setId(resultSet.getLong("bucket_id"));
                String string = resultSet.getString("product_id");
                bucket.setProducts(getProductsForBucket(string, products));
                buckets.add(bucket);
            }
            LOGGER.info("Список всех корзин успешно получен");
            return buckets;
        } catch (SQLException throwables) {
            LOGGER.error("Невозможно получить список корзин", throwables);
            throw new RuntimeException("Невозможно получить список корзин", throwables);
        }
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        User user ;
        user = new User (resultSet.getString("login"),
                resultSet.getString("password"));
        user.setId(resultSet.getLong("user_id"));
        HashSet<Role.RoleName> roleNames = new HashSet<>();
        for (String roleName : resultSet.getString("roles").split(" ")) {
            roleNames.add(Role.RoleName.valueOf(roleName));
        }
        user.setRoles(roleNames);
        return user;
    }

    private String fromProductListToDb(Bucket bucket) {
        return bucket.getProducts().stream()
                .map(product -> product.getId().toString())
                .collect(Collectors.joining(", "));
    }

    private List<Product> getProductsForBucket(String string, List<Product> products) {
        List<Product> result = new ArrayList<>();
        if (string.equals("")) {
            return result;
        }
        String[] strings = string.replaceAll(" ", "")
                .split(",");
        for (String str : strings) {
            for (Product product : products) {
                if (product.getId().equals(Long.valueOf(str))) {
                    result.add(product);
                    break;
                }
            }
        }
        return result;
    }
}
