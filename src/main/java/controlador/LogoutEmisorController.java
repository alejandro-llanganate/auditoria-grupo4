package controlador;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogoutEmisorController", value = "/LogoutEmisorController")
public class LogoutEmisorController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LogoutEmisorController - doGet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LogoutEmisorController - doPost");
        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("/jsp/moduloEmisor/loginEmisor.jsp").forward(request, response);

    }
}
