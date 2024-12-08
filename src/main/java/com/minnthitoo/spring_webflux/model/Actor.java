package com.minnthitoo.spring_webflux.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "actors")
public class Actor {

    @Id
    String id;

    String firstName;

    String lastName;

}
