package cl.orbitrack.gateway.controlador;

// Punto unico de entrada para el frontend
// BFF — unifica inventario y pedidos en una sola API
import cl.orbitrack.gateway.modelo.RespuestaDTO;
import cl.orbitrack.gateway.servicio.InventarioServicio;
import cl.orbitrack.gateway.servicio.PedidoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gateway")
@CrossOrigin(origins = "*")
public class GatewayControlador {

    // Servicios inyectados — uno por microservicio
    private final InventarioServicio inventarioServicio;
    private final PedidoServicio pedidoServicio;

    public GatewayControlador(
            InventarioServicio inventarioServicio,
            PedidoServicio pedidoServicio) {
        this.inventarioServicio = inventarioServicio;
        this.pedidoServicio     = pedidoServicio;
    }

    // GET — retorna todos los productos del inventario
    @GetMapping("/inventario/productos")
    public ResponseEntity<RespuestaDTO> obtenerProductos() {
        return ResponseEntity.ok(inventarioServicio.obtenerProductos());
    }

    // GET — retorna productos con stock bajo
    @GetMapping("/inventario/stock-bajo/{limite}")
    public ResponseEntity<RespuestaDTO> obtenerStockBajo(@PathVariable Integer limite) {
        return ResponseEntity.ok(inventarioServicio.obtenerStockBajo(limite));
    }

    // GET — retorna todos los pedidos
    @GetMapping("/pedidos")
    public ResponseEntity<RespuestaDTO> obtenerPedidos() {
        return ResponseEntity.ok(pedidoServicio.obtenerPedidos());
    }

    // GET — retorna pedidos filtrados por estado
    @GetMapping("/pedidos/estado/{estado}")
    public ResponseEntity<RespuestaDTO> obtenerPedidosPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(pedidoServicio.obtenerPorEstado(estado));
    }
}