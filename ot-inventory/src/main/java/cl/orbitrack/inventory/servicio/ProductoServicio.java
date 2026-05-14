package cl.orbitrack.inventory.servicio;

// Logica de negocio del inventario
// Patron Factory Method — crea productos de forma controlada
import cl.orbitrack.inventory.modelo.Producto;
import cl.orbitrack.inventory.repositorio.ProductoRepositorio;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoServicio {

    // Repositorio inyectado — acceso a base de datos
    private final ProductoRepositorio repositorio;

    // Inyeccion por constructor — buena practica recomendada
    public ProductoServicio(ProductoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    // Factory Method — unico punto de creacion de productos
    public Producto crearProducto(String nombre, Integer stock, Double precio) {
        Producto producto = new Producto(nombre, stock, precio);
        return repositorio.save(producto);
    }

    // Retorna todos los productos del inventario
    public List<Producto> obtenerTodos() {
        return repositorio.findAll();
    }

    // Busca un producto por id — lanza error si no existe
    public Producto obtenerPorId(Long id) {
        return repositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado, id: " + id));
    }

    // Retorna productos con stock bajo segun el limite indicado
    public List<Producto> obtenerStockBajo(Integer limite) {
        return repositorio.findByStockLessThan(limite);
    }

    // Actualiza el stock de un producto existente
    public Producto actualizarStock(Long id, Integer nuevoStock) {
        Producto producto = obtenerPorId(id);
        producto.setStock(nuevoStock);
        return repositorio.save(producto);
    }

    // Elimina un producto por id
    public void eliminarProducto(Long id) {
        repositorio.deleteById(id);
    }
}