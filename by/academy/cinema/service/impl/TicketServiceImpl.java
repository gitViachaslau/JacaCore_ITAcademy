package by.academy.cinema.service.impl;

import by.academy.cinema.model.Ticket;
import by.academy.cinema.repository.TicketRepository;
import by.academy.cinema.service.TicketService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    @Override
    public List<Ticket> readAll() {
        return ticketRepository.readAll();
    }

    @Override
    public int getFreePcs(int idEvent) {
        return ticketRepository.getCountFreeTicketsForEvent(idEvent);
    }

    @Override
    public boolean update(Ticket ticketOld, Ticket ticketNew) {
        return ticketRepository.update(ticketOld, ticketNew);
    }

    @Override
    public List<Ticket> getTicketsForEvent(int idEvent) {
        return ticketRepository.getTicketsForEvent(idEvent);
    }

    @Override
    public Ticket read(int id) {
       return ticketRepository.read(id);
    }

    @Override
    public boolean buyTicket(int idUser, int idTicket, int seat, double price) {

        boolean result = false;
        try{
            Ticket ticketOld = ticketRepository.read(idTicket);

            if(ticketOld == null){
                return result;
            }

            String idUserString = String.valueOf(idUser);

            Ticket ticketNew = new Ticket(ticketOld.getId(), idUserString, ticketOld.getIdEvent(),
                    seat, price, true);

            result = ticketRepository.update(ticketOld, ticketNew);

        } catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            return result;
        }
    }

    @Override
    public List<Ticket> readTicketsUser(int idUser) {
        return ticketRepository.getTicketsForUser(idUser);
    }

    @Override
    public boolean returnTicket(int idTicket) {
        boolean result = false;

        try{
            Ticket oldTicket = ticketRepository.read(idTicket);

            Ticket newTicket = new Ticket(oldTicket.getId(),
                    "",oldTicket.getIdEvent(),oldTicket.getSeat(), 0, false);

            result = update(oldTicket, newTicket);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean checkIdTicket(int idTicket) {
        Ticket ticket = null;
        boolean result = false;

        try{
           ticket = ticketRepository.read(idTicket);
           if (ticket != null){
               result = true;
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return result;
        }
    }

    @Override
    public boolean createTicket(Ticket ticket) {
        return ticketRepository.create(ticket);
    }
}
