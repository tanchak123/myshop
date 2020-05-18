package myshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import myshop.dao.BucketDao;
import myshop.dao.UserDao;
import myshop.lib.Dao;
import myshop.lib.Inject;
import myshop.model.Role;
import myshop.model.User;
import myshop.util.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Dao
public class UserDaoJdbcImpl implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionUtil.class);

    @Inject
    BucketDao bucketDao;

    @Override
    public void create(User user) {
        String query = "INSERT INTO users (login, password, roles) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statemenet = connection
                     .prepareStatement(query,
                             PreparedStatement.RETURN_GENERATED_KEYS)) {
            statemenet.setString(1, user.getLogin());
            statemenet.setString(2, user.getPassword());
            statemenet.setString(3, user.getRolesToString());
            statemenet.executeUpdate();
            ResultSet resultSet = statemenet.getGeneratedKeys();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
            LOGGER.info("Пользователь успешно создан!");
        } catch (SQLException e) {
            LOGGER.error("Ошибка! Не могу создать юзера!");
            throw new RuntimeException("Ошибка! Не могу создать юзера");
        }
    }

    @Override
    public User get(Long id) {
        String query = "SELECT * FROM users WHERE user_id = ?";
        User user = null;
        try (Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement
                (query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = createUserFromResult(resultSet);
            }
            LOGGER.info("Пользователь с айди " + id + "получен");
            return user;
        } catch (SQLException throwables) {
            LOGGER.error("Не могу получить пользователя");
            throw new RuntimeException("Не могу получить пользователя");
        }
    }

    @Override
    public void update(User user) {
        String query = "UPDATE users SET login = ?, password = ?, roles = ? WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection
                .prepareStatement(query)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRolesToString());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.executeUpdate();
            LOGGER.info("Пользователь успешно обновлён");
        } catch (SQLException throwables) {
            LOGGER.error("Не могу обновить пользователя");
            throw new RuntimeException("Не могу обновить пользователя");
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM users WHERE user_id = ?";
        String query1 = "DELETE FROM buckets WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        PreparedStatement preparedStatement1 = connection.prepareStatement(query1)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement1.setLong(2, id);
            preparedStatement.executeUpdate();
            LOGGER.info("Пользователь с айди " + id +"успешно удалён");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List <User> resultList = new ArrayList<>();
            while (resultSet.next()) {
                resultList.add(createUserFromResult(resultSet));
            }
            LOGGER.info("Запрос на  получение списка всех пользователей успешно выполнен");
            return resultList;
        } catch (SQLException throwables) {
            LOGGER.error("Не могу получить список всех пользователей");
            throw new RuntimeException("Не могу получить список всех пользователей");
        }
    }

    private static User createUserFromResult(ResultSet resultSet) throws SQLException {
        String login = resultSet.getNString("login");
        String password = resultSet.getNString("password");
        User user = new User(login, password);
        user.setId(resultSet.getLong("user_id"));
        HashSet<Role.RoleName> roleNames= new HashSet<>();
        for (String roles : resultSet.getString("roles")
                .split(" ")) {
            roleNames.add(Role.RoleName.valueOf(roles));
        }
        user.setRoles(roleNames);
        return user;
    }
}
