package modelo.validacion;

import modelo.entidad.Ticket;

import java.util.Comparator;

public class OrdenarTicketPorPrioridad implements Comparator<Ticket> {

    @Override
    public int compare(Ticket t1, Ticket t2) {
        int valor1 = (t1.getPrioridad().equals("Alta")) ? 3 : (t1.getPrioridad().equals("Media")) ? 2 : 1;
        int valor2 = (t2.getPrioridad() == "Alta") ? 3 : (t2.getPrioridad() == "Media") ? 2 : 1;
        return valor2 - valor1;
    }
}
