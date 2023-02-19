/**
 * Данный класс реализует CRUD операции с базой данных таблицы МЕРОПРИЯТИЙ
 */

package by.academy.cinema.repository.mysql;

import by.academy.cinema.model.Event;
import by.academy.cinema.repository.CommonRepository;
import by.academy.cinema.repository.EventRepository;
import by.academy.cinema.util.ConnectionManager;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class EventRepositoryImpl implements EventRepository {

    private final String NAME_TABLE = "event";

    @Override
    public boolean create(Event event) {
        boolean result = false;
        try (Connection conn = ConnectionManager.open()) {
            String sql = "INSERT INTO " + NAME_TABLE + " (pcsTicket, idMovie, dateTime, price) VALUES (?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, event.getPcsTicket());
            stmt.setInt(2, event.getIdMovie());
            stmt.setTimestamp(3, Timestamp.valueOf(event.getDateTimeStart()));
            stmt.setDouble(4, event.getPrice());
            stmt.execute();
            result = true;
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    @Override
    public Event read(int id) {
        Event getEvent = null;
        String sql = "SELECT id, pcsTicket, idMovie, dateTime, price FROM " + NAME_TABLE + " WHERE id=?";

        try (Connection conn = ConnectionManager.open()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Integer idEvent = resultSet.getInt("id");
                Integer pcsTicket = resultSet.getInt("pcsTicket");
                Integer idMovie = resultSet.getInt("idMovie");
                Timestamp dateTime = resultSet.getTimestamp("dateTime");
                LocalDateTime dateTimeEvent = dateTime.toLocalDateTime();
                Double price = resultSet.getDouble("price");

                getEvent = new Event(idEvent, pcsTicket, idMovie, dateTimeEvent, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return getEvent;
        }
    }

    @Override
    public boolean update(Event oldObj, Event newObj) {
        boolean result = false;
        String sql = "UPDATE " + NAME_TABLE + " SET pcsTicket=?, idMovie=?, dateTime=?, price=? WHERE id=?";

        try (Connection conn = ConnectionManager.open()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, newObj.getPcsTicket());
            stmt.setInt(2, newObj.getIdMovie());
            stmt.setTimestamp(3, Timestamp.valueOf(newObj.getDateTimeStart()));
            stmt.setDouble(4, newObj.getPrice());
            stmt.setInt(5, oldObj.getId());
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
    public List<Event> readAll() {
        List<Event> events = null;
        String sql = "SELECT * FROM " + NAME_TABLE;

        try (Connection conn = ConnectionManager.open()) {
            events = new ArrayList<>();

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Integer idEvent = resultSet.getInt("id");
                Integer pcsTicket = resultSet.getInt("pcsTicket");
                Integer idMovie = resultSet.getInt("idMovie");
                Timestamp dateTime = resultSet.getTimestamp("dateTime");
                LocalDateTime dateTimeEvent = dateTime.toLocalDateTime();
                Double price = resultSet.getDouble("price");

                Event event = new Event(idEvent, pcsTicket, idMovie, dateTimeEvent, price);

                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return events;
        }
    }

    @Override
    public List<Event> getActualEvents() {
        List<Event> getEvents = null;

        String sql = "SELECT id, pcsTicket, idMovie, dateTime, price FROM " + NAME_TABLE + " WHERE  dateTime > ?";

        try (Connection conn = ConnectionManager.open()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            ResultSet resultSet = stmt.executeQuery();

            getEvents = new ArrayList<>();

            while (resultSet.next()) {
                Integer idEvent = resultSet.getInt("id");
                Integer pcsTicket = resultSet.getInt("pcsTicket");
                Integer idMovie = resultSet.getInt("idMovie");
                Timestamp dateTime = resultSet.getTimestamp("dateTime");
                LocalDateTime dateTimeEvent = dateTime.toLocalDateTime();
                Double price = resultSet.getDouble("price");

                getEvents.add(new Event(idEvent, pcsTicket, idMovie, dateTimeEvent, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return getEvents;
        }
    }

    @Override
    public int getIdEvent(Event event) {
        int result = 0;

            String sql = "SELECT id FROM " + NAME_TABLE + " WHERE (pcsTicket = ?) AND " +
                    "(idMovie = ?) AND (dateTime = ?) AND (price = ?)";

            try (Connection conn = ConnectionManager.open()) {
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setInt(1, event.getPcsTicket());
                stmt.setInt(2, event.getIdMovie());
                stmt.setTimestamp(3, Timestamp.valueOf(event.getDateTimeStart()));
                stmt.setDouble(4, event.getPrice());

                ResultSet resultSet = stmt.executeQuery();

                while (resultSet.next()) {
                    result = resultSet.getInt("id");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                return result;
            }

    }
}
