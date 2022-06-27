package modelo.entidad;

import java.io.Serializable;

public class CambioResponsables implements Serializable {
    private int idCambio;
    private int idTicket;
    private Empleado antiguoResponsable;
    private Empleado nuevoResponsable;
    private String razonCambio;

    public CambioResponsables() {
    }

    public CambioResponsables(int idCambio, int idTicket, Empleado antiguoResponsable, Empleado nuevoResponsable, String razonCambio) {
        this.idCambio = idCambio;
        this.idTicket = idTicket;
        this.antiguoResponsable = antiguoResponsable;
        this.nuevoResponsable = nuevoResponsable;
        this.razonCambio = razonCambio;
    }

    public int getIdCambio() {
        return idCambio;
    }

    public void setIdCambio(int idCambio) {
        this.idCambio = idCambio;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public Empleado getAntiguoResponsable() {
        return antiguoResponsable;
    }

    public void setAntiguoResponsable(Empleado antiguoResponsable) {
        this.antiguoResponsable = antiguoResponsable;
    }

    public Empleado getNuevoResponsable() {
        return nuevoResponsable;
    }

    public void setNuevoResponsable(Empleado nuevoResponsable) {
        this.nuevoResponsable = nuevoResponsable;
    }

    public String getRazonCambio() {
        return razonCambio;
    }

    public void setRazonCambio(String razonCambio) {
        this.razonCambio = razonCambio;
    }

    @Override
    public String toString() {
        return "CambioResponsables{" +
                "idCambio=" + idCambio +
                ", idTicket=" + idTicket +
                ", antiguoResponsable=" + antiguoResponsable +
                ", nuevoResponsable=" + nuevoResponsable +
                ", razonCambio='" + razonCambio + '\'' +
                '}';
    }
}
