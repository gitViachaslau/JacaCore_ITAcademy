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

public abstract class CommonManagementController implements Controller {

    private User user;

    private UserService userService;

    private EventService eventService;

    private MovieService movieService;

    private TicketService ticketService;

    public CommonManagementController(User user,
                                      UserService userService,
                                      EventService eventService,
                                      MovieService movieService,
                                      TicketService ticketService) {
        this.user = user;
        this.userService = userService;
        this.eventService = eventService;
        this.movieService = movieService;
        this.ticketService = ticketService;
    }


    protected void printInfoUser(User user) {
        System.out.println("Пользователь ID - " + user.getId());
        System.out.println("          Имя пользователя - " + user.getLogin());

        List<Ticket> tickets = ticketService.readTicketsUser(user.getId());

        for (Ticket t : tickets) {
            int idTicket = t.getId();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MMMM.yyyy");
            String dateTime = eventService.readEvent(t.getIdEvent()).getDateTimeStart().format(dateTimeFormatter);
            String nameMovie = movieService.read(eventService.readEvent(t.getIdEvent()).getIdMovie()).getTitle();

            System.out.println("          ID билета - " + idTicket + ", " + nameMovie + ", " + dateTime);
        }
    }

    protected boolean printAllUsers() {
        List<User> users = userService.readAllNotRule();

        if (users == null) {
            return false;
        }

        for (User u : users) {
            printInfoUser(u);
        }
        return true;
    }

    protected void printInfoEvent(Event event) {

        Movie read = movieService.read(event.getIdMovie());
        String titleMovie = read.getTitle();

        System.out.println("Кинопоказ ID - " + event.getId());
        System.out.println("          Название - " + titleMovie);
        System.out.println("          Дата и время начала: " + event.getDateTimeStart().getDayOfMonth() + "." +
                event.getDateTimeStart().getDayOfMonth() + "." +
                event.getDateTimeStart().getYear() + "   " +
                event.getDateTimeStart().getHour() + "-" +
                event.getDateTimeStart().getMinute());
        System.out.println("          Стоимость - " + event.getPrice() + " BYN");
    }


    protected boolean printEvents() {

        List<Event> events = eventService.readAll();

        if (events == null) {
            return false;
        }

        for (Event e : events) {
            printInfoEvent(e);
        }
        return true;
    }

    protected boolean printMovies() {
        List<Movie> movies = movieService.readAll();

        if (movies == null) {
            return false;
        }

        for (Movie m : movies) {
            printInfoMovie(m);
        }

        return true;
    }

    protected void printInfoMovie(Movie movie) {
        System.out.println("Фильм ID - " + movie.getId());
        System.out.println("          Название - " + movie.getTitle());
        System.out.println("          Продолжительность: " + movie.getDurationMinutes() + " минут");
    }


    protected boolean editMovie() {

        final int TITLE_SYMBOL_MIN = 1;
        final int TITLE_SYMBOL_MAX = 20;
        final int TIME_SYMBOL_MIN = 1;
        final int TIME_SYMBOL_MAX = 3;
        final int ID_SYMBOL_MIN = 1;
        final int ID_SYMBOL_MAX = 8;

        while (true) {
            System.out.println("=== 0 - Возврат  предыдущее меню ===");
            System.out.println("Введите ID-номер фильма для редактирования");
            System.out.print("--> ");
            Scanner input = new Scanner(System.in);
            String id = input.nextLine();

            if (EXIT.equals(id)) {
                return false;
            }

            if (checkSyntax(id, ID_SYMBOL_MIN, ID_SYMBOL_MAX, CheckType.ID)) {

                int idInt = Integer.valueOf(id);

                if (movieService.checkIdMovie(idInt)) {

                    Movie movieOld = movieService.read(idInt);
                    String title;
                    int time;

                    System.out.println("Введите новое название для фильма (или старое =) )" +
                            "\nСтарое имя: " + movieOld.getTitle());
                    System.out.print("--> ");
                    input = new Scanner(System.in);
                    title = input.nextLine();

                    if (EXIT.equals(title)) {
                        return false;
                    }

                    if (checkSyntax(title, TITLE_SYMBOL_MIN, TITLE_SYMBOL_MAX, CheckType.MOVIE_TITLE)) {

                        System.out.println("Введите новую продолжительность фильма (или старое =) )" +
                                "\nСтарая продолжительсноть: " + movieOld.getDurationMinutes());
                        System.out.print("--> ");
                        input = new Scanner(System.in);
                        time = Integer.parseInt(input.nextLine());

                        if (checkSyntax(String.valueOf(time), TIME_SYMBOL_MIN, TIME_SYMBOL_MAX, CheckType.MOVIE_TIME)) {
                            return movieService.update(movieOld, new Movie(title, time));
                        }
                        else {
                            System.out.println("Вы ввели некорректное значение.\n");
                        }
                    }
                    else {
                        System.out.println("Вы ввели некорректное значение.\n");
                    }

                } else {
                    System.out.println("Фильма с таким ID не существует.\n");
                }
            } else {
                System.out.println("Вы ввели некорретный номер.\n");
            }
        }
    }

    protected boolean editEvent(){
        return false;
    }





    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public EventService getEventService() {
        return eventService;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    public MovieService getMovieService() {
        return movieService;
    }

    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }
}

