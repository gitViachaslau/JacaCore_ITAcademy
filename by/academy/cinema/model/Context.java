/**
 * Класс описывавет текущего пользователя и содержит сервисы для работы
 */

package by.academy.cinema.model;

import by.academy.cinema.service.EventService;
import by.academy.cinema.service.MovieService;
import by.academy.cinema.service.TicketService;
import by.academy.cinema.service.UserService;

public class Context {

    private User user;

    private UserService userService;

    private TicketService ticketService;

    private MovieService movieService;

    private EventService eventService;

    public User getUser() {
        return user;
    }

    public Context setUser(User user) {
        this.user = user;
        return this;
    }

    public UserService getUserService() {
        return userService;
    }

    public Context setUserService(UserService userService) {
        this.userService = userService;
        return this;
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public Context setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
        return this;
    }

    public MovieService getMovieService() {
        return movieService;
    }

    public Context setMovieService(MovieService movieService) {
        this.movieService = movieService;
        return this;
    }

    public EventService getEventService() {
        return eventService;
    }

    public Context setEventService(EventService eventService) {
        this.eventService = eventService;
        return this;
    }
}
