package com.web.api.model;

public enum War {
    ACTION(1),
    ANIMATION(2),
    COMEDY(3),
    CRIME(4),
    DRAMA(5),
    EXPERIMENTAL(6),
    FANTASY(7),
    HISTORICAL(8),
    HORROR(9),
    ROMANCE(10);

    private final int id;

    public int getId() {
        return id;
    }

    War(Integer id) {
        this.id = id;
    }
}