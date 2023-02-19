package by.academy.cinema.service.impl;

import by.academy.cinema.model.Movie;
import by.academy.cinema.model.Ticket;
import by.academy.cinema.repository.MovieRepository;
import by.academy.cinema.service.MovieService;

import java.util.List;

public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @Override
    public Movie read(int id) {
        return movieRepository.read(id);
    }

    @Override
    public List<Movie> readAll() {
        return movieRepository.readAll();
    }

    @Override
    public boolean checkIdMovie(int idMovie) {
        Movie movie = null;
        boolean result = false;

        try{
            movie = movieRepository.read(idMovie);
            if (movie != null){
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
    public boolean update(Movie movieOld, Movie movieNew) {
        return movieRepository.update(movieOld, movieNew);
    }

    @Override
    public boolean create(Movie movie) {
        return movieRepository.create(movie);
    }


}
