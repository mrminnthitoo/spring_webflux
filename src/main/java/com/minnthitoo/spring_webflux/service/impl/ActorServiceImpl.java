package com.minnthitoo.spring_webflux.service.impl;

import com.minnthitoo.spring_webflux.model.Actor;
import com.minnthitoo.spring_webflux.model.dto.ActorDto;
import com.minnthitoo.spring_webflux.repository.ActorRepository;
import com.minnthitoo.spring_webflux.service.ActorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired private ActorRepository actorRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Flux<ActorDto> getAllActors() {
        return this.actorRepository.findAll()
                .map(this::entityToDto);
    }

    @Override
    public Mono<ActorDto> getActorById(String actorId) {
        return this.actorRepository.findById(actorId)
                .switchIfEmpty(Mono.error(new Exception("Actor id " + actorId + " not found.")))
                .map(this::entityToDto);
    }

    @Override
    public Mono<ActorDto> saveActor(ActorDto actorDto) {
        Actor actor = this.modelMapper.map(actorDto, Actor.class);
        return this.actorRepository.save(actor)
                .map(this::entityToDto);
    }

    @Override
    public Mono<ActorDto> updateActorById(String actorId, ActorDto actorDto) {
        return this.actorRepository.findById(actorId)
                .switchIfEmpty(Mono.error(new Exception("Actor id " + actorId + " not found.")))
                .flatMap(actor->{
                    actor.setId(actorId);
                    actor.setFirstName(actorDto.getFirstName());
                    actor.setLastName(actorDto.getLastName());
                    return this.actorRepository.save(actor);
                })
                .map(this::entityToDto);
    }

    @Override
    public Mono<ActorDto> deleteActorById(String actorId) {
        return this.actorRepository.findById(actorId)
                .switchIfEmpty(Mono.error(new Exception("Actor id " + actorId + " not found.")))
                .flatMap(actor -> {
                    return this.actorRepository.deleteById(actorId)
                            .thenReturn(this.entityToDto(actor));
                });
    }

    private ActorDto entityToDto(Actor actor){
        return modelMapper.map(actor, ActorDto.class);
    }

    private Actor dtoToEntity(ActorDto actorDto){
        return this.modelMapper.map(actorDto, Actor.class);
    }
}
