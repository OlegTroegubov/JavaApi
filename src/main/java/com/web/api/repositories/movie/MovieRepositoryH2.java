package com.web.api.repositories.movie;

import com.web.api.exception.NotFoundException;
import com.web.api.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepositoryH2 implements MovieRepository {

    private static final String CREATE = """
            INSERT INTO MOVIES (MOVIE_ID, PRODUCER_ID, TITLE, RELEASE_DATE, MOVIE_RATING, IS_TOP_RANKED, GENRE)
            VALUES (:movieId, :producerId, :title, :releaseDate, :movieRating, :topRanked, :genre.id)
            """;

    private static final String UPDATE = """
            UPDATE MOVIES SET PRODUCER_ID = :producerId,
            TITLE = :title,
            RELEASE_DATE = :releaseDate,
            MOVIE_RATING = :movieRating,
            IS_TOP_RANKED = :topRanked,
            GENRE = :genre.id
            WHERE MOVIE_ID = :movieId
            """;

    private final RowMapper<Movie> rowMapper = new DataClassRowMapper<>(Movie.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public MovieRepositoryH2(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Movie> getMovies() {
        return jdbcTemplate.query("SELECT * FROM MOVIES", rowMapper);
    }

    public Movie getMovie(int movieId) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM MOVIES WHERE MOVIE_ID = ?", rowMapper, movieId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Movie with id = [" + movieId + "] not found", e);
        }
    }

    public void createMovie(Movie movie) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(movie);
        namedParameterJdbcTemplate.update(CREATE, parameterSource);
    }

    public void updateMovie(Movie movie, int movieId) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(movie);
        namedParameterJdbcTemplate.update(UPDATE, parameterSource);
    }

    public void deleteMovie(int movieId) {
        jdbcTemplate.update("DELETE FROM MOVIES WHERE MOVIE_ID = ?", movieId);
    }
}
