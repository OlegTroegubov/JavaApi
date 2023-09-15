package com.web.api.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public record Producer(int id, String firstName, String lastName, Date dateOfBirth, String email, boolean isBadGuy){

    public Producer(int id, String firstName, String lastName, String dateOfBirth, String email, boolean isBadGuy) throws ParseException {
        this(id, firstName, lastName, new SimpleDateFormat("dd.MM.yyyy").parse(dateOfBirth), email, isBadGuy);
    }
}