package com.minnthitoo.spring_webflux.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private String id;

    @NotBlank
    private String movieId;

    @NotBlank
    private String review;

    @NotBlank
    private Integer rating;

}
