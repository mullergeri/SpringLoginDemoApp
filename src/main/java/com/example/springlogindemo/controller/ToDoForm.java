package com.example.springlogindemo.controller;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class ToDoForm {

    @Size(min= 5, max= 50)
    private String description;

    @Future
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private LocalDate deadline;
}
