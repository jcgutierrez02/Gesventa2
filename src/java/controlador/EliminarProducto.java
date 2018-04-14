
package controlador;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "EliminarProducto", urlPatterns = {"/EliminarProducto"})
public class EliminarProducto extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /* Al método doGet de este servlet se le invoca desde una vista que pasa por
     parámetro el código de un producto a eliminar.  
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        RequestDispatcher rd = request.getRequestDispatcher("/productosView.jsp");
       
        if ( request.getSession().getAttribute("user") == null ) {  // Si no existe una sesión ir a login.jsp
            request.getRequestDispatcher("login.jsp").include(request, response); 
            out.println("<p style=\"color:red; font-weight:bold\">");
            out.println("Para entrar debe autenticarse primero.</p>");
        }
        else {   
            
            Integer cod = Integer.parseInt(request.getParameter("cod")); 
           
            try {
               
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String userName = "dwes";
                String password = "dwes";
                String url = "jdbc:mysql://localhost/gesventa";
                Connection conn = DriverManager.getConnection(url, userName, password);
                              
                PreparedStatement stmt = 
                        conn.prepareStatement("DELETE FROM Productos WHERE cod=?");
               
                stmt.setInt(1, cod);
                stmt.executeUpdate();

                // Cierre de recursos
                stmt.close();
                conn.close(); 
               
                // Redirigir a la vista lista de productos para verficar la operación.
                response.sendRedirect("ListarProductos");
            
            } catch (Exception ex) {
                out.println(ex.getMessage());
            }
        } 
    }
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
