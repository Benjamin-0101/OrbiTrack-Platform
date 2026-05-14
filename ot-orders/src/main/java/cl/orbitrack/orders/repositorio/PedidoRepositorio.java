package cl.orbitrack.orders.repositorio;

// Patron Repository — separa acceso a datos de la logica de negocio
import cl.orbitrack.orders.modelo.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {

    // Busca pedidos por estado — PENDIENTE, APROBADO, etc
    List<Pedido> findByEstado(String estado);

    // Busca pedidos de un cliente especifico
    List<Pedido> findByCliente(String cliente);
}