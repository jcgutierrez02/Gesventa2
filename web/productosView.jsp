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
        Integer valor = (Integer) request.getAttribute("valor");
        Integer categoria = (Integer) request.getAttribute("categoria");
    %>
    <body>
        <jsp:include page="header.jsp"></jsp:include>       
        <jsp:include page="navegacion.jsp"></jsp:include>
     
        <div class="row" id="principal">  
           
            <article class="col-xs-12 col-sm-8">
                <div class="panel panel-success"> 
                    <div class="panel-heading">
                        <h3 class="panel-title"><strong>Productos</strong>
                            <span style="float:right;">Conectado como <strong><%= session.getAttribute("registro") %>
                                </strong></span>
                        </h3>
                    </div>
                </div>   
                <div class="row">
                    <form class="form-horizontal col-md-6" role="form" action="ListarProductos"
                                                    style="margin-left: 10px;" method="post">
                        <input type="hidden" name="cat" value="<%= categoria %>" />        
                        <div class="form-group">
                            <label for="ordenar" class="col-md-3 control-label">Ordenar por:</label>
                            <div class="col-md-4">
                                <select name="valor">
                                    <% if ( valor == 0 || valor == 1) { %>
                                        <option value="1" selected>Top Ventas</option>
                                    <% } else { %>
                                       <option value="1">Top Ventas</option>
                                    <% } %>    
                                    <% if ( valor == 2) { %>
                                        <option value="2" selected>Precio ascendente</option>
                                    <% } else { %>
                                       <option value="2">Precio ascendente</option>   
                                    <% } %> 
                                    <% if ( valor == 3) { %>
                                        <option value="3" selected>Precio descendente</option>
                                    <% } else { %>
                                        <option value="3">Precio descendente</option>  
                                    <% } %>   
                                </select>
                            </div>                      
                            <button type="submit" class="btn btn-primary" name="Enviar" value="Enviar">Enviar</button>
                        </div>
                    </form>
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
                                <img src="<%= request.getContextPath()+"/img/"+prod.getImagen() %>"
                                     width="80%" height="80%" />
                            <br/>
                            <div style="margin-top: 10px;">
                                <span class="negrilla"><%= prod.getPrecio() %> €</span> 
                                <a href="CarritoCompra?cod=<%= prod.getCod() %>">
                                    <img style="float:right;" src="img/carrito.png" width="10%" 
                                        title="Añadir a Carrito"  />
                                </a>   
                            </div>                         
                        </div>                           
              <%
                    }
                }
              %>
            </article>
        </div>    
              
        <jsp:include page="footer.jsp"></jsp:include>  
    </body>
</html>
