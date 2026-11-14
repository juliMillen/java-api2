package com.example.apirest.ApiRest.Entities;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {
    Producto p;
    Producto p2;

    @BeforeAll
    static void antes(){
        System.out.println(":: beforeAll ::");
    }

    @AfterAll
    static void despues(){
        System.out.println(":: afterAll ::");
    }

    @BeforeEach
    void iniciar(){
        p = new Producto("Galletas",2500.00);
        p2 = new Producto("Gaseosa",3500.00);

    }

    @AfterEach
    void terminar(){
        System.out.println("::afterEach::");
    }

    @Test
    void testUno(){

        //Prueba Assertions
        Assertions.assertTrue(p.getPrecio() > 1000, "El producto tiene un precio mayor que 1000");
        Assertions.assertTrue(p2.getPrecio()>3000, "El producto tiene un precio mayor que 3000");
        Assertions.assertNotNull(p.getNombre(), "El producto no es null");
        Assertions.assertEquals("Galletas", p.getNombre());
    }

    @Test
    void testDos(){
        System.out.println("::Este es un segundo test ::");
    }

}