package cl.orbitrack.gateway.servicio;

// Servicio que se comunica con el microservicio de inventario
// Patron: BFF — adapta y simplifica las llamadas para el frontend
import cl.orbitrack.gateway.modelo.RespuestaDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class InventarioServicio {

    // Cliente HTTP — apunta al microservicio de inventario
    private final WebClient clienteInventario;

    // URL base del microservicio de inventario
    public InventarioServicio(WebClient.Builder builder) {
        this.clienteInventario = builder
            .baseUrl("http://localhost:8081")
            .build();
    }

    // Obtiene todos los productos del inventario
    public RespuestaDTO obtenerProductos() {
        try {
            Object datos = clienteInventario
                .get()
                .uri("/api/inventario/productos")
                .retrieve()
                .bodyToMono(Object.class)
                .block();
            return new RespuestaDTO("OK", "Productos obtenidos correctamente", datos);
        } catch (Exception e) {
            return new RespuestaDTO("ERROR", "No se pudo conectar con inventario: " + e.getMessage(), null);
        }
    }

    // Obtiene productos con stock bajo segun limite
    public RespuestaDTO obtenerStockBajo(Integer limite) {
        try {
            Object datos = clienteInventario
                .get()
                .uri("/api/inventario/productos/stock-bajo/" + limite)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
            return new RespuestaDTO("OK", "Productos con stock bajo obtenidos", datos);
        } catch (Exception e) {
            return new RespuestaDTO("ERROR", "Error al obtener stock bajo: " + e.getMessage(), null);
        }
    }
}