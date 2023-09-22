package com.web.api.controller;


import com.web.api.model.Movie;
import com.web.api.model.Producer;
import com.web.api.persistence.DbSeeder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
