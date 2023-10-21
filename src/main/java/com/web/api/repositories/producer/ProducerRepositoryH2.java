package com.web.api.repositories.producer;

import com.web.api.exception.NotFoundException;
import com.web.api.model.Movie;
import com.web.api.model.Producer;
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
public class ProducerRepositoryH2 implements ProducerRepository {

    private static final String CREATE = """
            INSERT INTO PRODUCERS (PRODUCER_ID, FIRST_NAME, LAST_NAME, BIRTHDAY, EMAIL, IS_MALE)
            VALUES (:producerId, :firstName, :lastName, :birthday, :email, :male)
            """;
    private static final String UPDATE = """
            UPDATE PRODUCERS SET FIRST_NAME = :firstName,
            LAST_NAME = :lastName,
            BIRTHDAY = :birthday,
            EMAIL = :email,
            IS_MALE = :male
            WHERE PRODUCER_ID = :producerId
            """;

    private final RowMapper<Producer> rowMapper = new DataClassRowMapper<>(Producer.class);
    private final RowMapper<Movie> movieRowMapper = new DataClassRowMapper<>(Movie.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ProducerRepositoryH2(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Producer> getProducers() {
        return jdbcTemplate.query("SELECT * FROM PRODUCERS", rowMapper);
    }

    @Override
    public Producer getProducer(int producerId) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM PRODUCERS WHERE PRODUCER_ID = ?", rowMapper, producerId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Producer with id = [" + producerId + "] not found", e);
        }
    }

    @Override
    public List<Movie> getMoviesByProducerId(int producerId) {
        return jdbcTemplate.query("SELECT * FROM MOVIES WHERE PRODUCER_ID = ?", movieRowMapper, producerId);
    }

    @Override
    public void createProducer(Producer producer) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(producer);
        namedParameterJdbcTemplate.update(CREATE, parameterSource);
    }

    @Override
    public void updateProducer(Producer producer, int producerId) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(producer);
        namedParameterJdbcTemplate.update(UPDATE, parameterSource);
    }

    @Override
    public void deleteProducer(int producerId) {
        jdbcTemplate.update("DELETE FROM PRODUCERS WHERE PRODUCER_ID =?", producerId);
    }
}
