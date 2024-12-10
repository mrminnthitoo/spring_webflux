package com.minnthitoo.spring_webflux.service.impl;

import com.minnthitoo.spring_webflux.model.dto.ReviewDto;
import com.minnthitoo.spring_webflux.repository.ReviewRepository;
import com.minnthitoo.spring_webflux.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired private ReviewRepository reviewRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Flux<ReviewDto> getAllReview() {
        return this.reviewRepository.findAll()
                .map(review->this.modelMapper.map(review, ReviewDto.class));
    }

    @Override
    public Flux<ReviewDto> getAllReviewByMovie(String movieId) {
        return this.reviewRepository.findReviewByMovieId(movieId)
                .map(review -> this.modelMapper.map(review, ReviewDto.class));
    }
}
