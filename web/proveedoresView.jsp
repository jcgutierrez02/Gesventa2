<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Proveedor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Proveedores</title>
        <link rel='stylesheet' type='text/css' href='css/estilos.css' /> 
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <%   
        request.setCharacterEncoding("UTF-8");
        ArrayList <Proveedor> proveedores = (ArrayList <Proveedor>) request.getAttribute("proveedores");
    %>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <jsp:include page="navegacion.jsp"></jsp:include>
       
        <div class="row" id="principal">  
            
            <article class="col-xs-12 col-sm-8">
                    <div class="panel panel-success"> 
                        <div class="panel-heading">
                            <h2 class="panel-title"><strong>Listado de Proveedores</strong>
                               <span style="float:right;">Conectado como <strong><%= session.getAttribute("user") %>
                                </strong></span>
                            </h2>    
                        </div>
                    </div>    
            <%
               if ( proveedores != null )  {
            %>    
                <hr>
                <table class="table table-striped">
                    <tr>
                        <th>Cif</th>
                        <th>Empresa</th>
                        <th>Dirección</th>
                        <th>Población</th>
                    </tr>
              <%
                    for ( Proveedor prove : proveedores ) {                      
              %>
                        <tr>
                            <td><%= prove.getCif() %></td>
                            <td><%= prove.getNom_emp() %></td>
                            <td><%= prove.getDireccion() %></td>
                            <td><%= prove.getPoblacion() %></td>
                        </tr>
              <%
                    }
                }
              %>
              </table>
            </article>
        </div>    
        <jsp:include page="footer.jsp"></jsp:include>  
    </body>
</html>
