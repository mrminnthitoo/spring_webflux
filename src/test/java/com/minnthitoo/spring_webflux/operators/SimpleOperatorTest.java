package com.minnthitoo.spring_webflux.operators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimpleOperatorTest {

    @Test
    public void test(){
        Assertions.assertEquals(1, 1);
    }

}
