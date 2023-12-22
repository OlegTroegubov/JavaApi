package com.web.api.repositories;

import com.web.api.exception.NotFoundException;
import com.web.api.model.Side;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SideRepositoryH2 implements SideRepository {
    private static final String CREATE = """
            INSERT INTO SIDES (MOTION, LOT, WAR, RISE)
            VALUES (:motion, :lot, :war, :rise)
            """;

    private static final String UPDATE = """
            UPDATE SIDES SET
            MOTION = :motion,
            LOT = :lot,
            WAR = :war,
            RISE = :rise
            WHERE BABY = :baby
            """;

    private final RowMapper<Side> rowMapper = new DataClassRowMapper<>(Side.class);
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SideRepositoryH2(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Side> readAll() {
        return jdbcTemplate.query("SELECT * FROM SIDES", rowMapper);
    }

    @Override
    public Side read(Long baby) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM SIDES WHERE BABY = ?", rowMapper, baby);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Movie with id = [" + baby + "] not found", e);
        }
    }

    @Override
    public void create(Side side) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(side);
        namedParameterJdbcTemplate.update(CREATE, parameterSource);
    }

    @Override
    public void delete(Long baby) {
        jdbcTemplate.update("DELETE FROM SIDES WHERE BABY = ?", baby);
    }

    @Override
    public void update(Side side, Long baby) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(baby);
        namedParameterJdbcTemplate.update(UPDATE, parameterSource);
    }
}
