package cl.orbitrack.orders.controlador;

// Expone los endpoints REST del microservicio de pedidos
// Puerto: 8082 — configurado en application.properties
import cl.orbitrack.orders.modelo.Pedido;
import cl.orbitrack.orders.servicio.PedidoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoControlador {

    // Servicio inyectado — contiene la logica de negocio
    private final PedidoServicio servicio;

    public PedidoControlador(PedidoServicio servicio) {
        this.servicio = servicio;
    }

    // GET — retorna todos los pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerTodos() {
        return ResponseEntity.ok(servicio.obtenerTodos());
    }

    // GET — retorna un pedido por id
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicio.obtenerPorId(id));
    }

    // GET — retorna pedidos filtrados por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Pedido>> obtenerPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(servicio.obtenerPorEstado(estado));
    }

    // POST — crea un pedido nuevo usando Factory Method
    @PostMapping
    public ResponseEntity<Pedido> crear(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(
            servicio.crearPedido(
                pedido.getCliente(),
                pedido.getProducto(),
                pedido.getCantidad()
            )
        );
    }

    // PATCH — actualiza el estado de un pedido
    @PatchMapping("/{id}/estado/{nuevoEstado}")
    public ResponseEntity<Pedido> actualizarEstado(
            @PathVariable Long id,
            @PathVariable String nuevoEstado) {
        return ResponseEntity.ok(servicio.actualizarEstado(id, nuevoEstado));
    }

    // DELETE — elimina un pedido por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicio.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}