package cl.orbitrack.inventory.modelo;

// Representa un producto en el inventario
// Tabla: productos en la base de datos
import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {

    // Identificador unico autoincremental
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre del producto — obligatorio
    @Column(nullable = false)
    private String nombre;

    // Cantidad disponible en bodega
    @Column(nullable = false)
    private Integer stock;

    // Precio unitario en pesos chilenos
    @Column(nullable = false)
    private Double precio;

    // Constructor vacio requerido por JPA
    public Producto() {}

    // Constructor para crear productos nuevos
    public Producto(String nombre, Integer stock, Double precio) {
        this.nombre = nombre;
        this.stock  = stock;
        this.precio = precio;
    }

    // Getters — lectura de datos
    public Long getId()       { return id; }
    public String getNombre() { return nombre; }
    public Integer getStock() { return stock; }
    public Double getPrecio() { return precio; }

    // Setters — modificacion de datos
    public void setId(Long id)          { this.id = id; }
    public void setNombre(String n)     { this.nombre = n; }
    public void setStock(Integer s)     { this.stock = s; }
    public void setPrecio(Double p)     { this.precio = p; }
}