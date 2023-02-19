package by.academy.cinema.service;

import by.academy.cinema.model.Event;

import java.util.List;

public interface EventService {

    List<Event> readAllActual();

    boolean checkIdEvent(int idEvent);

    Event readEvent (int id);

    List<Event> readAll();

    boolean update(Event eventOld, Event eventNew);

    boolean create(Event event);

    int getIdEvent(Event event);
}
