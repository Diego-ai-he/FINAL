package prueba.com.prueba.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import prueba.com.prueba.model.Inventario;
import prueba.com.prueba.repository.InventarioRepository;

public class InventarioService2 {

    @InjectMocks
    private InventarioService inventarioService;

    @Mock
    private InventarioRepository inventarioRepository;

    @Test
    void actualizarStock_deberiaDevolverStockNuevo(){
        //Give
        Long idProducto = 1L;
        Integer nuevoStock = 10;

        Inventario inventarioTest = new Inventario();
        inventarioTest.setIdProducto(idProducto);
        inventarioTest.setStockActual(5);
        
        when(inventarioRepository.findById(idProducto)).thenReturn(Optional.of(inventarioTest));

        //When
        Optional<Inventario> result = inventarioService.actualizarStock(idProducto, nuevoStock);

        //Then
        assertTrue(result.isPresent());
        assertEquals(nuevoStock, result.get().getStockActual());
        verify(inventarioRepository).save(inventarioTest);
    }



}
