package prueba.com.prueba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba.com.prueba.model.Inventario;
import prueba.com.prueba.repository.InventarioRepository;

import java.util.Optional;

@Service
public class InventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;

    public Optional<Inventario> obtenerStock(Long idProducto) {
        return inventarioRepository.findById(idProducto);
    }

    public Optional<Inventario> actualizarStock(Long idProducto, Integer nuevoStock) {
        Optional<Inventario> inventarioOpt = inventarioRepository.findById(idProducto);
        if (inventarioOpt.isPresent()) {
            Inventario inventario = inventarioOpt.get();
            inventario.setStockActual(nuevoStock);
            inventarioRepository.save(inventario);
            return Optional.of(inventario);
        }
        return Optional.empty();
    }
}