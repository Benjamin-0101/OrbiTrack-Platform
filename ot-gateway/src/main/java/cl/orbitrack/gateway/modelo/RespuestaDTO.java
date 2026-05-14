package cl.orbitrack.gateway.modelo;

// Objeto de transferencia de datos — estandariza respuestas del BFF
// El frontend siempre recibe el mismo formato sin importar el microservicio
public class RespuestaDTO {

    // Estado de la respuesta: OK o ERROR
    private String estado;

    // Mensaje descriptivo para el frontend
    private String mensaje;

    // Datos retornados por el microservicio
    private Object datos;

    // Constructor vacio
    public RespuestaDTO() {}

    // Constructor completo
    public RespuestaDTO(String estado, String mensaje, Object datos) {
        this.estado  = estado;
        this.mensaje = mensaje;
        this.datos   = datos;
    }

    // Getters
    public String getEstado()  { return estado; }
    public String getMensaje() { return mensaje; }
    public Object getDatos()   { return datos; }

    // Setters
    public void setEstado(String estado)   { this.estado = estado; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public void setDatos(Object datos)     { this.datos = datos; }
}