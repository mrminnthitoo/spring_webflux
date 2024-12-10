package com.minnthitoo.spring_webflux.service;

import com.minnthitoo.spring_webflux.model.dto.ActorDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ActorService {

    Flux<ActorDto> getAllActors();
    Mono<ActorDto> getActorById(String actorId);
    Mono<ActorDto> saveActor(ActorDto actorDto);
    Mono<ActorDto> updateActorById(String actorId, ActorDto actorDto);
    Mono<ActorDto> deleteActorById(String actorId);

}
