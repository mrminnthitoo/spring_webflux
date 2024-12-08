package com.minnthitoo.spring_webflux.repository;

import com.minnthitoo.spring_webflux.model.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReviewRepository extends ReactiveMongoRepository<Review, String> {
}
