package com.minnthitoo.spring_webflux.service;

import com.minnthitoo.spring_webflux.WaitUntail;
import com.minnthitoo.spring_webflux.model.Movie;
import com.minnthitoo.spring_webflux.model.Review;
import com.minnthitoo.spring_webflux.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReviewServiceTest {

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    public void testReviewWithAvgRating(){
        this.reviewRepository
                .findAll()
                .groupBy(review -> review.getMovie().getId())
                .flatMap(Flux::collectList)
                .map(reviews -> {
                    double sum = 0;
                    for(Review review : reviews){
                        sum += review.getRating();
                    }
                    Double average = sum / reviews.size();
                    return Pair.of(reviews.get(0).getMovie(), average);

                })
                .filter(pair -> pair.getSecond() > 4)
                .subscribe(pair->{
                    System.out.println("Movie " + pair.getFirst().getName() + " Rating " + pair.getSecond());
                });
        WaitUntail.wait(2000);
    }

}
