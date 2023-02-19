/**
 * Данный класс реализует CRUD операции с базой данных таблицы ФИЛЬМОВ
 */

package by.academy.cinema.repository.mysql;

import by.academy.cinema.model.Movie;

import by.academy.cinema.repository.CommonRepository;
import by.academy.cinema.repository.MovieRepository;
import by.academy.cinema.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MovieRepositoryImpl implements MovieRepository {

    private final String NAME_TABLE = "movie";

    @Override
    public boolean create(Movie movie) {
        boolean result = false;
        try (Connection conn = ConnectionManager.open()) {
            String sql = "INSERT INTO " + NAME_TABLE + " (title, durationMinutes) VALUES (?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, movie.getTitle());
            stmt.setInt(2, movie.getDurationMinutes());
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
    public Movie read(int id) {
        Movie getMovie = null;
        String sql = "SELECT id, title, durationMinutes FROM " + NAME_TABLE + " WHERE id=?";

        try (Connection conn = ConnectionManager.open()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Integer idMovie = resultSet.getInt("id");
                String titleMovie = resultSet.getString("title");
                Integer durationMovie = resultSet.getInt("durationMinutes");

                getMovie = new Movie(idMovie, titleMovie, durationMovie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return getMovie;
        }
    }

    @Override
    public boolean update(Movie oldObj, Movie newObj) {
        boolean result = false;
        String sql = "UPDATE " + NAME_TABLE + " SET title=?, durationMinutes=? WHERE id=?";

        try (Connection conn = ConnectionManager.open()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newObj.getTitle());
            stmt.setInt(2, newObj.getDurationMinutes());
            stmt.setInt(3, oldObj.getId());
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
    public List<Movie> readAll() {
        List<Movie> movies = null;

        String sql = "SELECT * FROM " + NAME_TABLE;

        try (Connection conn = ConnectionManager.open()) {
            movies = new ArrayList<>();

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Integer idMovie = resultSet.getInt("id");
                String titleMovie = resultSet.getString("title");
                Integer durationMovie = resultSet.getInt("durationMinutes");

                Movie movie = new Movie(idMovie, titleMovie, durationMovie);

                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return movies;
        }
    }
}
