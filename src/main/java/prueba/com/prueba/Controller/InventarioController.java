package prueba.com.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prueba.com.prueba.model.Inventario;
import prueba.com.prueba.service.InventarioService;

import java.util.Optional;

@RestController
@RequestMapping("/inventario")
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @GetMapping("/{idProducto}")
    public ResponseEntity<Inventario> obtenerStock(@PathVariable Long idProducto) {
        Optional<Inventario> inventario = inventarioService.obtenerStock(idProducto);
        return inventario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{idProducto}")
    public ResponseEntity<Inventario> actualizarStock(
            @PathVariable Long idProducto,
            @RequestParam Integer stock) {
        Optional<Inventario> inventario = inventarioService.actualizarStock(idProducto, stock);
        return inventario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}