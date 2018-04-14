
package controlador;

import java.awt.Image;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Producto;


@WebServlet(name = "ListarProductosAdmin", urlPatterns = {"/ListarProductosAdmin"})
public class ListarProductosAdmin extends HttpServlet {

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
        else {   // Obtener relación de productos para administrar
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String userName = "dwes";
            String password = "dwes";
            String url = "jdbc:mysql://localhost/gesventa";
            Connection conn = DriverManager.getConnection(url, userName, password);
            Statement stmt = conn.createStatement();

            String sqlStr = "SELECT * FROM productos ORDER BY cod";
            ResultSet rset = stmt.executeQuery(sqlStr);

            ArrayList <Producto> productos = new ArrayList();

            Image rpta = null;

            while (rset.next()) {
                Integer codigo = rset.getInt("cod");
                String nombre = rset.getString("nom_prod");
                Double precio = rset.getDouble("pvp");
                String proveedor = rset.getString("prov");
                String imagen = rset.getString("imagen");
                Blob imagen2 = rset.getBlob("imagen2");
                Integer catalogo = rset.getInt("cat");
                String oferta = rset.getString("oferta");

                rpta = javax.imageio.ImageIO.read(imagen2.getBinaryStream());
                Producto prod = new Producto(codigo,nombre,precio,proveedor,imagen,rpta,catalogo,oferta);
                productos.add(prod);
            }   

            request.setAttribute("productos", productos);   

            // Cierre de recursos
            rset.close();
            stmt.close();
            conn.close();
            
            RequestDispatcher dispatcher = 
                    getServletContext().getRequestDispatcher("/productosViewAdmin.jsp");
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
