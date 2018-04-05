
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

@WebServlet(name = "ListarProveedores", urlPatterns = {"/ListarProveedores"})
public class ListarProveedores extends HttpServlet {

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
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String userName = "dwes";
                String password = "dwes";
                String url = "jdbc:mysql://localhost/gesventa";
                Connection conn = DriverManager.getConnection(url, userName, password);
                Statement stmt = conn.createStatement();
                String sqlStr = "SELECT * FROM proveedores ";

                ResultSet rset = stmt.executeQuery(sqlStr);

                ArrayList <Proveedor> proveedores = new ArrayList <Proveedor>();

                while (rset.next()) {
                    String cif = rset.getString("cif");
                    String nom_emp = rset.getString("nom_emp");
                    String direccion = rset.getString("direccion");
                    String poblacion = rset.getString("poblacion");

                    Proveedor provee = new Proveedor(cif,nom_emp,direccion,poblacion);
                    proveedores.add(provee);
                }   
                request.setAttribute("proveedores", proveedores);   
                
                // Cierre de recursos
                rset.close();
                stmt.close();
                conn.close(); 

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/proveedoresView.jsp");
                dispatcher.forward(request, response); 

            } catch (Exception e) {   
                out.println(e.getMessage());
            }
        }
    }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
