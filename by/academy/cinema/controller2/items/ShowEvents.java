/**
 * Класс для вывода мероприятий
 */

package by.academy.cinema.controller2.items;

import by.academy.cinema.controller2.AbstractMenuItem;
import by.academy.cinema.model.Context;
import by.academy.cinema.model.Event;
import by.academy.cinema.model.Movie;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ShowEvents extends AbstractMenuItem {
    public ShowEvents(String title, String executeCommand, Context context) {
        super(title, executeCommand, context);
    }

    @Override
    public void start() {

        System.out.println("\n=== Список мероприятий ===");

        List<Event> events = getContext().getEventService().readAll();

        if (events == null) {
            System.out.println("Мероприятий нет");
            return;
        }

        for (Event e : events) {
            printInfoEvent(e);
        }

    }

    protected void printInfoEvent(Event event) {

        Movie read = getContext().getMovieService().read(event.getIdMovie());
        String titleMovie = read.getTitle();

        String DATE_TIME_PATTERN = "dd.MM.yyyy HH-mm";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

        String dateTime = event.getDateTimeStart().format(dateTimeFormatter);

        System.out.println("Кинопоказ ID - " + event.getId());
        System.out.println("          Название - " + titleMovie);
        System.out.println("          Дата и время начала: " + dateTime);
        System.out.println("          Стоимость - " + event.getPrice() + " BYN");
    }
}
