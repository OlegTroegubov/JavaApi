package com.web.api.controller;

import com.web.api.exception.NotFoundException;
import com.web.api.model.Side;
import com.web.api.repositories.SideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sides")
public class SideController {
    private final SideRepository repository;

    @Autowired
    public SideController(SideRepository repository) {
        this.repository = repository;
    }

    public List<Side> readAll() {
        return repository.readAll();
    }

    @GetMapping("/{baby}")
    public Side read(@PathVariable("baby") Long baby) {
        return repository.read(baby);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createProducer(@RequestBody Side side) {
        repository.create(side);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{baby}")
    public void updateProducer(@RequestBody Side producer, @PathVariable("baby") Long baby) {
        repository.update(producer, baby);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{baby}")
    public void deleteProducer(@PathVariable("baby") Long baby) {
        repository.delete(baby);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleException(NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
