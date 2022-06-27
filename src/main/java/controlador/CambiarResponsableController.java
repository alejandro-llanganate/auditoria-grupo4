package controlador;

import modelo.dao.CambioResponsablesDAO;
import modelo.dao.EmpleadoDAO;
import modelo.entidad.CambioResponsables;
import modelo.entidad.Empleado;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CambiarResponsableController", value = "/CambiarResponsableController")
public class CambiarResponsableController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CambiarResponsableController -doGet");
        int idTicket = Integer.parseInt(request.getParameter("idTicket"));
        //Se obtiene al empleado que está en sesión
        Empleado miEmpleado = (Empleado) request.getSession().getAttribute("miEmpleado");

        //Obtengo la lista de empleados disponibles
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        List<Empleado> empleadosDisponibles = empleadoDAO.obtenerTodosLosEmpleados();
        //tengo que quitarme de la lista
        for(int i = 0; i < empleadosDisponibles.size(); i++){
            if(empleadosDisponibles.get(i).getIdEmpleado() == miEmpleado.getIdEmpleado()){
                empleadosDisponibles.remove(i);
            }
        }



        //Obtengo el registro de cambios de ese ticket
        CambioResponsablesDAO cambioResponsablesDAO = new CambioResponsablesDAO();
        List<CambioResponsables> cambioResponsables = cambioResponsablesDAO.obtenerTodosLosResponsablesDelTicket(idTicket);

//        //Filtro los empleados, veririfico que no se repitan.
//        List<Empleado> empleadosResponsables = new ArrayList<>();
//        ArrayList<Integer> auxIds = new ArrayList<>();
//        for(int i = 0; i < cambioResponsables.size(); i++){
//            if(!auxIds.contains(cambioResponsables.get(i).getAntiguoResponsable().getIdEmpleado())){
//                empleadosResponsables.add(cambioResponsables.get(i).getAntiguoResponsable());
//                auxIds.add(cambioResponsables.get(i).getAntiguoResponsable().getIdEmpleado());
//            }
//            if(!auxIds.contains(cambioResponsables.get(i).getNuevoResponsable().getIdEmpleado())){
//                empleadosResponsables.add(cambioResponsables.get(i).getNuevoResponsable());
//                auxIds.add(cambioResponsables.get(i).getNuevoResponsable().getIdEmpleado());
//            }
//        }

        //Mando toda la info

        //Mando los datos a la vista
        request.setAttribute("disponibles", empleadosDisponibles);
        request.setAttribute("responsables", cambioResponsables);
        request.setAttribute("idTicket", idTicket);

        //Llamo al jsp
        request.getRequestDispatcher("/jsp/moduloEmpleado/cambioResponsable.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CambiarResponsableController -doPost");
        //Obtengo el id del nuevo empleado, el id del ticket y la razon del cambio.
        int idTicket = Integer.parseInt(request.getParameter("idTicket"));
        int idNuevoEmpleado = Integer.parseInt(request.getParameter("selectDisponible"));
        String razonCambio = request.getParameter("razonCambio");

        //Obtengo el empleado que está en sesión (ahora sera el antiguo)
        Empleado miEmpleadoAntiguo = (Empleado) request.getSession().getAttribute("miEmpleado");

        //Obtengo el empleado nuevo
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        Empleado miEmpleadoNuevo = empleadoDAO.obtenerEmpleado(idNuevoEmpleado);

        //Agregamos los datos a cambio de responsables
        CambioResponsables miCambioResponsable = new CambioResponsables();
        miCambioResponsable.setIdTicket(idTicket);
        miCambioResponsable.setAntiguoResponsable(miEmpleadoAntiguo);
        miCambioResponsable.setNuevoResponsable(miEmpleadoNuevo);
        miCambioResponsable.setRazonCambio(razonCambio);

        //Agregamos el cambio de responsable a la base de datos
        CambioResponsablesDAO cambioResponsablesDAO = new CambioResponsablesDAO();
        cambioResponsablesDAO.insertarCambioResponsable(miCambioResponsable);

        //La actualizacion del ticket se hace automaticamente por el trigger

        //Se redirige al jsp de gestion de tickets.
        request.getRequestDispatcher("ListarTicketsController").forward(request, response);
    }
}
