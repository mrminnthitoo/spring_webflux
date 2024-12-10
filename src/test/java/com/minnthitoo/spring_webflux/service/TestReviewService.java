package com.minnthitoo.spring_webflux.service;

import com.minnthitoo.spring_webflux.WaitUntail;
import com.minnthitoo.spring_webflux.model.dto.ReviewDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@Rollback(false)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestReviewService {

    @Autowired private ReviewService reviewService;

    @Test
    public void testGetAllReview(){
        this.reviewService.getAllReview()
                .collectList()
                .map(review->{
                    assertTrue(review.size() > 0);
                });
        WaitUntail.wait(2000);
    }

    @Test
    public void getReviewByMovieId(){
        this.reviewService.getAllReviewByMovie("6753206ace6cdf479a81e542")
                .collectList()
                .subscribe(reviews->{
                    for (ReviewDto reviewDto : reviews){
                        log.info("Review {}", reviewDto);
                    }
                });
        WaitUntail.wait(2000);
    }

}
