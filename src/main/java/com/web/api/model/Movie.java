package com.web.api.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Movie {
    public int id;
    public int producerId;
    public String title;
    public Date releaseDate;
    public float movieRating;
    public boolean isTopRanked;

    public Movie(int id, int producerId, String title, String releaseDate, float movieRating, boolean isTopRanked) throws ParseException {
        this.id = id;
        this.producerId = producerId;
        this.title = title;
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        this.releaseDate = formatter.parse(releaseDate);
        this.movieRating = movieRating;
        this.isTopRanked = isTopRanked;
    }
}
