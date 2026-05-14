package cl.orbitrack.orders.servicio;

// Logica de negocio de pedidos
// Patron Factory Method — crea pedidos de forma controlada
import cl.orbitrack.orders.modelo.Pedido;
import cl.orbitrack.orders.repositorio.PedidoRepositorio;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PedidoServicio {

    // Repositorio inyectado — acceso a base de datos
    private final PedidoRepositorio repositorio;

    // Inyeccion por constructor — buena practica recomendada
    public PedidoServicio(PedidoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    // Factory Method — unico punto de creacion de pedidos
    public Pedido crearPedido(String cliente, String producto, Integer cantidad) {
        Pedido pedido = new Pedido(cliente, producto, cantidad, "PENDIENTE");
        return repositorio.save(pedido);
    }

    // Retorna todos los pedidos del sistema
    public List<Pedido> obtenerTodos() {
        return repositorio.findAll();
    }

    // Busca un pedido por id — lanza error si no existe
    public Pedido obtenerPorId(Long id) {
        return repositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado, id: " + id));
    }

    // Retorna pedidos filtrados por estado
    public List<Pedido> obtenerPorEstado(String estado) {
        return repositorio.findByEstado(estado);
    }

    // Actualiza el estado de un pedido existente
    public Pedido actualizarEstado(Long id, String nuevoEstado) {
        Pedido pedido = obtenerPorId(id);
        pedido.setEstado(nuevoEstado);
        return repositorio.save(pedido);
    }

    // Elimina un pedido por id
    public void eliminarPedido(Long id) {
        repositorio.deleteById(id);
    }
}