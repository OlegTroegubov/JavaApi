package com.web.api.controller;


import com.web.api.model.Producer;
import com.web.api.persistence.DbSeeder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("api/producers")
public class ProducerController {
    private final List<Producer> producerList;

    public ProducerController() throws ParseException {
        this.producerList = DbSeeder.getProducerList();
    }

    @GetMapping
    public List<Producer> getProducers(){
        return producerList;
    }

    @GetMapping("/{producer_id}")
    public Producer getProducer(@PathVariable("producer_id") int producerId){
        return producerList.stream()
                .filter(producer -> producer.id == producerId)
                .findAny()
                .orElse(null);
    }
}