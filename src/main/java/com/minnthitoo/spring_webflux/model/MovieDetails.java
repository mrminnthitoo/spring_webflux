package com.minnthitoo.spring_webflux.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("movie_details")
public class MovieDetails {

    @Id
    private String id;

    @NotBlank
    @Size(max = 140)
    private String details;

}
