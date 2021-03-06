<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta Proveedores</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="css/estilos.css" rel="stylesheet" media="screen">
        <script src="js/bootstrap.min.js"></script> 
    </head>   
    <body>
        <% request.setCharacterEncoding("UTF-8"); %>
        
        <jsp:include page="header.jsp" />  
        
         <div class="row" id="principal">  
            <jsp:include page="menu.jsp" />  
            
            <article class="col-xs-12 col-sm-8">
                    <div class="panel panel-success"> 
                        <div class="panel-heading">
                            <h2 class="panel-title"><strong>Listado de Proveedores</strong>
                                Conectado como <%= session.getAttribute("user") %></h2>
                        </div>
                    </div>    
         
                <form method="POST" action="">
                    Población: <input type="text" name="poblacion"  
                                   value=<%= request.getParameter("poblacion") %> /> <br/>
                    <input type="submit" value="Enviar" />
                </form>
            
                <%@ page import = "java.sql.*" %>
                <%
                 try {    
                    String parametro = request.getParameter("poblacion");
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    String userName="dwes";
                    String password="dwes";
                    String url="jdbc:mysql://localhost/gesventa";
                    Connection conn = DriverManager.getConnection(url, userName, password);	 
                    Statement stmt = conn.createStatement();
                    String sqlStr = new String();
                    if (parametro != null && parametro != "") {		  
                        sqlStr = "SELECT * FROM proveedores WHERE poblacion = ";
                        sqlStr += "'" + parametro + "'";
                    }      
                    else {
                        sqlStr = "SELECT * FROM proveedores";
                    }     		
                    ResultSet rset = stmt.executeQuery(sqlStr);
                 %>
                    <hr>
                    <table class="table table-striped">
                        <tr>
                            <th>Cif</th>
                            <th>Nombre</th>
                            <th>Dirección</th>
                            <th>Población</th>
                        </tr>
                    <%

                        while (rset.next()) {
                    %>
                          <tr>
                            <td><%= rset.getString("cif") %></td>
                            <td><%= rset.getString("nom_emp") %></td>
                            <td><%= rset.getString("direccion") %></td>
                            <td><%= rset.getString("poblacion") %></td>
                          </tr>
                        <%
                        }
                        %>
                    </table>
            </article>        
          
        <%
              //Cierre de recursos
            rset.close();
            stmt.close();
            conn.close(); 
        } catch(Exception e) {
            e.printStackTrace(new java.io.PrintWriter(out));
        }
              
        %>
        </div>
        <jsp:include page="footer.jsp" />   
    </body>
</html>
