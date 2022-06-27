import modelo.dao.EmisorDAO;
import modelo.dao.TicketDAO;
import modelo.entidad.Emisor;
import modelo.entidad.Ticket;
import modelo.validacion.OrdenarTicketPorEstado;
import modelo.validacion.OrdenarTicketPorPrioridad;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class test {

    public static void main(String[] args) {
        System.out.println("Hello, World!");

//        String fechaCierre = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        System.out.println(fechaCierre);
//        String fechaCierre =  "2022-06-18 11:20:00";
//        String fechaCreacion = "2022-03-01 12:24:32";
//
//        LocalDateTime fechaCierreDate = LocalDateTime.parse(fechaCierre, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        LocalDateTime fechaCreacionDate = LocalDateTime.parse(fechaCreacion, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//
//        Period tiempoTranscurrido = Period.between(fechaCreacionDate.toLocalDate(), fechaCierreDate.toLocalDate());
//        System.out.println(tiempoTranscurrido.getDays());
//        System.out.println(tiempoTranscurrido.getMonths());
//        System.out.println(tiempoTranscurrido.getYears());
//        System.out.println(tiempoTranscurrido);
//        System.out.println(ChronoUnit.HOURS.between(fechaCreacionDate, fechaCierreDate));

//        Ticket miTicket1 = new Ticket();
//        miTicket1.setIdTicket(1);
//        miTicket1.setPrioridad("Baja");
//        miTicket1.setEstado("Finalizado");
//        miTicket1.setTitulo("Problema con el servidor");
//
//        Ticket miTicket2 = new Ticket();
//        miTicket2.setIdTicket(2);
//        miTicket2.setPrioridad("Alta");
//        miTicket2.setEstado("Pendiente");
//        miTicket2.setTitulo("mi ticket 2");
//
//        Ticket miTicket3 = new Ticket();
//        miTicket3.setIdTicket(3);
//        miTicket3.setPrioridad("Media");
//        miTicket3.setEstado("Pendiente");
//        miTicket3.setTitulo("mi ticket 3");
//
//        List<Ticket> listaTickets = new ArrayList<>();
//        listaTickets.add(miTicket1);
//        listaTickets.add(miTicket2);
//        listaTickets.add(miTicket3);
//
//        Collections.sort(listaTickets, new OrdenarTicketPorEstado());
//        for (Ticket ticket : listaTickets) {
//            System.out.println(ticket);
//        }

//
//        String miFecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        System.out.println(miFecha);

//        Ticket miTicket = new Ticket();
//        miTicket.setFechaCreacion(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        miTicket.setTitulo("titulo");
//        miTicket.setEstado("Pendiente");
//        miTicket.setPrioridad("Baja");
//        miTicket.setDescripcion("descripcion");
//        miTicket.setSolucion(null);
//        miTicket.setFechaCierre(null);
//
//        Emisor emisor = new Emisor();
//        emisor.setIdEmisor(1);
//        miTicket.setEmisor(emisor);
//
//        System.out.println(miTicket);
//
//        TicketDAO miTicketDAO = new TicketDAO();
//        miTicketDAO.crearTicket(miTicket);
//        TicketDAO  miTicketDAO = new TicketDAO();
//        System.out.println(miTicketDAO.obtenerEmpleadoConMenosCarga());
        EmisorDAO emisorDAO= new EmisorDAO();
        Emisor emisor = emisorDAO.autenticar("juan.perez@epn.edu.ec", "juan123");
        System.out.println(emisor);



        TicketDAO miTicketDAO = new TicketDAO();
        Ticket miTicket = new Ticket();
        miTicket.setFechaCreacion(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        miTicket.setTitulo("titulo");
        miTicket.setEstado("Pendiente");
        miTicket.setPrioridad("Baja");
        miTicket.setDescripcion("descripcion");
        miTicket.setSolucion(null);
        miTicket.setFechaCierre(null);
        miTicket.setEmisor(emisor);

        //Imprimo el ticket
        System.out.println("Imprimo el ticket: " + miTicket);
        //Se guarda el ticket en la base de datos
        miTicketDAO.crearTicket(miTicket);




    }
}
