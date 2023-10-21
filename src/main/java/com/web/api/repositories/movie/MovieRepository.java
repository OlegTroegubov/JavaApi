package com.web.api.repositories.movie;

import com.web.api.model.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> getMovies();

    Movie getMovie(int movieId);

    void createMovie(Movie movie);

    void updateMovie(Movie movie, int movieId);

    void deleteMovie(int movieId);
}