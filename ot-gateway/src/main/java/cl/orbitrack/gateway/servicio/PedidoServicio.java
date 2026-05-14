package cl.orbitrack.gateway.servicio;

// Servicio que se comunica con el microservicio de pedidos
// Patron: BFF — adapta y simplifica las llamadas para el frontend
import cl.orbitrack.gateway.modelo.RespuestaDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PedidoServicio {

    // Cliente HTTP — apunta al microservicio de pedidos
    private final WebClient clientePedidos;

    // URL base del microservicio de pedidos
    public PedidoServicio(WebClient.Builder builder) {
        this.clientePedidos = builder
            .baseUrl("http://localhost:8082")
            .build();
    }

    // Obtiene todos los pedidos
    public RespuestaDTO obtenerPedidos() {
        try {
            Object datos = clientePedidos
                .get()
                .uri("/api/pedidos")
                .retrieve()
                .bodyToMono(Object.class)
                .block();
            return new RespuestaDTO("OK", "Pedidos obtenidos correctamente", datos);
        } catch (Exception e) {
            return new RespuestaDTO("ERROR", "No se pudo conectar con pedidos: " + e.getMessage(), null);
        }
    }

    // Obtiene pedidos filtrados por estado
    public RespuestaDTO obtenerPorEstado(String estado) {
        try {
            Object datos = clientePedidos
                .get()
                .uri("/api/pedidos/estado/" + estado)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
            return new RespuestaDTO("OK", "Pedidos por estado obtenidos", datos);
        } catch (Exception e) {
            return new RespuestaDTO("ERROR", "Error al obtener pedidos: " + e.getMessage(), null);
        }
    }
}