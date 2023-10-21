package com.web.api.controller;

import com.web.api.model.Movie;
import com.web.api.repositories.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    private final MovieRepository repository;

    @Autowired
    public MovieController(MovieRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Movie> getMovies() {
        return repository.getMovies();
    }

    @GetMapping("/{movie_id}")
    public Movie getMovie(@PathVariable("movie_id") int movieId) {
        return repository.getMovie(movieId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createMovie(@RequestBody Movie movie) {
        repository.createMovie(movie);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{movie_id}")
    public void updateMove(@RequestBody Movie movie, @PathVariable("movie_id") int movieId) {
        repository.updateMovie(movie, movieId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{movie_id}")
    public void deleteMovie(@PathVariable("movie_id") int movieId) {
        repository.deleteMovie(movieId);
    }
}
