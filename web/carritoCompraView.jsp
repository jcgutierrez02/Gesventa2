<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrito Compra</title>
        <link rel='stylesheet' type='text/css' href='css/estilos.css' /> 
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <%   
        request.setCharacterEncoding("UTF-8");
        ArrayList <Producto> carrito = (ArrayList <Producto>) request.getAttribute("carrito");
    %>
    <body>
        <jsp:include page="header.jsp"></jsp:include>    
        <jsp:include page="navegacion.jsp"></jsp:include> 
        
        <div class="row" id="principal">  
          
            <article class="col-xs-12 col-sm-8">
                
                <div class="panel panel-success"> 
                    <div class="panel-heading">
                        <h4>Carrito  
                           <span style="float:right;">Conectado como <strong><%= session.getAttribute("user") %>
                           </strong></span>                         
                        </h4>   
                    </div>  
                </div>  
               
              <%
               if ( carrito != null )  {
              %> 
                <div style="width: 600px; margin-left: 50px; ">
                    <a href="ListarProductos"><img style="float: right; " 
                                               src="img/carrito.png" width="5%" height="5%" title="Elegir otro"/></a>
                    <hr>
                    <form id="mform" action="GenerarPedido" method="post">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Imagen</th>
                                    <th>Nombre</th>
                                    <th>Precio</th>
                                    <th>Unidades</th>
                                    <th></th>
                                    <th>En stock</th>
                                </tr>
                            </thead>
                            <tbody>
                  <%
                        Double total = 0.0;
                        String total2 = new String();
                        for ( Producto prod : carrito ) {                            
                  %>
                            <tr>
                                <td><input class="form-control" type="text" name="cod" value="<%= prod.getCod() %>" readonly /></td>
                                <td>
                                <img src="<%= request.getContextPath()+"/img/"+prod.getImagen() %>"
                                     width="60%" height="10%" /></td>
                                <td class="col-md-8"><input class="form-control" type="text" name="nom" value="<%= prod.getNombre() %>" readonly /></td>
                                <td class="col-md-9"><input class="form-control" type="text" name="precio" value="<%= prod.getPrecio() %>" readonly /></td>
                                <td class="col-md-3">
                                    <input class="form-control" type="number" name="cant" value="1" min="1" onclick="ImporteTotal()"/>
                                </td>
                                <td><a href="CarritoCompra?cod=<%= prod.getCod() %>&tipo=2">Eliminar</a></td>
                                <td class="col-md-9"><input class="form-control" type="text" name="stock" value="<%= prod.getStock() %>" readonly /></td>
                            </tr>                                             
                        <%
                            DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
                            separadoresPersonalizados.setDecimalSeparator('.');
                            DecimalFormat formato1 = new DecimalFormat("#.00", separadoresPersonalizados);
                            total += prod.getPrecio();
                            total2 = formato1.format(total);
                        }
                        %>
                            </tbody>
                        </table>
                             
                        <h3 id="total">Total: <%= total2 %>€</h3>  
                        <h3 id="total2"></h3>  
                        <button type="submit" class="btn btn-primary" value="pedido">
                            <img style="float:left;" src="img/carrito.png" width="5%"/>Tramitar pedido</button>    
                    </form>
                    
                </div>            
              <%
                }
              %>
                            
            </article>
              
        </div>    
              
        <jsp:include page="footer.jsp"></jsp:include>  
        
        <script>
           /* Función que actualiza el importe total de la compra */ 
           function ImporteTotal() {            
              var Total = 0.0;
              for (i=0; i<document.getElementById("mform").elements.namedItem("precio").length; i++) {
                  var precio = document.getElementById("mform").elements.namedItem("precio")[i].value;
                  var cantidad = document.getElementById("mform").elements.namedItem("cant")[i].value;
                //  var stock = document.getElementById("mform").elements.namedItem("stock")[i].value;
                  Total += parseFloat(precio) * parseFloat(cantidad);
              }
              document.getElementById("total").innerHTML = "Total: " + Total.toFixed(2) + "€";      
           } 
        </script>    
        
    </body>
</html>