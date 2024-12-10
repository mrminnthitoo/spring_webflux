package com.minnthitoo.spring_webflux.service;

import com.minnthitoo.spring_webflux.WaitUntail;
import com.minnthitoo.spring_webflux.model.dto.ActorDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Rollback(value = false)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestActorService {

    @Autowired private ActorService actorService;

    @Test
    public void testGetAllActors(){
        this.actorService.getAllActors()
                .doOnNext(actor->{
                    log.info("Actor {}", actor);
                })
                .collectList()
                .subscribe(data->{
                    assertFalse(data.isEmpty());
                });
        WaitUntail.wait(2000);
    }

    @Test
    public void testGetActorById(){

        String actorId = "6753206ace6cdf479a81e542";

        this.actorService.getActorById(actorId)
                .subscribe(actor->{
                    System.out.println("Actor " + actor);
                });
        WaitUntail.wait(2000);

    }

    @Test
    public void testSaveActor(){
        ActorDto actorDto = new ActorDto();

        actorDto.setFirstName("Actor");
        actorDto.setLastName("Nine");

        this.actorService.saveActor(actorDto)
                .subscribe(data->{
                    assertNotNull(data.getId());
                });
        WaitUntail.wait(2000);

    }

    @Test
    public void testUpdateActorById(){
        ActorDto actorDto = new ActorDto();
        actorDto.setFirstName("Actor");
        actorDto.setLastName("Update");

        this.actorService.updateActorById("6753206ace6cdf479a81e542", actorDto)
                .subscribe(actor->{
                    System.out.println("Actor " + actor);
                });
    }

    @Test
    public void testDeleteActorById(){
        this.actorService.deleteActorById("6753206ace6cdf479a81e542")
                .subscribe(actor->{
                    log.info("Deleted Actor {}", actor);
                    assertNotNull(actor.getId());
                });
        WaitUntail.wait(2000);
    }

}
