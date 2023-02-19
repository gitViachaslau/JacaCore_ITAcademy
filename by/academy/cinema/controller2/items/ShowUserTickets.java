/**
 * Класс для отображения купленных билетов у пльзователя
 */

package by.academy.cinema.controller2.items;

import by.academy.cinema.controller2.AbstractMenuItem;
import by.academy.cinema.model.Context;
import by.academy.cinema.model.Event;
import by.academy.cinema.model.Ticket;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ShowUserTickets extends AbstractMenuItem {
    public ShowUserTickets(String title, String executeCommand, Context context) {
        super(title, executeCommand, context);
    }

    @Override
    public void start() {
        System.out.println("\n=== Список купленных билетов пользователя " + getContext().getUser().getLogin() + " ===");
        int idUser = getContext().getUser().getId();

        List<Ticket> tickets = getContext().getTicketService().readTicketsUser(idUser);

        if (tickets == null || tickets.size() == 0) {
            System.out.println("Купленных билетов нет");
            return;
        }

        for (Ticket t : tickets) {
            printInfoTicket(t);
        }
    }

    private void printInfoTicket(Ticket t) {
        String DATE_TIME_PATTERN = "dd.MM.yyyy HH-mm";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

        Event event = getContext().getEventService().readEvent(t.getIdEvent());

        String dateTime = event.getDateTimeStart().format(dateTimeFormatter);

        System.out.println("ID БИЛЕТА - " + t.getId());
        System.out.println("Кинопоказ ID - " + t.getIdEvent());
        System.out.println("          Название - " + getContext().getMovieService().read(getContext().getEventService().readEvent(t.getIdEvent())
                .getIdMovie()).getTitle());
        System.out.println("          Номер места: " + t.getSeat());
        System.out.println("          Дата и время начала: " + dateTime);
        System.out.println("          Стоимость - " + t.getPrice() + " BYN");
    }
}
