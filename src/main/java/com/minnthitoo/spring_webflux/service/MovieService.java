package com.minnthitoo.spring_webflux.service;

import com.minnthitoo.spring_webflux.model.dto.MovieDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {

    Flux<MovieDto> getAllMovies();
    Mono<MovieDto> getMovieById(String movieId);
    Mono<MovieDto> saveMovie(MovieDto movieDto);
    Mono<MovieDto> updateMovieById(String movieId, MovieDto movieDto);

}
