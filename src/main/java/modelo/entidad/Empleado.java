package modelo.entidad;

import java.io.Serializable;

public class Empleado implements Serializable {
    private int idEmpleado;
    private String nombre;
    private String apellido;
    private String nombreUsuario;
    private String clave;
    private String correo;


    public Empleado() {
    }

    public Empleado(String nombre, String apellido, String nombreUsuario, String clave, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.correo = correo;
    }

    public Empleado(int idEmpleado, String nombre, String apellido, String nombreUsuario, String clave, String correo) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.correo = correo;
    }


    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "idEmpleado=" + idEmpleado +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", clave='" + clave + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}
