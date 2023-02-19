package by.academy.cinema.service;

import by.academy.cinema.model.Movie;
import by.academy.cinema.model.Ticket;

import java.util.List;

public interface MovieService {

    Movie read(int id);

    List<Movie> readAll ();

    boolean checkIdMovie(int idMovie);

    boolean update(Movie movieOld, Movie movieNew);

    boolean create(Movie movie);
}
