package by.bsuir.winapihandbookjspserver.dao;

import by.bsuir.winapihandbookjspserver.bean.WinAPIFunction;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Объект доступа к данным базы данных, реализующий CRUD-функции.
 */
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

    /**
     * Получение всех записей справочника.
     * @return list - список функций
     * @throws SQLException
     */
    public static List<WinAPIFunction> getAllFunctions() throws SQLException {
        String SQL;
        ResultSet rs;
        List<WinAPIFunction> list = new ArrayList<>();
        SQL = "SELECT * " +
                "FROM winapi_function;";

        rs = database.getConnection().createStatement().executeQuery(SQL);

        while (rs.next()) {
            list.add(fromResultSetToObject(rs));
        }

        return list;
    }

    /**
     * Добавление записи в справочник.
     * @param function - добавляемая функция
     * @return status - статус обновления
     * @throws SQLException
     */
    public static int addFunction(WinAPIFunction function) throws SQLException {
        String SQL;
        PreparedStatement preparedStatement;

        SQL = "INSERT INTO winapi_function(name, params, return_value, description) " +
                "VALUES(?, ?, ?, ?);";
        preparedStatement = database.getConnection().prepareStatement(SQL);
        preparedStatement.setString(1, function.getName());
        preparedStatement.setString(2, function.getParams());
        preparedStatement.setString(3, function.getReturnValue());
        preparedStatement.setString(4, function.getDescription());
        int status = preparedStatement.executeUpdate();
        return status;
    }

    /**
     * Удаление записи из справочника.
     * @param function - удаляемая функция
     * @throws SQLException
     */
    public static void deleteFunction(WinAPIFunction function) throws SQLException {
        String SQL;
        PreparedStatement preparedStatement;
        SQL = "DELETE FROM winapi_function " +
                "WHERE name=?;";
        preparedStatement = database.getConnection().prepareStatement(SQL);
        preparedStatement.setString(1, function.getName());
        preparedStatement.executeUpdate();
    }

    /**
     * Обновление записи справочника.
     * @param function - обновляемая функция
     * @throws SQLException
     */
    public static void updateFunction(WinAPIFunction function) throws SQLException {
        String SQL;
        PreparedStatement preparedStatement;

        SQL = "UPDATE winapi_function " +
                "SET name=?, params=?, return_value=?, description=? " +
                "WHERE name=?;";
        preparedStatement = database.getConnection().prepareStatement(SQL);
        preparedStatement.setString(1, function.getName());
        preparedStatement.setString(2, function.getParams());
        preparedStatement.setString(3, function.getReturnValue());
        preparedStatement.setString(4, function.getDescription());
        preparedStatement.setString(5, function.getName());
        preparedStatement.executeUpdate();
    }

    /**
     * Получение записи справочника по идентификатору.
     * @param id - идентификатор функции
     * @return искомая функция
     * @throws SQLException
     */
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

    /**
     * Получение записи справочника по имени.
     * @param name - имя функции
     * @return искомая функция
     * @throws SQLException
     */
    public static WinAPIFunction getFunctionByName(String name) throws SQLException {
        String SQL;
        ResultSet rs;

        SQL = "SELECT * " +
                "FROM winapi_function " +
                "WHERE name = ?;";

        PreparedStatement statement = database.getConnection().prepareStatement(SQL);
        statement.setString(1, name);
        rs = statement.executeQuery();
        rs.next();

        return fromResultSetToObject(rs);
    }

    /**
     * Маршализация выборки из БД в объект функции.
     * @param rs - результирующее множество выборки
     * @return function - преобразуемая функция
     * @throws SQLException
     */
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