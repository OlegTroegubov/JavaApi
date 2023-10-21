package com.web.api.model;

import java.util.Date;

public record Producer(int producerId, String firstName, String lastName, Date birthday, String email, boolean isMale) {

}