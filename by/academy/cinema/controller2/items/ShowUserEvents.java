/**
 * Класс для вывода мероприятий доступных пользователю к приобретению билетов
 */

package by.academy.cinema.controller2.items;

import by.academy.cinema.controller2.AbstractMenuItem;
import by.academy.cinema.model.Context;
import by.academy.cinema.model.Event;
import by.academy.cinema.model.Movie;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ShowUserEvents extends AbstractMenuItem {

    public ShowUserEvents(String title, String executeCommand, Context context) {
        super(title, executeCommand, context);
    }

    @Override
    public void start() {
        System.out.println("\n=== Список доступных билетов пользователя " + getContext().getUser().getLogin() + " ===");
        List<Event> events = getContext().getEventService().readAllActual();
        if (events == null) {
            System.out.println("Доступных билетов нет нет");
            return;
        }

        for (Event e : events) {
            int freeTickets = getContext().getTicketService().getFreePcs(e.getId());

            printInfoEvent(e);

            System.out.println("          Доступно билетов - " + freeTickets);
        }
    }

    private void printInfoEvent(Event e) {
        Movie read = getContext().getMovieService().read(e.getIdMovie());
        String titleMovie = read.getTitle();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MMMM.yyyy HH-mm");
        String dateTime = e.getDateTimeStart().format(dateTimeFormatter);

        System.out.println("Кинопоказ ID - " + e.getId());
        System.out.println("          Название - " + titleMovie);
        System.out.println("          Дата и время начала: " + dateTime);
        System.out.println("          Стоимость - " + e.getPrice() + " BYN");
    }
}
