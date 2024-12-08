package com.minnthitoo.spring_webflux.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

    private String id;

    @NotBlank
    @Size(max = 140)
    private String name;

    @NotNull
    private Integer year;

    @NotNull
    private String director;

    private Date createdAt;

    private MovieDetailsDto detailsDto;

    private List<String> genres;

    private List<ActorDto> actors;

}
