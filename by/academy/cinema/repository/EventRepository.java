package by.academy.cinema.repository;

import by.academy.cinema.model.Event;

import java.util.List;

public interface EventRepository extends CommonRepository<Event>{

    List<Event> getActualEvents();

    int getIdEvent(Event event);
}
