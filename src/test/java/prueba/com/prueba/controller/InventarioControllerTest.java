import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import prueba.com.prueba.controller.InventarioController;
import prueba.com.prueba.model.Inventario;
import prueba.com.prueba.service.InventarioService;

import java.util.Optional;

@WebMvcTest(InventarioController.class)
public class InventarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventarioService inventarioService;

    @Test
    void obtenerStock_ProductoExistente() throws Exception {
        Inventario inventario = new Inventario();
        inventario.setIdProducto(1L);
        inventario.setStockActual(100);

        when(inventarioService.obtenerStock(1L)).thenReturn(Optional.of(inventario));

        mockMvc.perform(get("/inventario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProducto").value(1))
                .andExpect(jsonPath("$.stockActual").value(100));
    }

    @Test
    void obtenerStock_ProductoNoExistente() throws Exception {
        when(inventarioService.obtenerStock(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/inventario/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void actualizarStock_ProductoExistente() throws Exception {
        Inventario inventario = new Inventario();
        inventario.setIdProducto(1L);
        inventario.setStockActual(150);

        when(inventarioService.actualizarStock(1L, 150)).thenReturn(Optional.of(inventario));

        mockMvc.perform(put("/inventario/1")
                .param("stock", "150"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProducto").value(1))
                .andExpect(jsonPath("$.stockActual").value(150));
    }

    @Test
    void actualizarStock_ProductoNoExistente() throws Exception {
        when(inventarioService.actualizarStock(999L, 150)).thenReturn(Optional.empty());

        mockMvc.perform(put("/inventario/999")
                .param("stock", "150"))
                .andExpect(status().isNotFound());
    }

    @Test
    void obtenerStock_IdProductoInvalido() throws Exception {
        mockMvc.perform(get("/inventario/abc")) // Invalid ID format
                .andExpect(status().isBadRequest());
    }

    @Test
    void actualizarStock_IdProductoInvalido() throws Exception {
        mockMvc.perform(put("/inventario/abc")
                .param("stock", "150")) // Invalid ID format
                .andExpect(status().isBadRequest());
    }

    @Test
    void actualizarStock_StockInvalido() throws Exception {
        mockMvc.perform(put("/inventario/1")
                .param("stock", "-10")) // Invalid stock value
                .andExpect(status().isBadRequest());
    }

    // Nuevos tests adicionales

    @Test
    void actualizarStock_StockNoEsNumero() throws Exception {
        mockMvc.perform(put("/inventario/1")
                .param("stock", "abc")) // Stock no numérico
                .andExpect(status().isBadRequest());
    }

    @Test
    void actualizarStock_StockFaltante() throws Exception {
        mockMvc.perform(put("/inventario/1")) // Falta parámetro stock
                .andExpect(status().isBadRequest());
    }

    @Test
    void obtenerStock_SinId() throws Exception {
        mockMvc.perform(get("/inventario/")) // Falta idProducto
                .andExpect(status().isNotFound());
    }
}

        when(inventarioService.obtenerStock(1L)).thenReturn(Optional.of(inventario));

        mockMvc.perform(get("/inventario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProducto").value(1))
                .andExpect(jsonPath("$.stock").value(100));
    }

    @Test
    void obtenerStock_ProductoNoExistente() throws Exception {
        when(inventarioService.obtenerStock(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/inventario/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void actualizarStock_ProductoExistente() throws Exception {
        Inventario inventario = new Inventario();
        inventario.setIdProducto(1L);
        inventario.setStockActual(150);

        when(inventarioService.actualizarStock(1L, 150)).thenReturn(Optional.of(inventario));

        mockMvc.perform(put("/inventario/1")
                .param("stock", "150"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProducto").value(1))
                .andExpect(jsonPath("$.stock").value(150));
    }

    @Test
    void actualizarStock_ProductoNoExistente() throws Exception {
        when(inventarioService.actualizarStock(999L, 150)).thenReturn(Optional.empty());

        mockMvc.perform(put("/inventario/999")
                .param("stock", "150"))
                .andExpect(status().isNotFound());
    }

    @Test
    void obtenerStock_IdProductoInvalido() throws Exception {
        mockMvc.perform(get("/inventario/abc")) // Invalid ID format
                .andExpect(status().isBadRequest());
    }

    @Test
    void actualizarStock_IdProductoInvalido() throws Exception {
        mockMvc.perform(put("/inventario/abc")
                .param("stock", "150")) // Invalid ID format
                .andExpect(status().isBadRequest());
    }

    @Test
    void actualizarStock_StockInvalido() throws Exception {
        mockMvc.perform(put("/inventario/1")
                .param("stock", "-10")) // Invalid stock value
                .andExpect(status().isBadRequest());
    }
}