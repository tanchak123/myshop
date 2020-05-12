package myshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionUtil {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionUtil.class);

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            LOGGER.info("DataBase driver is registered.");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Could not find MySQL driver.", e);
            throw new RuntimeException("Can't find MySql Driver", e);
        }
    }

    public static Connection getConnection() {
        Properties dbProperties = new Properties();
        dbProperties.put("user", "root");
        dbProperties.put("password", "123");
        String url = "jdbc:mysql://localhost:3306/myshop?serverTimezone=UTC";
        try {
            LOGGER.info("Connection to DB is established.");
            return DriverManager.getConnection(url, dbProperties);
        } catch (SQLException e) {
            LOGGER.error("Can't establish the connection to DB.", e);
            throw new RuntimeException("Can'nt connect to db :(");
        }
    }
}
