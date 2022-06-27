package modelo.entidad;

import java.io.Serializable;

public class Ticket implements Serializable {
    private int idTicket;
    private String titulo;
    private String fechaCreacion;
    private String estado;
    private String prioridad;
    private String descripcion;
    private String solucion;
    private String fechaCierre;
    private Empleado empleadoEncargado;
    private Emisor emisor;

    public Ticket() {
    }
    public Ticket(int idTicket, String titulo, String fechaCreacion, String estado, String prioridad, String descripcion, String solucion, String fechaCierre, Empleado empleadoEncargado, Emisor emisor) {
        this.idTicket = idTicket;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.prioridad = prioridad;
        this.descripcion = descripcion;
        this.solucion = solucion;
        this.fechaCierre = fechaCierre;
        this.empleadoEncargado = empleadoEncargado;
        this.emisor = emisor;
    }

    public Ticket(String titulo, String fechaCreacion, String estado, String prioridad, String descripcion, String solucion, String fechaCierre, Empleado empleadoEncargado, Emisor emisor) {
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.prioridad = prioridad;
        this.descripcion = descripcion;
        this.solucion = solucion;
        this.fechaCierre = fechaCierre;
        this.empleadoEncargado = empleadoEncargado;
        this.emisor = emisor;
    }

    public Ticket(int idTicket, String estado, String prioridad, String solucion, String fechaCierre) {
        this.idTicket = idTicket;
        this.estado = estado;
        this.prioridad = prioridad;
        this.solucion = solucion;
        this.fechaCierre = fechaCierre;
    }


    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Empleado getEmpleadoEncargado() {
        return empleadoEncargado;
    }

    public void setEmpleadoEncargado(Empleado empleadoEncargado) {
        this.empleadoEncargado = empleadoEncargado;
    }

    public Emisor getEmisor() {
        return emisor;
    }

    public void setEmisor(Emisor emisor) {
        this.emisor = emisor;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idTicket=" + idTicket +
                ", titulo='" + titulo + '\'' +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                ", estado='" + estado + '\'' +
                ", prioridad='" + prioridad + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", solucion='" + solucion + '\'' +
                ", fechaCierre='" + fechaCierre + '\'' +
                ", empleadoEncargado=" + empleadoEncargado +
                ", emisor=" + emisor +
                '}';
    }
}
