package by.academy.cinema.repository;

import by.academy.cinema.model.Ticket;

import java.util.List;

public interface TicketRepository extends CommonRepository<Ticket> {

    int getCountFreeTicketsForEvent(int idEventInput);

    List<Ticket> getTicketsForEvent(int idEventInput);

    List<Ticket> getTicketsForUser(int idUserInput);
}

