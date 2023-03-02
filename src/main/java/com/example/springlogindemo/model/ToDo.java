package com.example.springlogindemo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@NoArgsConstructor
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String description;

    private LocalDate deadline;

    private Boolean done;

    @ManyToOne
    private AppUser owner;
}
