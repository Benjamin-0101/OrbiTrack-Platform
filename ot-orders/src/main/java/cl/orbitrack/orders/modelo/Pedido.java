package cl.orbitrack.orders.modelo;

// Representa un pedido en el sistema
// Tabla: pedidos en la base de datos
import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedido {

    // Identificador unico autoincremental
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre del cliente que realiza el pedido
    @Column(nullable = false)
    private String cliente;

    // Producto solicitado
    @Column(nullable = false)
    private String producto;

    // Cantidad de unidades pedidas
    @Column(nullable = false)
    private Integer cantidad;

    // Estado del pedido: PENDIENTE, APROBADO, ENVIADO, CANCELADO
    @Column(nullable = false)
    private String estado;

    // Constructor vacio requerido por JPA
    public Pedido() {}

    // Constructor para crear pedidos nuevos
    public Pedido(String cliente, String producto, Integer cantidad, String estado) {
        this.cliente  = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.estado   = estado;
    }

    // Getters — lectura de datos
    public Long getId()        { return id; }
    public String getCliente() { return cliente; }
    public String getProducto(){ return producto; }
    public Integer getCantidad(){ return cantidad; }
    public String getEstado()  { return estado; }

    // Setters — modificacion de datos
    public void setId(Long id)            { this.id = id; }
    public void setCliente(String c)      { this.cliente = c; }
    public void setProducto(String p)     { this.producto = p; }
    public void setCantidad(Integer c)    { this.cantidad = c; }
    public void setEstado(String e)       { this.estado = e; }
}