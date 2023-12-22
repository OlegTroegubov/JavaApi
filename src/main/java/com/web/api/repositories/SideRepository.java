package com.web.api.repositories;

import com.web.api.model.Side;

import java.util.List;

public interface SideRepository {
    List<Side> readAll();

    Side read(Long baby);

    void create(Side side);

    void delete(Long baby);

    void update(Side side, Long baby);
}
