package com.web.api.persistence;

import com.web.api.model.Movie;
import com.web.api.model.Producer;

import java.text.ParseException;
import java.util.List;

public class DbSeeder {

    private List<Movie> movieList;



    public static List<Movie> getMovieList() throws ParseException {
        Movie movie1 = new Movie(1, 1, "Godfather", "14.03.1972",9.2F,true);
        Movie movie2 = new Movie(2, 1,"Dracula","13.11.1992",7.4F,false);
        Movie movie3 = new Movie(3, 2,"Lock, Stock and Two Smoking Barrels", "28.08.1998", 8.1F, false);
        Movie movie4 = new Movie(4, 2,"Snatch", "01.09.2001", 8.2F, false);
        return List.of(movie1, movie2, movie3, movie4);

    }

    public static List<Producer> getProducerList() throws ParseException {
        Producer producer1 = new Producer(1, "Francis", "Ford Coppola");
        Producer producer2 = new Producer(2,"Guy ", "Ritchie");

        return List.of(producer1, producer2);
    }
}