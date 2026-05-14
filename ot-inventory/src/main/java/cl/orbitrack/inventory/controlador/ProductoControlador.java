package cl.orbitrack.inventory.controlador;

// Expone los endpoints REST del microservicio
// Puerto: 8081 — configurado en application.properties
import cl.orbitrack.inventory.modelo.Producto;
import cl.orbitrack.inventory.servicio.ProductoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@CrossOrigin(origins = "*")
public class ProductoControlador {

    // Servicio inyectado — contiene la logica de negocio
    private final ProductoServicio servicio;

    public ProductoControlador(ProductoServicio servicio) {
        this.servicio = servicio;
    }

    // GET — retorna todos los productos
    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> obtenerTodos() {
        return ResponseEntity.ok(servicio.obtenerTodos());
    }

    // GET — retorna un producto por id
    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicio.obtenerPorId(id));
    }

    // GET — retorna productos con stock bajo segun limite
    @GetMapping("/productos/stock-bajo/{limite}")
    public ResponseEntity<List<Producto>> stockBajo(@PathVariable Integer limite) {
        return ResponseEntity.ok(servicio.obtenerStockBajo(limite));
    }

    // POST — crea un producto nuevo usando Factory Method
    @PostMapping("/productos")
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        return ResponseEntity.ok(
            servicio.crearProducto(
                producto.getNombre(),
                producto.getStock(),
                producto.getPrecio()
            )
        );
    }

    // PATCH — actualiza solo el stock de un producto
    @PatchMapping("/productos/{id}/stock/{nuevoStock}")
    public ResponseEntity<Producto> actualizarStock(
            @PathVariable Long id,
            @PathVariable Integer nuevoStock) {
        return ResponseEntity.ok(servicio.actualizarStock(id, nuevoStock));
    }

    // DELETE — elimina un producto por id
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicio.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}