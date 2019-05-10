package by.bsuir.winapihandbookjspserver.dao;

import by.bsuir.winapihandbookjspserver.bean.WinAPIFunction;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FunctionDao {
    private static final Logger log;
    private static Database database;

    static {
        log = LogManager.getLogger(FunctionDao.class);
        try {
            database = new Database();
        } catch (Exception e) {
            database = null;
            log.error(e.getMessage());
        }
    }

    public static List<WinAPIFunction> getAllFunctions() throws SQLException {
        String SQL;
        ResultSet rs;
        List<WinAPIFunction> list = new ArrayList<>();
        SQL = "SELECT * " +
                "FROM winapi_function";

        rs = database.getConnection().createStatement().executeQuery(SQL);

        while (rs.next()) {
            list.add(fromResultSetToObject(rs));
        }

        return list;
    }

    public static int addFunction(WinAPIFunction function) throws SQLException {
        String SQL;
        PreparedStatement preparedStatement;

        SQL = "INSERT INTO winapi_function(name, params, return_value, description) " +
                "VALUES(?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE name=?;";
        preparedStatement = database.getConnection().prepareStatement(SQL);
        preparedStatement.setString(1, function.getName());
        preparedStatement.setString(2, function.getParams());
        preparedStatement.setString(3, function.getReturnValue());
        preparedStatement.setString(4, function.getDescription());
        preparedStatement.setString(5, function.getName());
        int status = preparedStatement.executeUpdate();
        return status;
    }

    public static void deleteFunction(WinAPIFunction function) throws SQLException {
        String SQL;
        PreparedStatement preparedStatement;
        SQL = "DELETE FROM winapi_function " +
                "WHERE id=?;";
        preparedStatement = database.getConnection().prepareStatement(SQL);
        preparedStatement.setInt(1, function.getId());
        preparedStatement.executeUpdate();
    }

    public static void updateFunction(WinAPIFunction function) throws SQLException {
        String SQL;
        PreparedStatement preparedStatement;

        SQL = "UPDATE winapi_function " +
                "SET name=?, params=?, return_value=?, description=? " +
                "WHERE id=?;";
        preparedStatement = database.getConnection().prepareStatement(SQL);
        preparedStatement.setString(1, function.getName());
        preparedStatement.setString(2, function.getParams());
        preparedStatement.setString(3, function.getReturnValue());
        preparedStatement.setString(4, function.getDescription());
        preparedStatement.setInt(5, function.getId());
        preparedStatement.executeUpdate();
    }

    public static WinAPIFunction getFunctionById(Integer id) throws SQLException {
        String SQL;
        ResultSet rs;

        SQL = "SELECT * " +
                "FROM winapi_function " +
                "WHERE id = ?;";

        PreparedStatement statement = database.getConnection().prepareStatement(SQL);
        statement.setInt(1, id);
        rs = statement.executeQuery();
        rs.next();

        return fromResultSetToObject(rs);
    }

    private static WinAPIFunction fromResultSetToObject(ResultSet rs) throws SQLException {
        WinAPIFunction function = new WinAPIFunction();
        function.setId(rs.getInt("id"));
        function.setName(rs.getString("name"));
        function.setParams(rs.getString("params"));
        function.setReturnValue(rs.getString("return_value"));
        function.setDescription(rs.getString("description"));
        return function;
    }
}