package com.minnthitoo.spring_webflux.service.impl;

import com.minnthitoo.spring_webflux.model.Actor;
import com.minnthitoo.spring_webflux.model.Movie;
import com.minnthitoo.spring_webflux.model.MovieDetails;
import com.minnthitoo.spring_webflux.model.dto.ActorDto;
import com.minnthitoo.spring_webflux.model.dto.MovieDetailsDto;
import com.minnthitoo.spring_webflux.model.dto.MovieDto;
import com.minnthitoo.spring_webflux.repository.ActorRepository;
import com.minnthitoo.spring_webflux.repository.MovieRepository;
import com.minnthitoo.spring_webflux.service.MovieService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired private MovieRepository movieRepository;
    @Autowired private ActorRepository actorRepository;


    @Override
    public Flux<MovieDto> getAllMovies() {
        return this.movieRepository.findAll()
                .map(this::entityToDto);
    }

    @Override
    public Mono<MovieDto> getMovieById(String movieId) {
        return this.movieRepository.findById(movieId)
                .switchIfEmpty(Mono.error(new Exception("Movie ID " + movieId + " not found.")))
                .map(this::entityToDto);
    }

    @Override
    public Mono<MovieDto> saveMovie(MovieDto movieDto) {
        Movie movie = this.dtoToEntity(movieDto);
        return this.actorRepository.saveAll(movie.getActors())
                .collectList()
                .flatMap(actors -> {
                    movie.setActors(actors);
                    return this.movieRepository.save(movie);
                })
                .map(this::entityToDto);
    }

    @Override
    public Mono<MovieDto> updateMovieById(String movieId, MovieDto movieDto) {
        return this.movieRepository.findById(movieId)
                .switchIfEmpty(Mono.error(new Exception("Movie id " + movieId + " not found.")))
                .flatMap(movie -> {
                    movieDto.setId(movie.getId());
                    return this.saveMovie(movieDto);
                });
    }

    @Override
    public Mono<MovieDto> deleteMovieById(String movieId) {
        return this.movieRepository.findById(movieId)
                .switchIfEmpty(Mono.error(new Exception("Movie id " + movieId + " not found.")))
                .flatMap(movie -> {
                    return this.movieRepository.deleteById(movieId)
                            .thenReturn(this.entityToDto(movie));
                });
    }

    private MovieDto entityToDto(Movie movie){
        MovieDto movieDto = modelMapper.map(movie, MovieDto.class);
        MovieDetailsDto movieDetailsDto = new MovieDetailsDto();
        movieDto.setDetailsDto(movieDetailsDto);

        List<ActorDto> actorDtos = modelMapper.map(movie.getActors(), new TypeToken<List<MovieDto>>(){}.getType());
        movieDto.setActors(actorDtos);
        return movieDto;
    }

    private Movie dtoToEntity(MovieDto movieDto){
        Movie movie = this.modelMapper.map(movieDto, Movie.class);

        MovieDetails movieDetails = this.modelMapper.map(movieDto.getDetailsDto(), MovieDetails.class);

        if (!movieDto.getActors().isEmpty()){
            List<Actor> actors = this.modelMapper.map(movieDto.getActors(), new TypeToken<List<Actor>>(){}.getType());
            movie.setActors(actors);
        }
        return movie;
    }
}
