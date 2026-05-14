package cl.orbitrack.gateway;

// Tests unitarios del BFF Gateway
// Rubrica: buenas practicas + pruebas unitarias
import cl.orbitrack.gateway.modelo.RespuestaDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GatewayServicioTest {

    // Test respuesta OK tiene estado correcto
    @Test
    void respuestaDTO_estadoOK_debeSerOK() {
        RespuestaDTO respuesta = new RespuestaDTO("OK", "Productos obtenidos correctamente", null);
        assertEquals("OK", respuesta.getEstado());
    }

    // Test respuesta ERROR tiene estado correcto
    @Test
    void respuestaDTO_estadoERROR_debeSerERROR() {
        RespuestaDTO respuesta = new RespuestaDTO("ERROR", "No se pudo conectar", null);
        assertEquals("ERROR", respuesta.getEstado());
    }

    // Test mensaje se almacena correctamente
    @Test
    void respuestaDTO_mensaje_debeAlmacenarseCorrectamente() {
        RespuestaDTO respuesta = new RespuestaDTO("OK", "Pedidos obtenidos", null);
        assertEquals("Pedidos obtenidos", respuesta.getMensaje());
    }

    // Test datos nulos cuando hay error
    @Test
    void respuestaDTO_datosNulos_cuandoHayError() {
        RespuestaDTO respuesta = new RespuestaDTO("ERROR", "Error de conexion", null);
        assertNull(respuesta.getDatos());
    }
}