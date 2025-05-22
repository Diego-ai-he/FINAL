package prueba.com.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prueba.com.prueba.model.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}