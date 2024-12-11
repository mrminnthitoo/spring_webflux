package com.minnthitoo.spring_webflux.service;

import com.minnthitoo.spring_webflux.model.dto.MovieDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {

    Flux<MovieDto> getAllMovies();
    Flux<MovieDto> getMoviesByYear(Long year);
    Flux<MovieDto> getMovieByDirector(String director);
    Flux<MovieDto> getMovieByYearGt(Long year);
    Mono<MovieDto> getMovieById(String movieId);
    Mono<MovieDto> saveMovie(MovieDto movieDto);
    Mono<MovieDto> updateMovieById(String movieId, MovieDto movieDto);
    Mono<MovieDto> deleteMovieById (String movieId);
    Mono<MovieDto> assignActorToMovie(String movieId, String actorId);
    Flux<MovieDto> getMovieWithAverageRatingGTE(int averageRating);

}
