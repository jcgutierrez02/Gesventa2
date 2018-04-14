package controlador;

import java.awt.Image;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import modelo.Producto;
import modelo.Registro;

@WebServlet(name = "RegistrarUsuario", urlPatterns = {"/RegistrarUsuario"})

@MultipartConfig(maxFileSize = 10485760L) // 10MB máximo de subida ficheros

public class RegistrarUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    // Método invocado desde la vista que tiene el formulario de modificación de datos. 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
          
        String nom_usu = request.getParameter("nom_usu");
        String nom_apell = request.getParameter("nom_apell");
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        String clave2 = request.getParameter("clave2");
            
        RequestDispatcher rd = request.getRequestDispatcher("/registroView.jsp");
        Registro registro =  new Registro(nom_usu, nom_apell, usuario, clave);
        request.setAttribute("registro", registro);  
               
        // Valida que los datos introducidos en el formularios no están vacíos
        if ( nom_usu.isEmpty() ) {          
           rd.include(request, response);  
           out.println("<p style=\"color:red; font-weight:bold\">");
	   out.println("Debe introducir Nombre Usuario.</p>");
        }   
        else if ( nom_apell.isEmpty() ) {
           rd.include(request, response);  
           out.println("<p style=\"color:red; font-weight:bold\">");
	   out.println("Debe introducir Apellidos.</p>");
        }   
        else if ( usuario.isEmpty() ) {
           rd.include(request, response);  
           out.println("<p style=\"color:red; font-weight:bold\">");
	   out.println("Debe introducir e-mail válido.</p>");
        }
        else if ( clave.isEmpty() ) {
           rd.include(request, response);  
           out.println("<p style=\"color:red; font-weight:bold\">");
	   out.println("Debe introducir contraseña.</p>");
        }
         else if ( clave.compareTo(clave2) !=0 ) {
           rd.include(request, response);  
           out.println("<p style=\"color:red; font-weight:bold\">");
	   out.println("Contraseñas no coinciden.</p>");
        }
        else {   // Todos los datos han venido rellenados
            
           try {                                     
               // Conectar a BD e insertar fila 
               Class.forName("com.mysql.jdbc.Driver").newInstance();
               String userName = "dwes";
               String password = "dwes";
               String url = "jdbc:mysql://localhost/gesventa";
             
               Connection conn = DriverManager.getConnection(url, userName, password);
               
               PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO Registros VALUES (?, ?, ?, ?)");
               
               // Realizar la inserción del usuario a registrar
               stmt.setString(1, nom_usu);
               stmt.setString(2, nom_apell); 
               stmt.setString(3, usuario); 
               stmt.setString(4, clave); 
              
               int filas = stmt.executeUpdate();
               
               // Cierre de recursos
               stmt.close();
               conn.close(); 
            
               // Cuando el registro sea correcto ir a página inicial
               rd = request.getRequestDispatcher("/homeView.jsp");
               rd.include(request, response);  
               if ( filas > 0 ) {
                  out.println("<p style=\"color:green; font-weight:bold\">"); 
                  out.println("Registro realizado correctamente.</p>");
               }   
               
           } catch (Exception e) {   
                out.println(e.getMessage());
           }

         } 
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
