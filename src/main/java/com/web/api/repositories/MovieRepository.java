package com.web.api.repositories;

import com.web.api.model.Movie;
import com.web.api.persistence.DbSeeder;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class MovieRepository {
    List<Movie> movieList = new CopyOnWriteArrayList<>();

    public MovieRepository() throws ParseException {
        movieList.addAll(DbSeeder.getMovieList());
    }

    public List<Movie> getMovies() {
        return movieList;
    }

    public Movie getMovie(int movieId) {
        return movieList
                .stream()
                .filter(x -> x.id() == movieId)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Movie not Found"));
    }

    public Movie createMovie(Movie movie) {
        movieList.add(movie);
        return movie;
    }

    public void updateMovie(Movie movie, int movieId) {
        var existingMovie = getMovie(movieId);
        movieList.remove(existingMovie);
        movieList.add(movie);
    }

    public void deleteMovie(int movieId) {
        var existingMovie = getMovie(movieId);
        movieList.remove(existingMovie);
    }
}
