package com.minnthitoo.spring_webflux.controller.api;

import com.minnthitoo.spring_webflux.model.dto.MovieDto;
import com.minnthitoo.spring_webflux.model.dto.RestResponse;
import com.minnthitoo.spring_webflux.service.MovieService;
import com.minnthitoo.spring_webflux.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/movies")
public class MovieApiController {

    @Autowired private MovieService movieService;
    @Autowired private ReviewService reviewService;

    @GetMapping
    Mono<ResponseEntity<RestResponse>> getAllMovies(){
        return this.movieService.getAllMovies()
                .collectList()
                .flatMap(movies->successResponse(200, "Get All Movies", movies));
    }

    @GetMapping("/{movieId}")
    Mono<ResponseEntity<RestResponse>> getMovieById(@PathVariable String movieId){
        return this.movieService.getMovieById(movieId)
                .flatMap(movieDto -> successResponse(200, "Movie found", movieDto))
                .onErrorResume(error->errorResponse(400, error.getMessage(), error.getLocalizedMessage()));
    }

    @PostMapping
    Mono<ResponseEntity<RestResponse>> saveMovie(@Valid @RequestBody MovieDto dto){
        return this.movieService.saveMovie(dto)
                .flatMap(movieDto->successResponse(201, "Movie saved successfully.", movieDto));
    }

    @PutMapping("/{movieId}")
    Mono<ResponseEntity<RestResponse>> updateMovieById(@PathVariable String movieId, @Valid @RequestBody MovieDto dto){
        return  this.movieService.updateMovieById(movieId, dto)
                .flatMap(movieDto -> successResponse(200, "Movie updated successfully.", movieDto))
                .onErrorResume(error->errorResponse(404, error.getMessage(), error.getLocalizedMessage()));
    }

    @DeleteMapping("/{movieId}")
    Mono<ResponseEntity<RestResponse>> deleteMovieById(@PathVariable String movieId){
        return  this.movieService.deleteMovieById(movieId)
                .flatMap(movieDto -> successResponse(200, "Movie deleted successfully.", movieDto))
                .onErrorResume(error->errorResponse(404, error.getMessage(), error.getLocalizedMessage()));
    }

    @GetMapping("/{movieId}/reviews")
    Mono<ResponseEntity<RestResponse>> getAllReviewsOfMovie(@PathVariable String movieId){
        return  this.reviewService.getAllReviewByMovie(movieId)
                .collectList()
                .flatMap(movieDto -> successResponse(200, "Movie deleted successfully.", movieDto))
                .onErrorResume(error->errorResponse(404, error.getMessage(), error.getLocalizedMessage()));
    }

    Mono<ResponseEntity<RestResponse>> successResponse(int statusCode, String message, Object data){
        RestResponse restResponse = new RestResponse();
        restResponse.setMessage(message);
        restResponse.setData(data);
        return Mono.just(ResponseEntity.status(statusCode).body(restResponse));
    }

    Mono<ResponseEntity<RestResponse>> errorResponse(int statusCode, String message, Object error){
        RestResponse restResponse = new RestResponse();
        restResponse.setMessage(message);
        restResponse.setError(error);
        return Mono.just(ResponseEntity.status(statusCode).body(restResponse));
    }

}
