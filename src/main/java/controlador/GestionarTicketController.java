package controlador;

import modelo.dao.TicketDAO;
import modelo.entidad.Emisor;
import modelo.entidad.Ticket;
import modelo.validacion.Validaciones;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "GestionarTicketController", value = "/GestionarTicketController")
public class GestionarTicketController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GestionarTicketController - doGet");
        //Accedo a los datos del ticket
        int idTicket = Integer.parseInt(request.getParameter("idTicket"));
        TicketDAO ticketdao = new TicketDAO();
        Ticket miTicket = ticketdao.obtenerTicket(idTicket);
        Emisor miEmisor = miTicket.getEmisor();
        //Cargo los datos en el jsp
        request.setAttribute("miTicket", miTicket);
        request.setAttribute("miEmisor", miEmisor);

        //En caso que el ticket este finalizado, se muestra el tiempo que tardo en ser resuelto.
        if(miTicket.getEstado().equals("Finalizado")){
            String fechaCierre = miTicket.getFechaCierre();
            String fechaCreacion = miTicket.getFechaCreacion();
            String tiempoResolucion = Validaciones.calcularTiempoResolucion(fechaCierre, fechaCreacion);
            request.setAttribute("tiempoResolucion", tiempoResolucion);

        }

        //Llamo al jsp
        request.getRequestDispatcher("/jsp/moduloEmpleado/gestionTicket.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GestionarTicketController - doPost");
        //Accedo a los datos del ticket
        //Aqui voy a actualizar los datos del ticket.
        int idTicket = Integer.parseInt(request.getParameter("idTicket"));
        String estado = request.getParameter("selectEstado");
        String prioridad = request.getParameter("selectPrioridad");


        System.out.println("idTicket: " + idTicket);
        System.out.println("prioridad: " + prioridad);
        System.out.println("estado: " + estado);

        String solucion = null;
        String fechaCierre = null;

        //Se agrega una fecha de cierre y solucion cuando se detecta que el estado es finalizado.
        if(estado.equals("Finalizado")){
            fechaCierre = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            solucion = request.getParameter("solucion");
        }

        System.out.println("Fecha cierre: " + fechaCierre);
        //Se guardan los datos en la base de datos.
        Ticket miTicket = new Ticket(idTicket, estado, prioridad, solucion, fechaCierre);
        TicketDAO ticketdao = new TicketDAO();
        ticketdao.actualizarTicket(miTicket);

        //Se redirige al jsp de gestion de tickets.
        request.getRequestDispatcher("ListarTicketsController").forward(request, response);

    }
}
