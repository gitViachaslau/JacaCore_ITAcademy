/**
 * Данный класс реализует CRUD операции с базой данных таблицы БИЛЕТОВ
 */

package by.academy.cinema.repository.mysql;

import by.academy.cinema.model.Ticket;
import by.academy.cinema.repository.CommonRepository;
import by.academy.cinema.repository.TicketRepository;
import by.academy.cinema.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketRepositoryImpl implements TicketRepository {

    private final String NAME_TABLE = "ticket";

    @Override
    public boolean create(Ticket ticket) {
        boolean result = false;
        try (Connection conn = ConnectionManager.open()) {
            String sql = "INSERT INTO " + NAME_TABLE + " (userID, eventID, seat, price, free) VALUES (?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ticket.getIdUser());
            stmt.setInt(2, ticket.getIdEvent());
            stmt.setInt(3, ticket.getSeat());
            stmt.setDouble(4, ticket.getPrice());
            stmt.setBoolean(5, ticket.isPurchase());
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
    public Ticket read(int id) {
        Ticket getTicket = null;
        String sql = "SELECT id, userID, eventID, seat, price, free FROM " + NAME_TABLE + " WHERE id=?";

        try (Connection conn = ConnectionManager.open()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Integer idTicket = resultSet.getInt("id");
                String idUser = resultSet.getString("userID");
                Integer idEvent = resultSet.getInt("eventID");
                Integer seatTicket = resultSet.getInt("seat");
                Double priceTicket = resultSet.getDouble("price");
                Boolean freeTicket = resultSet.getBoolean("free");

                getTicket = new Ticket(idTicket, idUser, idEvent, seatTicket, priceTicket, freeTicket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return getTicket;
        }
    }

    @Override
    public boolean update(Ticket oldObj, Ticket newObj) {
        boolean result = false;
        String sql = "UPDATE " + NAME_TABLE + " SET userID=?, eventID=?, seat=?, price=?, free=?  WHERE id=?";

        try (Connection conn = ConnectionManager.open()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newObj.getIdUser());
            stmt.setInt(2, newObj.getIdEvent());
            stmt.setInt(3, newObj.getSeat());
            stmt.setDouble(4, newObj.getPrice());
            stmt.setBoolean(5, newObj.isPurchase());
            stmt.setInt(6, oldObj.getId());
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
        } finally {
            return result;
        }
    }

    @Override
    public List<Ticket> readAll() {
        List<Ticket> tickets = null;
        String sql = "SELECT * FROM " + NAME_TABLE;

        try (Connection conn = ConnectionManager.open()) {
            tickets = new ArrayList<>();

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            tickets = createTicketObj(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return tickets;
        }
    }

    @Override
    public int getCountFreeTicketsForEvent(int idEventInput) {
        int result = 0;

        String sql = "SELECT COUNT(*) FROM " + NAME_TABLE + " WHERE ((eventID=?) & (free=?))";

        try (Connection conn = ConnectionManager.open()) {

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idEventInput);
            stmt.setBoolean(2, false);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                result = resultSet.getInt("COUNT(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    @Override
    public List<Ticket> getTicketsForEvent(int idEventInput) {
        List<Ticket> tickets = null;

        String sql = "SELECT * FROM " + NAME_TABLE + " WHERE ((eventID=?) & (free=?))";

        try (Connection conn = ConnectionManager.open()) {
            tickets = new ArrayList<>();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idEventInput);
            stmt.setBoolean(2, false);
            ResultSet resultSet = stmt.executeQuery();

            tickets = createTicketObj(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return tickets;
        }

    }

    @Override
    public List<Ticket> getTicketsForUser(int idUserInput) {
        List<Ticket> tickets = null;

        String sql = "SELECT * FROM " + NAME_TABLE + " WHERE userID=?";

        try (Connection conn = ConnectionManager.open()) {
            tickets = new ArrayList<>();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUserInput);
            ResultSet resultSet = stmt.executeQuery();

            tickets = createTicketObj(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return tickets;
        }
    }


    private List<Ticket> createTicketObj(ResultSet resultSet) {

        List<Ticket> result = null;
        try {
            result = new ArrayList();

            while (resultSet.next()) {
                Integer idTicket = resultSet.getInt("id");
                String idUser = resultSet.getString("userID");
                Integer idEvent = resultSet.getInt("eventID");
                Integer seatTicket = resultSet.getInt("seat");
                Double priceTicket = resultSet.getDouble("price");
                Boolean freeTicket = resultSet.getBoolean("free");

                result.add(new Ticket(idTicket, idUser, idEvent, seatTicket, priceTicket, freeTicket));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
