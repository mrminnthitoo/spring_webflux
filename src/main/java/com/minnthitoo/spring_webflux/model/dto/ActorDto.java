package com.minnthitoo.spring_webflux.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorDto {

    private String id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

}
