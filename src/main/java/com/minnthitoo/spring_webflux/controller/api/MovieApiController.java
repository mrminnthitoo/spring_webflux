package com.minnthitoo.spring_webflux.controller.api;

import com.minnthitoo.spring_webflux.model.dto.MovieDto;
import com.minnthitoo.spring_webflux.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/movies")
public class MovieApiController {

    @Autowired private MovieService movieService;

    @GetMapping
    Flux<MovieDto> getAllMovies(){
        return this.movieService.getAllMovies();
    }

//    @PostMapping
//    Mono<MovieDto> saveMovie(@Valid @RequestBody MovieDto movieDto, BindingResult bindingResult){
//        if (!bindingResult.hasErrors()){
//            return this.movieService.saveMovie(movieDto);
//        }else{
//
//        }
//    }

}
