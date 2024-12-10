package com.minnthitoo.spring_webflux.service;

import com.minnthitoo.spring_webflux.model.dto.ReviewDto;
import reactor.core.publisher.Flux;

public interface ReviewService {

    Flux<ReviewDto> getAllReview();
    Flux<ReviewDto> getAllReviewByMovie(String movieId);

}
