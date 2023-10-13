package com.web.api.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public record Movie(int id, int producerId, String title, Date releaseDate, float movieRating, boolean isTopHundred,
                    MovieGenre genre) {

    public Movie(int id, int producerId, String title, String releaseDate, float movieRating, boolean isTopHundred, MovieGenre genre) throws ParseException {
        this(id, producerId, title, new SimpleDateFormat("dd.MM.yyyy").parse(releaseDate), movieRating, isTopHundred, genre);
    }
}

