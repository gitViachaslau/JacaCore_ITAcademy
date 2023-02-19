/**
 * Класс описывавет мероприятие
 *
 * id - номер id;
 * pcsTicket - Количество выпущенных билетов для этого мероприятия;
 * idMovie - ID фильма;
 * dateTimeStart - время начала показа;
 * price - стоимость билета;
 */

package by.academy.cinema.model;

import java.time.LocalDateTime;

public class Event {

    private int id;

    private int pcsTicket;

    private int idMovie;

    private LocalDateTime dateTimeStart;

    private double price;

    public Event(int id, int pcsTicket, int idMovie, LocalDateTime dateTimeStart, double price) {
        this.id = id;
        this.pcsTicket = pcsTicket;
        this.idMovie = idMovie;
        this.dateTimeStart = dateTimeStart;
        this.price = price;
    }

    public Event(int pcsTicket, int idMovie, LocalDateTime dateTimeStart, double price) {
        this.pcsTicket = pcsTicket;
        this.idMovie = idMovie;
        this.dateTimeStart = dateTimeStart;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPcsTicket() {
        return pcsTicket;
    }

    public void setPcsTicket(int pcsTicket) {
        this.pcsTicket = pcsTicket;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public LocalDateTime getDateTimeStart() {
        return dateTimeStart;
    }

    public void setDateTimeStart(LocalDateTime dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
