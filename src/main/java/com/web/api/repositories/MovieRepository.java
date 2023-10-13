package com.web.api.repositories;

import com.web.api.model.Movie;
import com.web.api.persistence.DbSeeder;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class MovieRepository {
    List<Movie> movies = new CopyOnWriteArrayList<>();

    public MovieRepository() throws ParseException {
        movies.addAll(DbSeeder.getMovieList());
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public Movie getMovie(int movieId) {
        return movies
                .stream()
                .filter(x -> x.id() == movieId)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Movie not Found"));
    }

    public Movie createMovie(Movie movie) {
        movies.add(movie);
        return movie;
    }

    public void updateMovie(Movie movie, int movieId) {
        var existingMovie = getMovie(movieId);
        movies.remove(existingMovie);
        movies.add(movie);
    }

    public void deleteMovie(int movieId) {
        var existingMovie = getMovie(movieId);
        movies.remove(existingMovie);
    }
}
