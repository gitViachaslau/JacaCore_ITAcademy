/**
 * Данный класс реализует CRUD операции с базой данных таблицы ПОЛЬЗОВАТЕЛЕЙ
 */

package by.academy.cinema.repository.mysql;

import by.academy.cinema.model.User;
import by.academy.cinema.model.UserType;
import by.academy.cinema.repository.CommonRepository;
import by.academy.cinema.repository.UserRepository;
import by.academy.cinema.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.academy.cinema.util.EncryptionAndDecodingBase64.*;

public class UserRepositoryImpl implements UserRepository {

    private final String NAME_TABLE = "user";

    @Override
    public boolean create(User user) {
        boolean result = false;
        try (Connection conn = ConnectionManager.open()) {
            String sql = "INSERT INTO " + NAME_TABLE + " (login, password, level) VALUES (?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            String userPasswordEncrypting = encryptionPasswordBase64(user.getPassword());
            stmt.setString(1, user.getLogin());
            stmt.setString(2, userPasswordEncrypting);
            stmt.setString(3, user.getUserType().getLevel());
            stmt.execute();
            result = true;
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return result;
        }
    }


    @Override
    public User read(int id) {
        User getUser = null;
        String sql = "SELECT id, login, password, level FROM " + NAME_TABLE + " WHERE id=?";

        try (Connection conn = ConnectionManager.open()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            getUser = createUserObj(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return getUser;
        }
    }

    @Override
    public boolean update(User oldObj, User newObj) {
        boolean result = false;
        String sql = "UPDATE " + NAME_TABLE + " SET login=?, password=?, level=? WHERE id=?";

        try (Connection conn = ConnectionManager.open()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            String userPasswordEncrypting = encryptionPasswordBase64(newObj.getPassword());
            stmt.setString(1, newObj.getLogin());
            stmt.setString(2, userPasswordEncrypting);
            stmt.setString(3, newObj.getUserType().getLevel());
            stmt.setInt(4, oldObj.getId());
            stmt.execute();
            result = false;
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return result;
        }
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        String sql = "DELETE FROM " + NAME_TABLE + " WHERE id=?";
        try (Connection conn = ConnectionManager.open()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int i = stmt.executeUpdate();
            if (i > 0) {
                result = true;
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return result;
        }
    }

    @Override
    public List<User> readAll() {
        List<User> users = null;
        String sql = "SELECT * FROM " + NAME_TABLE;

        try (Connection conn = ConnectionManager.open()) {
            users = new ArrayList<>();

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Integer idUser = resultSet.getInt("id");
                String username = resultSet.getString("login");
                String password = resultSet.getString("password");
                String level = resultSet.getString("level");
                String passwordDecoding = decodingPasswordBase64(password);
                UserType userType = UserType.valueOf(level);

                User user = new User(idUser, username, passwordDecoding, userType);

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return users;
        }
    }

    @Override
    public User gerUserOnName(String nameUser) {
        User getUser = null;
        String sql = "SELECT id, login, password, level FROM " + NAME_TABLE + " WHERE login=?";

        try (Connection conn = ConnectionManager.open()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nameUser);
            ResultSet resultSet = stmt.executeQuery();

            getUser = createUserObj(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return getUser;
        }
    }


    private User createUserObj(ResultSet resultSet){

        User result = null;

        try{
            while (resultSet.next()) {
                Integer idUser = resultSet.getInt("id");
                String username = resultSet.getString("login");
                String password = resultSet.getString("password");
                String level = resultSet.getString("level");
                String passwordDecoding = decodingPasswordBase64(password);
                UserType userType = UserType.valueOf(level);

                result =  new User(idUser, username, passwordDecoding, userType);
        }
    } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
