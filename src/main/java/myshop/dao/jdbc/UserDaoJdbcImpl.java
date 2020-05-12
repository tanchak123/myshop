package myshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import myshop.dao.UserDao;
import myshop.lib.Dao;
import myshop.model.User;
import myshop.storage.Storage;
import myshop.util.ConnectionUtil;

@Dao
public class UserDaoJdbcImpl implements UserDao {
    private long id = 1;

    @Override
    public void create(User user) {
        String query = "INSERT INTO users (login, password) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statemenet = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statemenet.setString(1, user.getLogin());
            statemenet.setString(2, user.getPassword());
            statemenet.executeUpdate();
            ResultSet resultSet = statemenet.getGeneratedKeys();
        } catch (SQLException e) {
            throw new RuntimeException("NOT FOUND!");
        }
    }

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }
}
