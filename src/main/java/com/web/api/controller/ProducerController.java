package com.web.api.controller;


import com.web.api.model.Movie;
import com.web.api.model.Producer;
import com.web.api.persistence.DbSeeder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/producers")
public class ProducerController {

    List<Producer> producerList = new ArrayList<>();

    public ProducerController() throws ParseException {
        producerList.addAll(DbSeeder.getProducerList());
    }

    @GetMapping
    public List<Producer> getProducers() {
        return producerList;
    }

    @GetMapping("/{producer_id}")
    public Producer getProducer(@PathVariable("producer_id") int producerId) {
        return producerList
                .stream()
                .filter(producer -> producer.id() == producerId)
                .findAny()
                .orElse(null);
    }

    @GetMapping("/{producer_id}/movies")
    public List<Movie> getMoviesByProducerId(@PathVariable("producer_id") int producerId) throws ParseException {
        List<Movie> movieList = DbSeeder.getMovieList();
        return movieList
                .stream()
                .filter(movie -> movie.producerId() == producerId)
                .toList();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Producer createProducer(@RequestBody Producer producer) {
        producerList.add(producer);
        return producer;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{producer_id}")
    public void updateProducer(@RequestBody Producer producer, @PathVariable("producer_id") int producerId) {
        var exsistingProducer = producerList
                .stream()
                .filter(x -> x.id() == producerId)
                .findAny()
                .orElse(null);

        producerList.remove(exsistingProducer);
        producerList.add(producer);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{producer_id}")
    public void deleteProducer(@PathVariable("producer_id") int producerId) {
        var exsistingProducer = producerList
                .stream()
                .filter(x -> x.id() == producerId)
                .findAny()
                .orElse(null);

        if (exsistingProducer != null) {
            producerList.remove(exsistingProducer);
        }

    }
}
