/**
 * Класс для отображения пользователей
 */

package by.academy.cinema.controller2.items;

import by.academy.cinema.controller2.AbstractMenuItem;
import by.academy.cinema.model.Context;
import by.academy.cinema.model.Ticket;
import by.academy.cinema.model.User;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ShowUsers extends AbstractMenuItem {
    public ShowUsers(String title, String executeCommand, Context context) {
        super(title, executeCommand, context);
    }

    @Override
    public void start() {

        System.out.println("\n=== Список пользователей ===");

        List<User> users = getContext().getUserService().readAllNotRule();

        if (users == null) {
            System.out.println("Пользователей нет");
            return;
        }

        for (User u : users) {
            printInfoUser(u);
        }

    }

    protected void printInfoUser(User user) {
        System.out.println("Пользователь ID - " + user.getId());
        System.out.println("          Имя пользователя - " + user.getLogin());

        List<Ticket> tickets = getContext().getTicketService().readTicketsUser(user.getId());

        for (Ticket t : tickets) {
            int idTicket = t.getId();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MMMM.yyyy  HH-mm");
            String dateTime = getContext().getEventService().readEvent(t.getIdEvent()).getDateTimeStart().format(dateTimeFormatter);
            String nameMovie = getContext().getMovieService().read(getContext().getEventService().readEvent(t.getIdEvent()).getIdMovie()).getTitle();

            System.out.println("          ID билета - " + idTicket + ", " + nameMovie + ", " + dateTime);
        }
    }
}
