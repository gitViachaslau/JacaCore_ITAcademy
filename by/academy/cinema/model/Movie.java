/**
 * Класс описывает фильм
 *
 * id - номер id;
 * title - название фильма;
 * durationMinutes - продолжительность фильма в минутах;
 */

package by.academy.cinema.model;

public class Movie {

    private int id;

    private String title;

    private int durationMinutes;

    public Movie(int id, String title, int durationMinutes) {
        this.id = id;
        this.title = title;
        this.durationMinutes = durationMinutes;
    }

    public Movie(String title, int durationMinutes) {
        this.title = title;
        this.durationMinutes = durationMinutes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}
