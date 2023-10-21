package com.web.api.repositories.producer;

import com.web.api.model.Movie;
import com.web.api.model.Producer;

import java.util.List;

public interface ProducerRepository {
    List<Producer> getProducers();

    Producer getProducer(int producerId);

    List<Movie> getMoviesByProducerId(int producerId);

    void createProducer(Producer producer);

    void updateProducer(Producer producer, int producerId);

    void deleteProducer(int producerId);
}
