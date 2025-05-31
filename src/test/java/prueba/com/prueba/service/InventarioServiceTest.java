package prueba.com.prueba.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import prueba.com.prueba.model.Inventario;
import prueba.com.prueba.repository.InventarioRepository;

@ExtendWith(MockitoExtension.class)
public class InventarioServiceTest {

    @InjectMocks
    private InventarioService inventarioService;

    @Mock
    private InventarioRepository inventarioRepository;

    @Test
    void obtenerStock_DeberiaDevolverUnPorducto_OK(){
        //Give
        Inventario inventarioTest = new Inventario();
        inventarioTest.setIdProducto(1L);
        inventarioTest.setStockActual(1);

        when(inventarioRepository.findById(1L)).thenReturn(Optional.of(inventarioTest));

        //When
        Optional<Inventario> result = inventarioService.obtenerStock(1L);

        //Then
        assertTrue(result.isPresent());
        verify(inventarioRepository).findById(1L);
    }

   @Test
    void actualizarStock_deberiaDevolverStockNuevo(){
        //Give
        //Parametros de la petición
        Long idProducto = 1L;
        Integer nuevoStock = 10;

        Inventario inventarioTest = new Inventario();
        inventarioTest.setIdProducto(1L);
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
