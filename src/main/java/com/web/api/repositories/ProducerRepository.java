package com.web.api.repositories;

import com.web.api.model.Movie;
import com.web.api.model.Producer;
import com.web.api.persistence.DbSeeder;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ProducerRepository {
    List<Producer> producerList = new CopyOnWriteArrayList<>();

    public ProducerRepository() throws ParseException {
        producerList.addAll(DbSeeder.getProducerList());
    }

    public List<Producer> getProducers() {
        return producerList;
    }

    public Producer getProducer(int producerId) {
        return producerList
                .stream()
                .filter(x -> x.id() == producerId)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Producer not Found"));
    }

    public List<Movie> getMoviesByProducerId(int producerId) throws ParseException {
        return DbSeeder.getMovieList()
                .stream()
                .filter(x -> x.producerId() == producerId)
                .toList();
    }

    public Producer createProducer(Producer producer) {
        producerList.add(producer);
        return producer;
    }

    public void updateProducer(Producer producer, int producerId) {
        var existingProducer = getProducer(producerId);
        producerList.remove(existingProducer);
        producerList.add(producer);
    }

    public void deleteProducer(int producerId) {
        var existingProducer = getProducer(producerId);
        producerList.remove(existingProducer);
    }
}
