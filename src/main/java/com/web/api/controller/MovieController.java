package com.web.api.controller;

import com.web.api.model.Movie;
import com.web.api.persistence.DbSeeder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
