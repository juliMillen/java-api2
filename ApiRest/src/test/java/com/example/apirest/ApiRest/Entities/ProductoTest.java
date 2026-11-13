package com.example.apirest.ApiRest.Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {
    @Test
    void test(){
        Producto p = new Producto("Galletas",2500.00);
        Producto p2 = new Producto("Gaseosa",3500.00);
    }
}