package com.minnthitoo.spring_webflux.repository;

import com.minnthitoo.spring_webflux.model.Movie;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {

    // query mathod
    Flux<Movie> findByYear(Long year);

    // mongo query
    @Query("{ 'director' : ?0 }")
    Flux<Movie> getMovieByDirector(String director);

    // mongo query
    @Query("{ 'year' : { $gt : ?0 } }")
    Flux<Movie> getMoviesByYearGt(Long year);

}
