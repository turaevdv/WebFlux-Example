package ru.turaev.grpcserver.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Student {
    private final long id;
    private final String name;
    private final LocalDate birthDay;
}
