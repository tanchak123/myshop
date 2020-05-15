package myshop.dao.jdbc;

import myshop.dao.ProductDao;
import myshop.lib.Dao;
import myshop.model.Product;
import myshop.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Dao
public class ProductDaoJdbcImpl implements ProductDao {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionUtil.class);

    @Override
    public void create(Product product) {
        String query = "INSERT INTO products(name, price) VALUES(?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query,
        PreparedStatement.RETURN_GENERATED_KEYS)) {
                 preparedStatement.setString(1, product.getName());
                 preparedStatement.setBigDecimal(2, product.getPrice());
                 preparedStatement.executeUpdate();
                 ResultSet resultSet = preparedStatement.getGeneratedKeys();
                 while (resultSet.next()) {
                     product.setId(resultSet.getLong(1));
                 }
                 LOGGER.info("Товар успешно создан");
        } catch (SQLException throwables) {
            LOGGER.error("Не могу создать товар");
            throw new RuntimeException("Не могу создать товар");
        }
    }

    @Override
    public Product get(Long id) {
        Product product = null;
        String query = "SELECT * FROM products WHERE product_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                product = createProductFromResultSet(resultSet);
            }
            LOGGER.info("Товар успешно получен");
            return product;
        } catch (SQLException throwables) {
            LOGGER.error("Не могу получить товар");
            throw new RuntimeException("Не могу получить товар");
        }
    }

    @Override
    public void update(Product product) {
        String query = "UPDATE products SET name = ?, price = ? WHERE product_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setLong(3, product.getId());
            preparedStatement.executeUpdate();
            LOGGER.info("Товар успешно обновлён");
        } catch (SQLException throwables) {
            LOGGER.error("Не могу обновить товар");
            throw new RuntimeException("Не могу обновить товар");
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM products WHERE product_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            LOGGER.info("Товар успешно обновлён");
        } catch (SQLException throwables) {
            LOGGER.error("Не могу обновить товар");
            throw new RuntimeException("Не могу обновить товар");
        }

    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * from products";
        try (Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List <Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(createProductFromResultSet(resultSet));
            }
            LOGGER.info("Товар успешно удалён");
            return products;
        } catch (SQLException throwables) {
            LOGGER.error("Не могу удалить товар");
            throw new RuntimeException("Не могу удалить товар");
        }
    }

    private Product createProductFromResultSet(ResultSet resultSet)
            throws SQLException {
        Product product = new Product(resultSet.getNString("name"),
                resultSet.getBigDecimal("price").toString());
        product.setId(resultSet.getLong(1));
        return product;
    }
}
