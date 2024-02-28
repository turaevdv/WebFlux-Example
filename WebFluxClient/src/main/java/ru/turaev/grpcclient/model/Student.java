package ru.turaev.grpcclient.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private long id;
    private String name;
    @JsonFormat(pattern="yyyy.MM.dd")
    private LocalDate birthDay;
}