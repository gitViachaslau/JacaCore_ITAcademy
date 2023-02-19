package by.academy.cinema.service;

import by.academy.cinema.model.Ticket;

import java.util.List;

public interface TicketService {

    List <Ticket> readAll();

    int getFreePcs(int idEvent);

    boolean update(Ticket ticketOld, Ticket ticketNew);

    List<Ticket> getTicketsForEvent(int idEvent);

    Ticket read(int id);

    boolean buyTicket(int idUser, int idTicket, int seat, double price);

    List<Ticket> readTicketsUser(int idUser);

    boolean returnTicket(int idTicket);

    boolean checkIdTicket(int idTicket);

    boolean createTicket(Ticket ticket);

}
