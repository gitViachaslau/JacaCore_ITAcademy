/**
 * Класс для отображению меню ПОЛЬЗОВАТЕЛЯ
 */

package by.academy.cinema.controller;

import by.academy.cinema.model.*;
import by.academy.cinema.service.EventService;
import by.academy.cinema.service.MovieService;
import by.academy.cinema.service.TicketService;
import by.academy.cinema.service.UserService;
import by.academy.cinema.util.CheckType;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static by.academy.cinema.util.CheckSyntax.checkSyntax;
import static by.academy.cinema.controller.MainMenuController.EXIT;

public class UserMenuController implements Controller{

    private User user;

    private UserService userService;

    private EventService eventService;

    private MovieService movieService;

    private TicketService ticketService;

    public UserMenuController(User user, UserService userService,
                              EventService eventService,
                              MovieService movieService,
                              TicketService ticketService) {
        this.user = user;
        this.userService = userService;
        this.eventService = eventService;
        this.movieService = movieService;
        this.ticketService = ticketService;
    }

    @Override
    public void start() {
        while (true) {
            System.out.println("\n=-=-=-= МЕНЮ ПОЛЬЗОВАТЕЛЯ " + user.getLogin() + " =-=-=-=");
            System.out.println("1 - Показать весь список мероприятий");
            System.out.println("2 - Просмотреть список моих билетов");
            System.out.println("3 - Купить билет");
            System.out.println("4 - Вернуть купленный билет");
            System.out.println("\n0 - Возврат в предыдущее меню с завершением авторизации");
            System.out.println("-->");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            boolean result = false;

            switch (input) {
                case "1":
                    System.out.println("\n=== Список доступных билетов пользователя " + user.getLogin() + " ===");
                    result = printEvents();
                    //ShowUserEvents
                    System.out.println(result ? "" : "Доступных билетов нет нет\n");
                    break;
                case "2":
                    System.out.println("\n=== Список купленных билетов пользователя " + user.getLogin() + " ===");
                    result = printTicketsUser();
                    //ShowUserTickets
                    System.out.println(result ? "" : "Купленных билетов нет\n");
                    break;
                case "3":
                    System.out.println("\n=== Покупка билета на мероприятие для пользователя " + user.getLogin() + "  ===");
                    result = buyTicket();
                    //BuyTicket
                    System.out.println(result ? "Билет куплен" : "Покупка билета не удалась\n");
                    break;
                case "4":
                    System.out.println("\n=== Сдача билета на мероприятие пользователя " + user.getLogin() + " ===");
                    result = returnTickets();
                    //ReturnTicket
                    System.out.println(result ? "Билет удачно сдан" : "Сдать билет не удалось не удалась\n");
                    break;
                case EXIT:
                    return;
                default:
                    System.out.println("Вы ввели недопустимое значение!!! Попробуйте ещё раз!\n");
                    break;
            }

        }
    }


    private boolean printEvents() {
        List<Event> events = eventService.readAllActual();

        if (events == null) {
            return false;
        }

        for (Event e : events) {
            int freeTickets = ticketService.getFreePcs(e.getId());

            printInfoEvent(e);

            System.out.println("          Доступно билетов - " + freeTickets);
        }
        return true;
    }

    public boolean buyTicket() {

        final int ID_SYMBOL_MIN = 1;
        final int ID_SYMBOL_MAX = 8;

        while (true) {
            System.out.println("=== 0 - Возврат  предыдущее меню ===");
            System.out.println("Введите ID-номер мероприятия для покупки билета");
            System.out.print("--> ");
            Scanner input = new Scanner(System.in);
            String id = input.nextLine();

            if (EXIT.equals(id)) {
                return false;
            }


            if (checkSyntax(id, ID_SYMBOL_MIN, ID_SYMBOL_MAX, CheckType.ID)) {

                int idInt = Integer.valueOf(id);

                if (eventService.checkIdEvent(idInt)) {

                    List<Ticket> ticketsForEvent = ticketService.getTicketsForEvent(idInt);

                    if ((ticketsForEvent != null) && (ticketsForEvent.size() > 0)) {

                        printInfoEvent(eventService.readEvent(idInt));

                        StringBuffer freeSeats = new StringBuffer();
                        freeSeats.append("Доступны места: ");

                        for (Ticket t : ticketsForEvent) {
                            freeSeats.append(t.getSeat());
                            freeSeats.append(", ");
                        }
                        freeSeats.delete(freeSeats.length() - 2, freeSeats.length() - 1);

                        System.out.println(freeSeats);

                        System.out.println("Для покупки билета введите номер места или введите 0 для отмены покупки");

                        String numberSeatForBuy = input.nextLine();

                        if (EXIT.equals(numberSeatForBuy)) {
                            return false;
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

                            return ticketService.buyTicket(user.getId(), idTicket, numberSeatForBuyInt,
                                    eventService.readEvent(idInt).getPrice());

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
        Movie read = movieService.read(e.getIdMovie());
        String titleMovie = read.getTitle();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MMMM.yyyy");
        String dateTime = e.getDateTimeStart().format(dateTimeFormatter);

        System.out.println("Кинопоказ ID - " + e.getId());
        System.out.println("          Название - " + titleMovie);
        System.out.println("          Дата и время начала: " + dateTime);
        System.out.println("          Стоимость - " + e.getPrice() + " BYN");
    }

    private boolean printTicketsUser() {
        int idUser = user.getId();

        List<Ticket> tickets = ticketService.readTicketsUser(idUser);

        if (tickets == null || tickets.size() == 0) {
            return false;
        }

        for (Ticket t : tickets) {
            printInfoTicket(t);
        }

        System.out.println();

        return true;
    }

    private void printInfoTicket(Ticket t) {
        System.out.println("ID БИЛЕТА - " + t.getId());
        System.out.println("Кинопоказ ID - " + t.getIdEvent());
        System.out.println("          Название - " + movieService.read(eventService.readEvent(t.getIdEvent())
                .getIdMovie()).getTitle());
        System.out.println("          Номер места: " + t.getSeat());
        System.out.println("          Дата и время начала: " +
                eventService.readEvent(t.getIdEvent()).getDateTimeStart().getDayOfMonth() + "." +
                eventService.readEvent(t.getIdEvent()).getDateTimeStart().getDayOfMonth() + "." +
                eventService.readEvent(t.getIdEvent()).getDateTimeStart().getYear() + "   " +
                eventService.readEvent(t.getIdEvent()).getDateTimeStart().getHour() + "-" +
                eventService.readEvent(t.getIdEvent()).getDateTimeStart().getMinute());
        System.out.println("          Стоимость - " + t.getPrice() + " BYN");
    }

    private boolean returnTickets() {

        final int ID_SYMBOL_MIN = 1;
        final int ID_SYMBOL_MAX = 8;

        while (true) {
            System.out.println("=== 0 - Возврат  предыдущее меню ===");
            System.out.println("Введите ID-номер билета для возврата");
            System.out.print("--> ");
            Scanner input = new Scanner(System.in);
            String id = input.nextLine();

            if (EXIT.equals(id)) {
                return false;
            }

            if (checkSyntax(id, ID_SYMBOL_MIN, ID_SYMBOL_MAX, CheckType.ID)) {

                int idInt = Integer.valueOf(id);

                if (ticketService.checkIdTicket(idInt)) {

                    Ticket ticket = ticketService.read(idInt);

                    if (ticket.getIdUser().equals(String.valueOf(user.getId()))) {

                        return ticketService.returnTicket(idInt);
                    }
                    else{
                        System.out.println("У Вас нет билета с таким ID\n");
                    }
                }

                else{
                    System.out.println("Билета с таким ID не существует.\n");
                }
            }
            else{
                System.out.println("Вы ввели некорретный номер.\n");
            }
        }
    }
}
