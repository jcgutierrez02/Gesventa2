<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Productos</title>
        <link rel='stylesheet' type='text/css' href='css/estilos.css' /> 
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <%   
        request.setCharacterEncoding("UTF-8");
        ArrayList <Producto> productos = (ArrayList <Producto>) request.getAttribute("productos");
    %>
    <body>
        <jsp:include page="header.jsp"></jsp:include>    
        <jsp:include page="navegacion.jsp"></jsp:include>
         
        <div class="row" id="principal">  
            <jsp:include page="menu.jsp"></jsp:include>
            <article class="col-xs-12 col-sm-8">
                <div class="panel panel-success"> 
                    <div class="panel-heading">
                        <h3 class="panel-title"><strong>Listado de Productos Administrador</strong>
                            <span style="float:right;">Conectado como <strong><%= session.getAttribute("user") %>
                                </strong></span>
                        </h3>
                    </div>
                </div>                                              
              <%
                if ( productos != null )  {
              %>    
                    <hr>
              <%
                    for ( Producto prod : productos ) {    
                        if ( prod.getOferta().compareTo("0") == 0 ) {  // No está en oferta
              %>
                        <div class="imagen">
              <%        } else {
              %>
                        <div class="imagen_oferta">
              <%        } %> 
                   
                        <span class="negrilla"><%= prod.getNombre() %></span><br/>
                        <a href="ModificarProducto?cod=<%= prod.getCod() %>&prov=<%= prod.getProveedor() %>&cat=<%= prod.getCat() %>">
                           <img src="<%= request.getContextPath()+"/img/"+prod.getImagen() %>"
                                width="80%" height="80%" title="Modificar" />
                        <a/>
                        <br/>
                        <div style="margin-top: 10px;">
                            <span class="negrilla"><%= prod.getPrecio() %> €</span>
                            <a href="EliminarProducto?cod=<%= prod.getCod() %>"
                               onclick="return confirm('¿ Seguro que desea eliminar el producto ?')">
                                <img style="float:right; " src="img/borrar.jpg" width="10%" height="10%" 
                                     title="Eliminar"  />
                            </a>    
                        </div>                         
                    </div>    
                        
              <%
                    }
                }
              %>             
                </div>
            </article>
        </div>    
        <jsp:include page="footer.jsp"></jsp:include>  
    </body>
</html>
