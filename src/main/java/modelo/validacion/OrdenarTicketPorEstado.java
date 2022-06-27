package modelo.validacion;

import modelo.entidad.Ticket;

import java.util.Comparator;

public class OrdenarTicketPorEstado implements Comparator<Ticket> {
    @Override
    public int compare(Ticket t1, Ticket t2) {
        int valor1 = (t1.getEstado().equals("Pendiente")) ? 3 : 2;
        int valor2 = (t2.getEstado().equals("Pendiente")) ? 3 : 2;
        return valor2 - valor1;
    }
}
