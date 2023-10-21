package com.web.api.model;

import java.util.Date;

public record Movie(int movieId, int producerId, String title, Date releaseDate, float movieRating, boolean isTopRanked,
                    MovieGenre genre) {
}

