package prueba.com.prueba.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PRODUCTOS")
public class Inventario {
    @Id
    @Column(name = "ID")
    private Long idProducto;

    @Column(name = "STOCK_ACTUAL")
    private Integer stockActual;

    // Getters y setters
    public Long getIdProducto() { return idProducto; }
    public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }
    public Integer getStockActual() { return stockActual; }
    public void setStockActual(Integer stockActual) { this.stockActual = stockActual; }
}
