package com.minnthitoo.spring_webflux.repository;

import com.minnthitoo.spring_webflux.WaitUntail;
import com.minnthitoo.spring_webflux.model.Actor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@Slf4j
@Rollback(value = false)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestActor {

    @Autowired
    private ActorRepository actorRepository;

    @Test
    public void testInsertActor(){
        Actor actor = new Actor();
        actor.setFirstName("Actor");
        actor.setLastName("Three");

        this.actorRepository.save(actor)
                .flatMap(savedActor->this.actorRepository.findById(savedActor.getId()))
                .subscribe(result->{
                    System.out.println("Actor " + result.toString());
                });
        WaitUntail.wait(2000);
    }

    @Test
    public void testGetActorById(){
        String leonardoId = "675a467326d49c44a98c0d62";
        this.actorRepository.findById(leonardoId)
                .subscribe(actor->{
                    System.out.println("Actor " + actor);
                });
        WaitUntail.wait(2000);
    }

}
