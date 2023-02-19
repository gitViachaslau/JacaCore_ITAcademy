/**
 * Класс для покупки билета
 */

package by.academy.cinema.controller2.items;

import by.academy.cinema.model.Event;
import by.academy.cinema.model.Movie;
import by.academy.cinema.util.CheckType;
import by.academy.cinema.controller2.AbstractMenuItem;
import by.academy.cinema.model.Context;
import by.academy.cinema.model.Ticket;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static by.academy.cinema.util.CheckSyntax.checkSyntax;

public class BuyTicket extends AbstractMenuItem {

    private static String EXIT_COMMAND = "0";

    public BuyTicket(String title, String executeCommand, Context context) {
        super(title, executeCommand, context);
    }

    @Override
    public void start() {
        final int ID_SYMBOL_MIN = 1;
        final int ID_SYMBOL_MAX = 8;

        System.out.println("\n=== Покупка билета на мероприятие для пользователя " + getContext().getUser().getLogin() + "  ===");

        while (true) {
            System.out.println("=== " + EXIT_COMMAND + " - Возврат  предыдущее меню ===");
            System.out.println("Введите ID-номер мероприятия для покупки билета");
            System.out.print("--> ");

            String id = getUserInput();

            if (EXIT_COMMAND.equals(id)) {
                return;
            }


            if (checkSyntax(id, ID_SYMBOL_MIN, ID_SYMBOL_MAX, CheckType.ID)) {

                int idInt = Integer.valueOf(id);

                if (getContext().getEventService().checkIdEvent(idInt)) {

                    List<Ticket> ticketsForEvent = getContext().getTicketService().getTicketsForEvent(idInt);

                    if ((ticketsForEvent != null) && (ticketsForEvent.size() > 0)) {

                        printInfoEvent(getContext().getEventService().readEvent(idInt));

                        StringBuffer freeSeats = new StringBuffer();
                        freeSeats.append("Доступны места: ");

                        for (Ticket t : ticketsForEvent) {
                            freeSeats.append(t.getSeat());
                            freeSeats.append(", ");
                        }

                        freeSeats.delete(freeSeats.length() - 2, freeSeats.length() - 1);

                        System.out.println(freeSeats);

                        System.out.println("Для покупки билета введите номер места или введите 0 для отмены покупки");

                        String numberSeatForBuy = getUserInput();

                        if (EXIT_COMMAND.equals(numberSeatForBuy)) {
                            return;
                        }

                        if ((checkSyntax(numberSeatForBuy, 1, 3, CheckType.SEAT))) {
                            int idTicket = 0;
                            int numberSeatForBuyInt = Integer.valueOf(numberSeatForBuy);

                            for (Ticket t : ticketsForEvent) {
                                if (t.getSeat() == numberSeatForBuyInt) {
                                    idTicket = t.getId();
                                    break;
                                }
                            }

                            boolean result = getContext().getTicketService().buyTicket(getContext().getUser().getId(), idTicket, numberSeatForBuyInt,
                                    getContext().getEventService().readEvent(idInt).getPrice());
                            System.out.println(result ? "Билет куплен" : "Покупка билета не удалась\n");
                            return;

                        } else {
                            System.out.println("Мы не можем вам продать билет с таким местом.\n");
                        }
                    } else {
                        System.out.println("На это мероприятие нет свободных билетов\n");
                    }
                } else {
                    System.out.println("Мероприятие которое вы запросили не найдено.\n");
                }
            } else {
                System.out.println("Вы ввели некорретный номер.\n");
            }
        }
    }

    private void printInfoEvent(Event e) {
        Movie read = getContext().getMovieService().read(e.getIdMovie());
        String titleMovie = read.getTitle();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MMMM.yyyy");
        String dateTime = e.getDateTimeStart().format(dateTimeFormatter);

        System.out.println("Кинопоказ ID - " + e.getId());
        System.out.println("          Название - " + titleMovie);
        System.out.println("          Дата и время начала: " + dateTime);
        System.out.println("          Стоимость - " + e.getPrice() + " BYN");
    }
}
