package cl.orbitrack.orders;

// Tests unitarios del servicio de pedidos
// Rubrica: buenas practicas + pruebas unitarias
import cl.orbitrack.orders.modelo.Pedido;
import cl.orbitrack.orders.repositorio.PedidoRepositorio;
import cl.orbitrack.orders.servicio.PedidoServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoServicioTest {

    // Repositorio simulado — no toca la base de datos real
    @Mock
    private PedidoRepositorio repositorio;

    // Servicio a testear con el repositorio simulado
    @InjectMocks
    private PedidoServicio servicio;

    // Inicializa los mocks antes de cada test
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test Factory Method — verifica creacion correcta de pedido
    @Test
    void crearPedido_debeCrearConEstadoPendiente() {
        Pedido mock = new Pedido("Juan Perez", "Teclado", 2, "PENDIENTE");
        when(repositorio.save(any(Pedido.class))).thenReturn(mock);

        Pedido resultado = servicio.crearPedido("Juan Perez", "Teclado", 2);

        assertNotNull(resultado);
        assertEquals("PENDIENTE", resultado.getEstado());
        assertEquals("Juan Perez", resultado.getCliente());
        verify(repositorio, times(1)).save(any(Pedido.class));
    }

    // Test Repository Pattern — verifica busqueda por id
    @Test
    void obtenerPorId_debeRetornarPedidoCorrecto() {
        Pedido mock = new Pedido("Maria Lopez", "Monitor", 1, "APROBADO");
        when(repositorio.findById(1L)).thenReturn(Optional.of(mock));

        Pedido resultado = servicio.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals("Maria Lopez", resultado.getCliente());
    }

    // Test filtro por estado — verifica consulta correcta
    @Test
    void obtenerPorEstado_debeRetornarPedidosFiltrados() {
        List<Pedido> mockLista = List.of(
            new Pedido("Carlos", "Mouse", 3, "PENDIENTE"),
            new Pedido("Ana", "Webcam", 1, "PENDIENTE")
        );
        when(repositorio.findByEstado("PENDIENTE")).thenReturn(mockLista);

        List<Pedido> resultado = servicio.obtenerPorEstado("PENDIENTE");

        assertEquals(2, resultado.size());
        verify(repositorio, times(1)).findByEstado("PENDIENTE");
    }

    // Test actualizacion de estado — verifica cambio correcto
    @Test
    void actualizarEstado_debeActualizarCorrectamente() {
        Pedido mock = new Pedido("Luis", "Silla", 1, "PENDIENTE");
        when(repositorio.findById(1L)).thenReturn(Optional.of(mock));
        when(repositorio.save(any(Pedido.class))).thenReturn(mock);

        Pedido resultado = servicio.actualizarEstado(1L, "APROBADO");

        assertEquals("APROBADO", resultado.getEstado());
        verify(repositorio, times(1)).save(any(Pedido.class));
    }
}