
package controlador;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import modelo.Producto;


@WebServlet(name = "AltaProductos", urlPatterns = {"/AltaProductos"})

// El fichero de subida debe existir en el disco
// @MultipartConfig(location = "C:\\upload", maxFileSize = 10485760L) // 10MB

@MultipartConfig(maxFileSize = 10485760L) // 10MB

public class AltaProductos extends HttpServlet {

    String salida = new String();
    String salida2 = new String();
    String salida3 = new String();
        
     /* Al método doGet de este servlet se le invoca desde una vista que pasa por
     parámetro el código de un producto.  
     Este método prepara la lista de proveedores que tiene la empresa. El usuario 
     seleccionará uno de estos proveedores en la select simple que este método 
     construirá de forma dinámica.
     También prepara la lista de catálogos de productos.
    */
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
        else {   // Obtener relación de proveedores
           try {
               Class.forName("com.mysql.jdbc.Driver").newInstance();
               String userName = "dwes";
               String password = "dwes";
               String url = "jdbc:mysql://localhost/gesventa";
               Connection conn = DriverManager.getConnection(url, userName, password);
               Statement stmt = conn.createStatement();
               String sqlStr = "SELECT cif FROM proveedores ";

               ResultSet rset = stmt.executeQuery(sqlStr);
               
               // Se construye un control de tipo SELECT SIMPLE con todos los cifs 
               // de la tabla Proveedores para que se muestre en la vista de alta
               // de productos.
               salida = "";
               salida += "<select class=\"form-control\" name=\"proveedores\">";
             
               while (rset.next()) {
                  salida += "<option value=\"" + rset.getString("cif") + "\">";
                  salida += rset.getString("cif") + "</option>";
               }
               salida += "</select>";
               request.setAttribute("salida", salida);   
               
               
               sqlStr = "SELECT * FROM catalogo ";

               rset = stmt.executeQuery(sqlStr);
               
               // Se construye un control de tipo SELECT SIMPLE con todos los 
               // catálogos para que se muestre en la vista de alta de productos.
               salida2 = "";
               salida2 += "<select class=\"form-control\" name=\"catalogos\">";
             
               while (rset.next()) {
                  salida2 += "<option value=\"" + rset.getString("cod") + "\">";
                  salida2 += rset.getString("descripcion") + "</option>";
               }
               salida2 += "</select>";
               request.setAttribute("salida2", salida2);   
               
               salida3 = "";
               salida3 += "<select class=\"form-control\" name=\"oferta\">";           
               salida3 += "<option value=\"1\">Sí</option>";
               salida3 += "<option value=\"0\">No</option>";
               salida3 += "</select>";
               request.setAttribute("salida3", salida3); 
               
               // Cierre de recursos
               rset.close();
               stmt.close();
               conn.close(); 
                             
               // Reenviar a la vista
               String vista = "/altaProductoView.jsp";
               RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
               dispatcher.forward(request, response); 
                
            } catch (Exception e) {   
                out.println(e.getMessage());
            } 
            
        }   
    }

    // Método invocado desde la vista que tiene el formulario de alta de datos. 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
          
        RequestDispatcher rd = request.getRequestDispatcher("/altaProductoView.jsp");
        
        // Valida que los datos introducidos en el formularios no están vacíos     
        if ( request.getParameter("nom_prod").isEmpty() ) {          
            rd.include(request, response);  
            out.println("<p style=\"color:red; font-weight:bold\">");
            out.println("Debe introducir Nombre Producto.</p>");
        }   
        else if ( request.getParameter("precio").isEmpty() ) {
            rd.include(request, response);  
            out.println("<p style=\"color:red; font-weight:bold\">");
            out.println("Debe introducir Precio.</p>");
        }   
        else if ( request.getParameter("proveedores").isEmpty() ) {
            rd.include(request, response);  
            out.println("<p style=\"color:red; font-weight:bold\">");
            out.println("Debe introducir Proveedor.</p>");
        }
        else {  // Todos los datos han venido rellenados  
            
            String nombre = request.getParameter("nom_prod");
            Double precio = Double.parseDouble(request.getParameter("precio")); 
            String proveedor = request.getParameter("proveedores"); 
            Integer stock = Integer.parseInt(request.getParameter("stock")); 
            Integer catalogo = Integer.parseInt(request.getParameter("catalogos")); 
            String oferta = request.getParameter("oferta");

            // Obtener ruta relativa de la aplicación (empieza en el directorio build/web)
         //   String rutaAplicacion = request.getServletContext().getRealPath("/img");
            // Construye ruta directorio de subida del fichero
         //   String rutaFicheroUpload = rutaAplicacion + File.separator;

            // Si no existe el directorio de subida, se crea
         //   File fichUploadDir = new File(rutaFicheroUpload);
         //   if ( !fichUploadDir.exists() ) {
         //       fichUploadDir.mkdirs();
         //   }

            Part parteArchivo = request.getPart("imagen");   // Recibe la imagen en un objeto de tipo Part
            String nombreArchivo = parteArchivo.getSubmittedFileName(); // Extrae el nombre original del archivo del objeto Part

         //   parteArchivo.write(fichUploadDir + File.separator + nombreArchivo);  // Guarda en el disco con nombre original
           
            // Stream para insertar en columna de tipo BLOB de Mysql
            InputStream is = parteArchivo.getInputStream();
           
            try {                            
                // Conectar a BD e insertar fila
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            
                String userName = "dwes";
                String password = "dwes";
                String url = "jdbc:mysql://localhost/gesventa";
             
                Connection conn = DriverManager.getConnection(url, userName, password);
                
                // Realizar la inserción del producto
                PreparedStatement stmt = 
                        conn.prepareStatement("insert into productos values (?,?,?,?,?,?,?,?,?)");
                stmt.setInt(1, 0);  // La clave es autoincrement
                stmt.setString(2, nombre);
                stmt.setDouble(3, precio);
                stmt.setString(4, proveedor); 
                stmt.setInt(5, stock); 
                stmt.setString(6, nombreArchivo); 
                if ( is != null )  // Si el stream existe insertar BLOB 
                   stmt.setBlob(7, is);  
                stmt.setInt(8, catalogo); 
                stmt.setString(9, oferta);
               
                int filas = stmt.executeUpdate();
                
                stmt.close();
                is.close();
                            
                String sqlStr = "SELECT cif FROM proveedores ";
                
                conn = DriverManager.getConnection(url, userName, password);              
                Statement stmt2 = conn.createStatement();
                ResultSet rset = stmt2.executeQuery(sqlStr);

                // Se construye un control de tipo SELECT SIMPLE con todos los cifs 
                // de la tabla Proveedores para que se muestre en la vista de 
                // modificación de productos.
                salida = "";
                salida += "<select class=\"form-control\" name=\"proveedores\">";
             
                while (rset.next()) {
                    salida += "<option value=\"" + rset.getString("cif") + "\"";
                    if ( rset.getString("cif").compareTo(proveedor) == 0 )
                       salida += "selected = 'selected'";
                    salida += ">" + rset.getString("cif") + "</option>";
                }
                salida += "</select>";
                
                sqlStr = "SELECT * FROM catalogo ";

                rset = stmt2.executeQuery(sqlStr);

                // Se construye un control de tipo SELECT SIMPLE con todos los  
                // datos de la tabla Catalogo para que se muestre en la vista de 
                // modificación de productos.
                salida2 = "";
                salida2 += "<select class=\"form-control\" name=\"catalogos\">";
             
                while (rset.next()) {
                    salida2 += "<option value=\"" + rset.getInt("cod") + "\"";
                    if ( rset.getInt("cod") == catalogo )
                       salida2 += "selected = 'selected'";
                    salida2 += ">" + rset.getString("descripcion") + "</option>";
                }
                salida2 += "</select>";
                
                salida3 = "";
                salida3 += "<select class=\"form-control\" name=\"oferta\">";           
                salida3 += "<option value=\"1\" ";
                if ( oferta.compareTo("1") == 0 )
                   salida3 += "selected = 'selected'";
                salida3 += ">Sí</option>";
                salida3 += "<option value=\"0\" ";
                if ( oferta.compareTo("0") == 0 )
                   salida3 += "selected = 'selected'";
                salida3 += ">No</option>";
                salida3 += "</select>";
                            
                // Cierre de recursos
                stmt2.close();
                conn.close();
               
                Producto producto =  new Producto(0,nombre,precio,proveedor,stock,nombreArchivo,null,catalogo,oferta);
                request.setAttribute("producto", producto);  
                request.setAttribute("salida", salida);  
                request.setAttribute("salida2", salida2);               
                request.setAttribute("salida3", salida3);   
               
                rd.include(request, response);  
                if ( filas > 0 ) {
                   out.println("<p style=\"color:green; font-weight:bold\">"); 
                   out.println("Producto insertado correctamente.</p>");
                }   
                
            } catch (Exception ex) {
                out.println(ex.getMessage());
            }

        } 
    }

}
