package cl.orbitrack.inventory;

// Tests unitarios del servicio de inventario
// Rubrica: buenas practicas + pruebas unitarias
import cl.orbitrack.inventory.modelo.Producto;
import cl.orbitrack.inventory.repositorio.ProductoRepositorio;
import cl.orbitrack.inventory.servicio.ProductoServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoServicioTest {

    // Repositorio simulado — no toca la base de datos real
    @Mock
    private ProductoRepositorio repositorio;

    // Servicio a testear con el repositorio simulado
    @InjectMocks
    private ProductoServicio servicio;

    // Inicializa los mocks antes de cada test
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test Factory Method — verifica creacion correcta de producto
    @Test
    void crearProducto_debeRetornarProductoGuardado() {
        Producto mock = new Producto("Teclado Mecanico", 50, 29990.0);
        when(repositorio.save(any(Producto.class))).thenReturn(mock);

        Producto resultado = servicio.crearProducto("Teclado Mecanico", 50, 29990.0);

        assertNotNull(resultado);
        assertEquals("Teclado Mecanico", resultado.getNombre());
        assertEquals(50, resultado.getStock());
        verify(repositorio, times(1)).save(any(Producto.class));
    }

    // Test Repository Pattern — verifica busqueda por id
    @Test
    void obtenerPorId_debeRetornarProductoCorrecto() {
        Producto mock = new Producto("Monitor 4K", 10, 199990.0);
        when(repositorio.findById(1L)).thenReturn(Optional.of(mock));

        Producto resultado = servicio.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals("Monitor 4K", resultado.getNombre());
    }

    // Test alerta stock bajo — verifica filtro por limite
    @Test
    void obtenerStockBajo_debeRetornarProductosConStockBajo() {
        List<Producto> mockLista = List.of(
            new Producto("Cable USB", 2, 990.0),
            new Producto("Mouse Inalambrico", 3, 14990.0)
        );
        when(repositorio.findByStockLessThan(5)).thenReturn(mockLista);

        List<Producto> resultado = servicio.obtenerStockBajo(5);

        assertEquals(2, resultado.size());
        verify(repositorio, times(1)).findByStockLessThan(5);
    }

    // Test actualizacion de stock — verifica cambio correcto
    @Test
    void actualizarStock_debeActualizarStockCorrectamente() {
        Producto mock = new Producto("Auriculares BT", 5, 39990.0);
        when(repositorio.findById(1L)).thenReturn(Optional.of(mock));
        when(repositorio.save(any(Producto.class))).thenReturn(mock);

        Producto resultado = servicio.actualizarStock(1L, 20);

        assertEquals(20, resultado.getStock());
        verify(repositorio, times(1)).save(any(Producto.class));
    }
}