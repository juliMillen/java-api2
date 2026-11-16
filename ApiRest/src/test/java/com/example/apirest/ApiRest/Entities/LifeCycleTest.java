package com.example.apirest.ApiRest.Entities;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

//Podemos ordenar por orden alfabetico
//MethodOrderer.Alphanumeric.class
//o por un orden que establezcamos mediante la annotation Order()
//MethodOrderer.OrderAnnotation.class
//tambien podemos hacerlo de manera random
@TestMethodOrder(MethodOrderer.Random.class)
public class LifeCycleTest {

    @Test
    void testMetodo(){
        System.out.println("Test de método");
    }

    @Test
    void testEjemplo(){
        System.out.println("Test de ejemplo");
    }

    @Test
    void testCaso(){
        System.out.println("Test de caso");
    }
}
