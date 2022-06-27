package controlador;

import modelo.dao.TicketDAO;
import modelo.entidad.Emisor;
import modelo.entidad.Ticket;
import modelo.validacion.Validaciones;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GestionSolicitudController", value = "/GestionSolicitudController")
public class GestionSolicitudController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GestionSolicitudController - doGet");

        //SE obtiene el id del ticket que se quiere ver.
        int idTicket = Integer.parseInt(request.getParameter("idTicket"));
        //Se obtieen los datos del ticket.
        TicketDAO miTicketDAO = new TicketDAO();
        Ticket miTicket = miTicketDAO.obtenerTicket(idTicket);

        //En caso que el ticket este finalizado, se muestra el tiempo que tardo en ser resuelto.
        if(miTicket.getEstado().equals("Finalizado")){
            String fechaCierre = miTicket.getFechaCierre();
            String fechaCreacion = miTicket.getFechaCreacion();
            String tiempoResolucion = Validaciones.calcularTiempoResolucion(fechaCierre, fechaCreacion);
            request.setAttribute("tiempoResolucion", tiempoResolucion);

        }
        //Se envian los datos a la vista.
        request.setAttribute("miTicket", miTicket);

        //Se muestra la vista.
        request.getRequestDispatcher("/jsp/moduloEmisor/infoMiTicket.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GestionSolicitudController - doPost");

        //Se accede al emisor que inicio la sesion.
        Emisor miEmisor = (Emisor) request.getSession().getAttribute("miEmisor");

        //Se cargan los tickets de este emisor.
        TicketDAO miTicketDAO = new TicketDAO();
        List<Ticket> listaTicketsDelEmisor = miTicketDAO.obtenerTicketsPorEmisor(miEmisor.getIdEmisor());

        //Se envian los datos a la vista.
        request.setAttribute("nombreEmisor", miEmisor.getNombre().toUpperCase());
        request.setAttribute("listaTicketsDelEmisor", listaTicketsDelEmisor);

        //Se muestra la vista.
        request.getRequestDispatcher("/jsp/moduloEmisor/gestionSolicitud.jsp").forward(request, response);

    }
}
