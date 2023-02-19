package by.academy.cinema.service.impl;

import by.academy.cinema.model.Event;
import by.academy.cinema.repository.EventRepository;
import by.academy.cinema.service.EventService;

import java.time.LocalDateTime;
import java.util.List;

public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> readAllActual() {

        List<Event> eventsActual = null;

        try {
           eventsActual = eventRepository.getActualEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return eventsActual;
        }
    }

    @Override
    public boolean checkIdEvent(int idEvent) {
        Event read = eventRepository.read(idEvent);
        if((read == null) || (read.getDateTimeStart().isBefore(LocalDateTime.now()))){
            return false;
        }
        return true;
    }

    @Override
    public Event readEvent(int id) {
       return eventRepository.read(id);
    }

    @Override
    public List<Event> readAll() {
        return eventRepository.readAll();
    }

    @Override
    public boolean update(Event eventOld, Event eventNew) {
        return eventRepository.update(eventOld, eventNew);
    }

    @Override
    public boolean create(Event event) {
        return eventRepository.create(event);
    }

    @Override
    public int getIdEvent(Event event) {
        return eventRepository.getIdEvent(event);
    }


}
