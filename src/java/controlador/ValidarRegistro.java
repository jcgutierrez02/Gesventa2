package controlador;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ValidarRegistro", urlPatterns = {"/ValidarRegistro"})
public class ValidarRegistro extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
    }
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
        
        RequestDispatcher rd = request.getRequestDispatcher("/login2.jsp");
        
        // Se recogen los datos enviados del formulario y se comprueba si se han
        // introducido o no.
        String name = request.getParameter("user");
        String pass = request.getParameter("pass");
        
        if ( name == "" && pass != "" ) {
            rd.include(request, response);  
            out.println("<p style=\"color:red; font-weight:bold\">");
            out.println("Debe introducir Usuario.</p>");
        }
        else if ( name != "" && pass == "" ) {
            rd.include(request, response);  
            out.println("<p style=\"color:red; font-weight:bold\">");
            out.println("Debe introducir Contraseña.</p>");
        }
        else if ( name == "" && pass == "" ) {
            rd.include(request, response);  
            out.println("<p style=\"color:red; font-weight:bold\">");
            out.println("Debe introducir Usuario y Contraseña.</p>");
        }        
        else  // Se introdujeron ambos datos
        {
            boolean usuarioValido = false;   
         
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String userName = "dwes";
                String password = "dwes";
                String url = "jdbc:mysql://localhost/gesventa";
              
                Connection conn = DriverManager.getConnection(url, userName, password);
                String sqlStr = "SELECT * FROM registros WHERE usuario=? AND clave=? ";
                PreparedStatement stmt = conn.prepareStatement(sqlStr);
                stmt.setString(1, name);
                stmt.setString(2, pass);

                ResultSet rset = stmt.executeQuery();
                while (rset.next()) {
                   usuarioValido = true;
                }
                
                // Cierre de recursos
                rset.close();
                stmt.close(); 
             
            } catch (Exception e) {   
                 out.println(e.getMessage());
            } 
           
            // Si el usuario es válido, crear la sesión y en caso contrario 
            // regresar al login indicando mensaje de error.
            if ( usuarioValido ) {          
                HttpSession sesion  = request.getSession();
                sesion.setMaxInactiveInterval(300) ;   // 5 min
             
                sesion.setAttribute("user", name);
                response.sendRedirect("ListarProductos");
            }
            else  
            {
                rd.include(request, response); 
                out.println("<p style=\"color:red; font-weight:bold\">");
                out.println("Usuario/Contraseña no válidos.</p>");
            }
        }   
    }
   
}
