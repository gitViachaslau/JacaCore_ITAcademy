package by.academy.cinema.controller;

import by.academy.cinema.model.*;
import by.academy.cinema.service.EventService;
import by.academy.cinema.service.MovieService;
import by.academy.cinema.service.TicketService;
import by.academy.cinema.service.UserService;

public class ControllerFactory {

    UserService userService;

    EventService eventService;

    MovieService movieService;

    TicketService ticketService;


    public ControllerFactory(UserService userService,
                             EventService eventService,
                             MovieService movieService,
                             TicketService ticketService) {
        this.userService = userService;
        this.eventService = eventService;
        this.movieService = movieService;
        this.ticketService = ticketService;
    }

    public Controller getController (User user){
        if (UserType.ADMIN.equals(user.getUserType())){
            return new AdminMenuController(user, userService, eventService, movieService, ticketService);
        }
        else if (UserType.MANAGER.equals(user.getUserType())){
            return new ManagerMenuController(user, userService, eventService, movieService, ticketService);
        }
        else{
            return new UserMenuController(user, userService, eventService, movieService, ticketService);
        }
    }
}
