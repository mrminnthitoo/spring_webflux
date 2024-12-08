package com.minnthitoo.spring_webflux.repository;

import com.minnthitoo.spring_webflux.model.Actor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends ReactiveMongoRepository<Actor, String> {
}
