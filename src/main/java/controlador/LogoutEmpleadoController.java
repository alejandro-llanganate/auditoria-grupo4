package controlador;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogoutEmpleadoController", value = "/LogoutEmpleadoController")
public class LogoutEmpleadoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LogoutEmpleadoController - doGet");
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("index.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LogoutEmpleadoController - doPost");

    }
}
