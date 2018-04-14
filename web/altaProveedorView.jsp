<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Proveedor"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alta Proveedor</title>
        <link rel='stylesheet' type='text/css' href='css/estilos.css' /> 
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>     
    </head>
    
     <%   
        request.setCharacterEncoding("UTF-8");
        Proveedor proveedor = (Proveedor) request.getAttribute("proveedor");
     %>
    
    <body>
        <jsp:include page="header.jsp"></jsp:include>
     
        <jsp:include page="navegacion.jsp"></jsp:include> 
        
        <div class="row" id="principal">  
            <jsp:include page="menu.jsp"></jsp:include>
            <article class="col-xs-12 col-sm-8">
                <div class="panel panel-success">    
                    <div class="panel-heading">
                        <h3 class="panel-title"><strong>Insertar datos de proveedor.</strong>
                            <span style="float:right;">Conectado como <strong><%= session.getAttribute("user") %>
                                </strong></span>
                        </h3>
                    </div>  
                </div>    
                <br/>  
                <form class="form-horizontal" role="form" action="Alta" method="post">
            
                    <div class="form-group">
                        <label for="cif" class="col-xs-2 control-label"> Cif:</label>
                        <div class="col-xs-2">
                          <input type="text" class="form-control" name="cif" id="cif" 
                             placeholder="Cif"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="nom_emp" class="col-xs-2 control-label"> Nombre:</label>
                        <div class="col-xs-3">
                          <input type="text" class="form-control" name="nom_emp" id="nom_emp" 
                             
                             placeholder="Nombre Proveedor"/>
                          
                        </div>
                    </div>                 
                    <div class="form-group">
                        <label for="direccion" class="col-xs-2 control-label"> Dirección:</label>
                        <div class="col-xs-5">
                            <input type="text" class="form-control" name="direccion" id="direccion" 
                             
                             placeholder="Dirección Proveedor"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="poblacion" class="col-xs-2 control-label"> Población:</label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" name="poblacion" id="poblacion" 
                             
                             placeholder="Población Proveedor"/>
                        </div>
                    </div>
            
                    <div class="col-lg-offset-2 col-lg-8">
                        <button type="submit" class="btn btn-primary" name="Enviar" value="Enviar">Insertar</button>
                        <button type="reset" class="btn btn-primary" name="Limpiar" value="Limpiar">Limpiar</button>
                    </div>
                    <br/>       
                </form>
        
       </article>
        </div>    
        <jsp:include page="footer.jsp"></jsp:include>  
    </body>
</html>
</html>
