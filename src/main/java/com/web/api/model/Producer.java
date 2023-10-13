package com.web.api.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public record Producer(int id, String firstName, String lastName, Date birthDay, String email, boolean isMale) {

    public Producer(int id, String firstName, String lastName, String birthDay, String email, boolean isMale) throws ParseException {
        this(id, firstName, lastName, new SimpleDateFormat("dd.MM.yyyy").parse(birthDay), email, isMale);
    }
}