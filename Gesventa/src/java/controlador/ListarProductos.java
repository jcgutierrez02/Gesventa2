
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

@WebServlet(name = "ListarProductos", urlPatterns = {"/ListarProductos"})
public class ListarProductos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        
        Integer valor = 0;
        if ( request.getParameter("valor") == null )
            valor = 0;
        else
            valor = Integer.parseInt(request.getParameter("valor")); 
        
        Integer categoria = 0;
        if ( request.getParameter("cat") == null )
            categoria = 0;
        else
            categoria = Integer.parseInt(request.getParameter("cat")); 
        
        String sqlStr = null;
        if ( valor == 0 ) 
           sqlStr = "SELECT * FROM productos ORDER BY cod";
        else if ( valor == 1 )
                sqlStr = "SELECT * FROM productos ORDER BY cod DESC";
        else if ( valor == 2 )
                sqlStr = "SELECT * FROM productos ORDER BY pvp";
        else if ( valor == 3 )
                sqlStr = "SELECT * FROM productos ORDER BY pvp DESC";
        else if ( valor == 4 )
                sqlStr = "SELECT * FROM productos WHERE oferta=\"1\" ORDER BY pvp DESC";
                  
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String userName = "dwes";
            String password = "dwes";
            String url = "jdbc:mysql://localhost/gesventa";
            Connection conn = DriverManager.getConnection(url, userName, password);
            Statement stmt = conn.createStatement();

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
               if ( categoria == 0 || (categoria !=0 && categoria == catalogo) )   
                  productos.add(prod);
            }   

            request.setAttribute("valor", valor);  
            request.setAttribute("categoria", categoria);  
            request.setAttribute("productos", productos);   

            // Cierre de recursos
            rset.close();
            stmt.close();
            conn.close(); 

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/productosView.jsp");
            dispatcher.forward(request, response); 

        } catch (Exception e) {   
            out.println(e.getMessage());
        }
          
    }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        
        Integer valor = 0;
        if ( request.getParameter("valor") == null )
            valor = 0;
        else
            valor = Integer.parseInt(request.getParameter("valor")); 
        
        Integer categoria = 0;
        if ( request.getParameter("cat") == null )
            categoria = 0;
        else
            categoria = Integer.parseInt(request.getParameter("cat")); 
        
        String sqlStr = null;
        if ( valor == 0 ) 
           sqlStr = "SELECT * FROM productos ORDER BY cod";
        else if ( valor == 1 )
                sqlStr = "SELECT * FROM productos ORDER BY cod DESC";
        else if ( valor == 2 )
                sqlStr = "SELECT * FROM productos ORDER BY pvp";
        else if ( valor == 3 )
                sqlStr = "SELECT * FROM productos ORDER BY pvp DESC";
                  
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String userName = "dwes";
            String password = "dwes";
            String url = "jdbc:mysql://localhost/gesventa";
            Connection conn = DriverManager.getConnection(url, userName, password);
            Statement stmt = conn.createStatement();

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
               if ( categoria == 0 || (categoria !=0 && categoria == catalogo) )   
                  productos.add(prod);
            }   

            request.setAttribute("valor", valor);  
            request.setAttribute("categoria", categoria);  
            request.setAttribute("productos", productos);   

            // Cierre de recursos
            rset.close();
            stmt.close();
            conn.close(); 

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/productosView.jsp");
            dispatcher.forward(request, response); 

        } catch (Exception e) {   
             out.println(e.getMessage());
        }
          
        
    }
    
}
