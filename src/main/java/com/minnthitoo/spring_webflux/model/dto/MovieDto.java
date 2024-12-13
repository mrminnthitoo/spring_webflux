package com.minnthitoo.spring_webflux.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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

    private MovieDetailsDto movieDetails;

    private List<String> genres;

    private List<ActorDto> actors;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public MovieDetailsDto getMovieDetails() {
        return movieDetails;
    }

    public void setMovieDetails(MovieDetailsDto movieDetails) {
        this.movieDetails = movieDetails;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<ActorDto> getActors() {
        return actors;
    }

    public void setActors(List<ActorDto> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "MovieDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", director='" + director + '\'' +
                ", createdAt=" + createdAt +
                ", detailsDto=" + movieDetails +
                ", genres=" + genres +
                ", actors=" + actors +
                '}';
    }
}
