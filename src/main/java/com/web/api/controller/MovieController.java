package com.web.api.controller;

import com.web.api.model.Movie;
import com.web.api.persistence.DbSeeder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    List<Movie> movieList = new ArrayList<>();

    public MovieController() throws ParseException {
        movieList.addAll(DbSeeder.getMovieList());
    }

    @GetMapping
    public List<Movie> getMovies() {
        return movieList;
    }

    @GetMapping("/{movie_id}")
    public Movie getMovie(@PathVariable("movie_id") int movieId) {
        return movieList.stream()
                .filter(movie -> movie.id() == movieId)
                .findAny()
                .orElse(null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        movieList.add(movie);
        return movie;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{movie_id}")
    public void updateMove(@RequestBody Movie movie, @PathVariable("movie_id") int movieId) {
        var exsistingMovie = movieList
                .stream()
                .filter(x -> x.id() == movieId)
                .findAny()
                .orElse(null);

        var movieIndex = movieList.indexOf(exsistingMovie);

        if (exsistingMovie != null) {
            exsistingMovie = new Movie(exsistingMovie.id(), movie.producerId(), movie.title(), movie.releaseDate(), movie.movieRating(), movie.isTopRanked(), movie.genre());
        }

        movieList.set(movieIndex, exsistingMovie);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{movie_id}")
    public void deleteMovie(@PathVariable("movie_id") int movieId) {
        var exsistingMovie = movieList
                .stream()
                .filter(x -> x.id() == movieId)
                .findAny()
                .orElse(null);

        movieList.remove(exsistingMovie);
    }
}
