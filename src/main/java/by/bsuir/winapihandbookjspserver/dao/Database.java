package by.bsuir.winapihandbookjspserver.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Класс для установления соединения с базой данных.
 */
class Database {
    private String url = "";
    private String user = "";
    private String password = "";

    Database() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        Class.forName("org.postgresql.Driver").newInstance();

        url = ResourceBundle.getBundle("config").getString("db.url");
        user = ResourceBundle.getBundle("config").getString("db.user");
        password = ResourceBundle.getBundle("config").getString("db.password");

    }

    /**
     * Установление соединения с базой данных для указанного урла, пользователя и пароля.
     * @return соединение с БД
     * @throws SQLException
     */
    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
