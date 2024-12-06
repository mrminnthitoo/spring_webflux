package com.minnthitoo.spring_webflux.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collation = "reviews")
public class Review {

    @Id
    private String id;

    @DBRef
    private Movie movie;

    private String review;

    Integer rating;

}
