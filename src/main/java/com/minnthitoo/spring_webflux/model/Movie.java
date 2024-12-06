package com.minnthitoo.spring_webflux.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document(collation = "movies")
public class Movie {

    @Id
    private String id;

    @NotBlank
    @Size(max = 140)
    private String name;

    @NotNull
    private Integer year;

    @NotNull
    private String director;

    private Date createdAt;

    // Reference Model
    @DBRef
    private List<Actor> actors;

    private ArrayList<String> genres;

    // Embedded Model
    private MovieDetails details;

}
