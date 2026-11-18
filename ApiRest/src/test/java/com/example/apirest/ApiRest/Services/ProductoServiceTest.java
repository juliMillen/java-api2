package com.example.apirest.ApiRest.Services;

import com.example.apirest.ApiRest.Entities.Producto;
import com.example.apirest.ApiRest.Repositories.IProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @InjectMocks
    private ProductoService productoService;

    @Mock
    private IProductoRepository productoRepository;



    //Simulando un objeto en la base de datos

    @BeforeEach
    void setUp() {
        Producto nuevo = Producto.builder()
                .id(1L)
                .nombre("Galletas")
                .precio(2500.0)
                .build();
        Mockito.when(productoRepository.findByNameIgnoreCase("Galletas")).thenReturn(Optional.of(nuevo));
    }

    @Test
    @DisplayName("Prueba de obtención de información de un local enviando un nombre válido")
    void findByNameIgnoreCaseShouldFound(){
        String nombre = "Galletas";
        Producto p = productoService.findByNameIgnoreCase(nombre).get();

        assertEquals(nombre, p.getNombre());
        System.out.println("Producto: " + p);
    }
}