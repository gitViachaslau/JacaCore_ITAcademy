/**
 * Класс организует соединение с базой данных
 */

package by.academy.cinema.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    public static final String URL = "db.url";

    public static final String USERNAME = "db.username";

    public static final String PASSWORD = "db.password";

    static {
        loadDriver();
    }


    public static Connection open(){
        try{
            return DriverManager.getConnection(
                    PropertyUtil.getValue(URL),
                    PropertyUtil.getValue(USERNAME),
                    PropertyUtil.getValue(PASSWORD));
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    private static void loadDriver(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }
}
