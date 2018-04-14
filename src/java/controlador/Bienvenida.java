
package controlador;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Bienvenida", urlPatterns = {"/Bienvenida"})
public class Bienvenida extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         
        if ( request.getSession().getAttribute("user") == null ) {  // Si no existe una sesión ir a login.jsp
           request.getRequestDispatcher("login.jsp").include(request, response); 
           out.println("<p style=\"color:red; font-weight:bold\">");
	   out.println("Para entrar debe autenticarse primero.</p>");
        }
        else    
           response.sendRedirect("homeView.jsp");
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

}
