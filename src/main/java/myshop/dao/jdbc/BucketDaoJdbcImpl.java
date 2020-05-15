package myshop.dao.jdbc;

import myshop.dao.BucketDao;
import myshop.lib.Dao;
import myshop.model.Bucket;
import myshop.util.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Dao
public class BucketDaoJdbcImpl implements BucketDao {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionUtil.class);

    @Override
    public void create(Bucket bucket) {
        bucket.setId(bucket.getUser().getId());
        String query = "INSERT INTO buckets (user_id, bucket_id) VALUES(?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, bucket.getId());
            preparedStatement.setLong(2, bucket.getId());
        } catch (SQLException throwables) {
            LOGGER.error("Не возможно создать корзину", throwables);
            throw new RuntimeException("Не возможно создать корзину", throwables);
        }
    }

    @Override
    public Bucket get(Long aLong) {
        return null;
    }

    @Override
    public void update(Bucket bucket) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<Bucket> getAll() {
        return null;
    }
}
