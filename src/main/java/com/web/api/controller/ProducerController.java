package com.web.api.controller;


import com.web.api.model.Movie;
import com.web.api.model.Producer;
import com.web.api.repositories.ProducerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("api/producers")
public class ProducerController {

    private final ProducerRepository repository;

    public ProducerController(ProducerRepository repository) throws ParseException {
        this.repository = repository;
    }

    @GetMapping
    public List<Producer> getProducers() {
        return repository.getProducers();
    }

    @GetMapping("/{producer_id}")
    public Producer getProducer(@PathVariable("producer_id") int producerId) {
        return repository.getProducer(producerId);
    }

    @GetMapping("/{producer_id}/movies")
    public List<Movie> getMoviesByProducerId(@PathVariable("producer_id") int producerId) throws ParseException {
        return repository.getMoviesByProducerId(producerId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Producer createProducer(@RequestBody Producer producer) {
        return repository.createProducer(producer);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{producer_id}")
    public void updateProducer(@RequestBody Producer producer, @PathVariable("producer_id") int producerId) {
        repository.updateProducer(producer, producerId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{producer_id}")
    public void deleteProducer(@PathVariable("producer_id") int producerId) {
        repository.deleteProducer(producerId);
    }
}
