package com.minnthitoo.spring_webflux.service;

import com.minnthitoo.spring_webflux.WaitUntail;
import com.minnthitoo.spring_webflux.model.MovieDetails;
import com.minnthitoo.spring_webflux.model.dto.ActorDto;
import com.minnthitoo.spring_webflux.model.dto.MovieDetailsDto;
import com.minnthitoo.spring_webflux.model.dto.MovieDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@Rollback(value = false)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestMovieService {

    @Autowired private MovieService movieService;

    @Test
    public void testGetAllMovies(){
        this.movieService.getAllMovies()
                .collectList()
                .subscribe(movies->{
//                    log.info("Movies {}", movies);
                    for (MovieDto movie : movies){
                        System.out.println("Movie " + movie);
                    }
                });
        WaitUntail.wait(2000);
    }

    @Test
    public void testGetMoviesByYearGt(){
        this.movieService.getMoviesByYear(2010L)
                .subscribe(movie->{
//                    log.info("Movie {}", movie);
                });
        WaitUntail.wait(2000);
    }

    @Test
    public void testFindMovieByDirector(){
        this.movieService.getMovieByDirector("Director One")
                .collectList()
                .subscribe(movie->{
//                    log.info("Movie {}", movie);
                    assertNotNull(movie);
                });
        WaitUntail.wait(2000);
    }

    @Test
    public void testGetMovieByYearGt(){
        this.movieService.getMovieByYearGt(2010L)
                .collectList()
                .subscribe(movie->{
                    assertTrue(movie.size() > 0);
                });
    }

    @Test
    public void testGetMovieById(){
        String id = "675a5760a93c4f21fba877b6";
        this.movieService.getMovieById(id)
                .subscribe(movieDto -> {
//                    log.info("Movie {}", movieDto);
                    System.out.println("Movie " + movieDto);
                }, error -> {
//                    log.info("Error {}", error.getMessage());
                });
        WaitUntail.wait(2000);
    }

    @Test
    public void testSaveMovie(){
        MovieDto movieDto = new MovieDto();
        movieDto.setName("Movie Two");

        MovieDetailsDto movieDetailsDto = new MovieDetailsDto();
        movieDetailsDto.setDetails("Movie Two Details");
        movieDto.setDetailsDto(movieDetailsDto);

        movieDto.setDirector("Movie Two Director");

        List<ActorDto> actorDtos = new ArrayList<>();
        ActorDto actorDto = new ActorDto();
        actorDto.setFirstName("Actor");
        actorDto.setLastName("2");
        actorDtos.add(actorDto);

        movieDto.setActors(actorDtos);

        this.movieService.saveMovie(movieDto)
                .subscribe(result->{
//                    log.info("Movie {}", result);
                    System.out.println("Saved Movie " + result);
                }, error->{
                    System.out.println("Error " + error.getMessage());
                });

    }

    @Test
    public void testUpdateMovieById(){
        MovieDto movieDto = new MovieDto();
        movieDto.setName("Movie Two Updated");

        MovieDetailsDto movieDetailsDto = new MovieDetailsDto();
        movieDetailsDto.setDetails("Movie Two Details Updated.");

        movieDto.setDetailsDto(movieDetailsDto);

        List<ActorDto> actorDtos = new ArrayList<>();
        ActorDto actorDto = new ActorDto();
        actorDto.setFirstName("Actor");
        actorDto.setLastName("7");
        actorDtos.add(actorDto);

        movieDto.setActors(actorDtos);

        this.movieService.updateMovieById("675a5bc0f3be662c38c93854", movieDto)
                .subscribe(result->{
//                    log.info("Updated Movie {}", result);
                    System.out.println("Updated");
                });
        WaitUntail.wait(2000);
    }

}
