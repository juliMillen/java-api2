package com.example.apirest.ApiRest.Entities;

import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {
    Producto p;
    Producto p2;

    @BeforeAll
    static void antes(){
        System.out.println(":: beforeAll ::");
    }

    @Test
    void despues(){
        System.out.println(":: afterAll ::");
        Producto p3 = new Producto("Mermelada", 2300.55);
        assertAll(
                () -> assertEquals(2300.55, p3.getPrecio()),
                () -> assertEquals(3,3),
                () -> assertEquals("Mermelada",p3.getNombre()),
                () -> assertTrue(true)
        );
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

    //PARA PRUEBAS DE RENDIMIENTO Y VELOCIDAD DE METODOS
    @Test
    void testTimeout(){
        assertTimeout(
                Duration.ofMillis(200),
                () -> Thread.sleep(100)
        );
    }

    @Test
    void testArray(){
        int[] arrayUno = new int[] {1, 4, 9, 16 , 26};
        int[] arrayDos = new int[] {1, 4, 9, 16, 26};

        assertArrayEquals(arrayUno,arrayDos);
    }
}