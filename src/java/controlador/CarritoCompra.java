
package controlador;

import java.awt.Image;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Producto;


@WebServlet(name = "CarritoCompra", urlPatterns = {"/CarritoCompra"})
public class CarritoCompra extends HttpServlet {
   
    ArrayList <Producto> carrito = new ArrayList <Producto>();
                   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /* Al método doGet de este servlet se le invoca desde una vista que pasa por
     parámetro el código de un producto a añadir al carrito.  
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        if ( request.getSession().getAttribute("registro") == null ) {  // Si no existe una sesión ir a login2.jsp
            request.getRequestDispatcher("login2.jsp").include(request, response); 
            out.println("<p style=\"color:red; font-weight:bold\">");
            out.println("Para entrar debe autenticarse primero.</p>");
        }
        else {   
            
            Integer cod = Integer.parseInt(request.getParameter("cod")); 
            String tipo = request.getParameter("tipo"); 
           
            if ( cod != null )
            {
            try {
               
                if ( tipo == null ) {  // Listar carrito
                
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    String userName = "dwes";
                    String password = "dwes";
                    String url = "jdbc:mysql://localhost/gesventa";
                    Connection conn = DriverManager.getConnection(url, userName, password);
                    Statement stmt = conn.createStatement();

                    String sqlStr = "SELECT * FROM productos WHERE cod=" + request.getParameter("cod");
                    ResultSet rset = stmt.executeQuery(sqlStr);

                    Image rpta = null;
                    while (rset.next()) {
                       Integer codigo = rset.getInt("cod");
                       String nombre = rset.getString("nom_prod");
                       Double precio = rset.getDouble("pvp");
                       Integer stock = rset.getInt("stock");
                       String imagen = rset.getString("imagen");
                       Blob imagen2 = rset.getBlob("imagen2");
                       Integer catalogo = rset.getInt("cat");
                       String oferta = rset.getString("oferta");

                       rpta = javax.imageio.ImageIO.read(imagen2.getBinaryStream());
               
                       Producto producto = new Producto(codigo,nombre,precio,null,stock,imagen,rpta,catalogo,oferta);

                       // Añadir el producto al carrito si no se ha añadido previamente
                       boolean existe = false;
                       for ( Producto p : carrito )
                          if ( p.getCod() == codigo ) {
                              existe = true;
                              break;
                          }
                       if ( !existe )       
                          carrito.add(producto);
                    }   

                    // Cierre de recursos
                    stmt.close();
                    conn.close(); 

                }
                else {  // Elimianr de carrito
                    for ( Producto p : carrito )
                        if ( p.getCod() == cod ) {
                           carrito.remove(p);
                           break;
                        }
                    
                }
                
            } catch (Exception ex) {
                out.println(ex.getMessage());
            }
          }
          
          request.setAttribute("carrito", carrito); 
          // Redirigir a la vista lista de productos para verficar la operación.
          RequestDispatcher rd = request.getRequestDispatcher("/carritoCompraView.jsp");     
          rd.forward(request, response);  
             
        } 
    }
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
