/**
 * Класс для создания нового мероприятия
 */

package by.academy.cinema.controller2.items;

import by.academy.cinema.controller2.AbstractMenuItem;
import by.academy.cinema.model.Context;
import by.academy.cinema.model.Event;
import by.academy.cinema.model.Ticket;
import by.academy.cinema.util.CheckType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static by.academy.cinema.util.CheckSyntax.checkSyntax;

public class CreateNewEvent extends AbstractMenuItem {

    public CreateNewEvent(String title, String executeCommand, Context context) {
        super(title, executeCommand, context);
    }

    @Override
    public void start() {

        System.out.println("\n=== Создание мероприятия ===");

        final int ID_SYMBOL_MIN = 1;
        final int ID_SYMBOL_MAX = 8;
        final int TICKETS_SYMBOL_MIN = 1;
        final int TICKETS_SYMBOL_MAX = 8;

        while (true) {
            System.out.println("=== 0 - Возврат  предыдущее меню ===");

            String idMovie;
            LocalDateTime dateTime;
            String price;
            String pcsTicket;

            System.out.println("Введите ID-номер фильма к показу");
            System.out.print("--> ");

            idMovie = getUserInput();

            if (EXIT_COMMAND.equals(idMovie)) {
                return;
            }

            if (checkSyntax(String.valueOf(idMovie), ID_SYMBOL_MIN, ID_SYMBOL_MAX, CheckType.ID)) {

                if (getContext().getMovieService().checkIdMovie(Integer.parseInt(idMovie))) {

                    System.out.println("Введите стоимость билета на мероприятие:");
                    System.out.print("--> ");

                    price = getUserInput();

                    if (EXIT_COMMAND.equals(price)) {
                        return;
                    }

                    if (checkSyntax(String.valueOf(price), CheckType.PRICE)) {

                        String DATE_TIME_PATTERN = "dd.MM.yyyy HH-mm";
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

                        System.out.println("Введите дату и время начала мероприятия в формате  ДД.MM.ГГГГ ЧЧ-ММ ");
                        System.out.print("--> ");

                        String inputDateTime = getUserInput();

                        if (EXIT_COMMAND.equals(inputDateTime)) {
                            return;
                        }

                        try {
                            dateTime = LocalDateTime.parse(inputDateTime, dateTimeFormatter);
                        } catch (Exception e) {
                            dateTime = null;
                        }

                        if (dateTime != null) {

                            System.out.println("Введите количество билетов на мероприятие: ");
                            System.out.print("--> ");

                            pcsTicket = getUserInput();

                            if (EXIT_COMMAND.equals(pcsTicket)) {
                                return;
                            }

                            if (checkSyntax(String.valueOf(pcsTicket), TICKETS_SYMBOL_MIN, TICKETS_SYMBOL_MAX, CheckType.SEAT)) {

                                Event newEvent = new Event(Integer.parseInt(pcsTicket), Integer.parseInt(idMovie), dateTime,Double.parseDouble(price));

                               boolean createEvent = getContext().getEventService().create(newEvent);

                               if(createEvent){

                                   int idNewEvent = getContext().getEventService().getIdEvent(newEvent);

                                   for(int i = 1; i <= Integer.parseInt(pcsTicket); i++){
                                       getContext().getTicketService().createTicket(new Ticket("", idNewEvent, i, 0, false));
                                   }

                               }

                                System.out.println(createEvent ? "Мероприятие создано" : "Создание мероприятия не удалось");

                                return;

                            } else {
                                System.out.println("Вы ввели дату и время несоответсвующее формату!");
                            }
                        } else {
                            System.out.println("Вы ввели некорректную стоимость!");
                        }
                    } else {
                        System.out.println("Вы ввели ID не существующего фильма!");
                    }
                } else {
                    System.out.println("Вы ввели некорректный ID фильма");
                }
            }
        }

    }
}
