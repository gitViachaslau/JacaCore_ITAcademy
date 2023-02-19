/**
 * Класс для вывода фильмов
 */

package by.academy.cinema.controller2.items;

import by.academy.cinema.controller2.AbstractMenuItem;
import by.academy.cinema.model.Context;
import by.academy.cinema.model.Movie;

import java.util.List;

public class ShowMovies  extends AbstractMenuItem {
    public ShowMovies(String title, String executeCommand, Context context) {
        super(title, executeCommand, context);
    }

    @Override
    public void start() {
        System.out.println("\n=== Список фильмов ===");
        List<Movie> movies = getContext().getMovieService().readAll();

        if (movies == null) {
            System.out.println("Фильмов нет");
            return;
        }

        for (Movie m : movies) {
            printInfoMovie(m);
        }

    }

    protected void printInfoMovie(Movie movie) {
        System.out.println("Фильм ID - " + movie.getId());
        System.out.println("          Название - " + movie.getTitle());
        System.out.println("          Продолжительность: " + movie.getDurationMinutes() + " минут");
    }
}
