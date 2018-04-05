
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

import modelo.Proveedor;

@WebServlet(name = "AltaProveedores", urlPatterns = {"/Alta"})
public class AltaProveedores extends HttpServlet {

    // Al método doGet de este servlet se le invoca desde una vista o menú.
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
        else {   
           response.sendRedirect("altaProveedorView.jsp");
        }   
    }

    // Método invocado desde la vista que tiene el formulario de alta de datos.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String cif = request.getParameter("cif");
        String nombre = request.getParameter("nom_emp");
        String direccion = request.getParameter("direccion");
        String poblacion = request.getParameter("poblacion"); 
        
        RequestDispatcher rd = request.getRequestDispatcher("/altaProveedorView.jsp");
        
        // Valida que los datos introducidos en el formularios no están vacíos   
        if ( cif == "" ) {
           rd.include(request, response);  
           out.println("<p style=\"color:red; font-weight:bold\">");
	   out.println("Debe introducir Cif.</p>");
        }   
        else if ( nombre == "" ) {
           rd.include(request, response);  
           out.println("<p style=\"color:red; font-weight:bold\">");
	   out.println("Debe introducir Nombre Proveedor.</p>");
        }   
        else if ( direccion == "" ) {
           rd.include(request, response);  
           out.println("<p style=\"color:red; font-weight:bold\">");
	   out.println("Debe introducir Dirección.</p>");
        }   
        else if ( poblacion == "" ) {
           rd.include(request, response);  
           out.println("<p style=\"color:red; font-weight:bold\">");
	   out.println("Debe introducir Población.</p>");
        }
        else {   // Todos los datos han venido rellenados  
              
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String userName = "dwes";
                String password = "dwes";
                String url = "jdbc:mysql://localhost/gesventa";
                Connection conn = DriverManager.getConnection(url, userName, password);
                
                // Realizar la inserción del proveedor
                PreparedStatement stmt = conn.prepareStatement("insert into proveedores values (?,?,?,?)");
                stmt.setString(1, cif);
                stmt.setString(2, nombre);
                stmt.setString(3, direccion);
                stmt.setString(4, poblacion); 
                
                int filas = stmt.executeUpdate();

                // Cierre de recursos
                stmt.close();
                conn.close(); 
                
                rd.include(request, response);
                if ( filas > 0 ) {
                   out.println("<p style=\"color:green; font-weight:bold\">");
                   out.println("Proveedor insertado correctamente.</p>");
                }    

            } catch (Exception e) {   
                out.println(e.getMessage());
            }
      
        }    
    }

}
