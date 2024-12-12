package com.minnthitoo.spring_webflux.repository;

import com.minnthitoo.spring_webflux.WaitUntail;
import com.minnthitoo.spring_webflux.model.Review;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@Rollback(value = false)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestReviewRepository {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired private ReviewRepository reviewRepository;

    @Test
    public void insertReview(){

        String movieId = "675a5760a93c4f21fba877b6";

        this.movieRepository.findById(movieId)
                .flatMap(movie -> {
                    Review review = new Review();

                    review.setMovie(movie);
                    review.setRating(5);
                    review.setReview("Best Movie");
                    return this.reviewRepository.save(review);
                })
                .subscribe(savedReview->{
                    System.out.println(savedReview.toString());
                    assertEquals(5, savedReview.getRating());
                });

        WaitUntail.wait(2000);

    }

}
