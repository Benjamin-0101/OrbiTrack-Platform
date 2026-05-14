package cl.orbitrack.inventory.repositorio;

// Patron Repository — separa el acceso a datos de la logica de negocio
// Spring genera automaticamente las consultas SQL
import cl.orbitrack.inventory.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {

    // Busca productos con stock menor al limite — alerta de stock bajo
    List<Producto> findByStockLessThan(Integer limite);

    // Busca productos por nombre sin importar mayusculas
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}