package com.minnthitoo.spring_webflux.repository;

import com.minnthitoo.spring_webflux.WaitUntail;
import com.minnthitoo.spring_webflux.model.Actor;
import com.minnthitoo.spring_webflux.model.Movie;
import com.minnthitoo.spring_webflux.model.MovieDetails;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@Rollback(value = false)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestMovieRepository {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Test
    public void insertMovie(){

        //Titanic
        String leonadoId = "675a467326d49c44a98c0d62";
        String kateId = "675a46be9394bd270650f4f4";

        List<String> ids = new ArrayList<>();
        ids.add(leonadoId);
        ids.add(kateId);

        List<String> genres = new ArrayList<>();
        genres.add("Drama");
        genres.add("History");

        Movie movie = new Movie();
        movie.setName("Titanic");
        movie.setYear(2002);
        movie.setDirector("Director 1");
        movie.setGenres(genres);

        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setDetails("Titanic Movie Details");
        movie.setDetails(movieDetails);

        this.actorRepository.findAllById(ids)
                .collectList()
                .flatMap(actors -> {
                    movie.setActors(actors);
                    return this.movieRepository.save(movie);
                })
                .subscribe(savedMovie->{
                    System.out.println("Saved Movie " + savedMovie);
                    assertEquals(2, savedMovie.getActors().size());
                }, error->{
                    System.out.println("Error " + error.getMessage());
                });


        WaitUntail.wait(2000);

    }

}
