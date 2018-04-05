<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Producto"%>
<%@page import="modelo.Registro"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificación Producto</title>
        <link rel='stylesheet' type='text/css' href='css/estilos.css' /> 
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    
    <%   
        request.setCharacterEncoding("UTF-8");   
        Registro reg = (Registro) request.getAttribute("registro");
        String nom_usu="", nom_apell="", usuario="", clave="";

        if ( reg != null ) {
            nom_usu = reg.getNom_usu();
            nom_apell = reg.getNom_apell();
            usuario = reg.getUsuario();
            clave = reg.getClave();
        }

    %>
    
    <body>
        <jsp:include page="header.jsp"></jsp:include>    
        <jsp:include page="navegacion.jsp"></jsp:include>
         
        <div id="principal">  
            
            <article>
                <div class="panel panel-success">    
                    <div class="panel-heading">
                        <h3 class="panel-title"><strong>Introduzca datos.</strong></h3>
                    </div>  
                </div>    
                <br/>  
                <div class="row" >
                    <form class="form-horizontal col-md-6" role="form" action="RegistrarUsuario"
                          style="margin-left: 10px;"
                          method="post" enctype="multipart/form-data">

                        <div class="form-group">
                            <label for="nom_usu" class="col-md-2 control-label"> Nombre:</label>
                            <div class="col-md-9">
                              <input type="text" class="form-control" name="nom_usu" id="nom_usu"     
                                 value="<%= nom_usu %>"    
                                 placeholder="Nombre"/>
                            </div>                      
                        </div>        
                        <div class="form-group">
                            <label for="nom_apell" class="col-md-2 control-label"> Apellido:</label>
                            <div class="col-md-9">
                              <input type="text" class="form-control" name="nom_apell" id="nom_apell"  
                                 value="<%= nom_apell %>"    
                                 placeholder="Apellido(s)"/>
                            </div>                      
                        </div>        
                        <div class="form-group">
                            <label for="usuario" class="col-md-2 control-label"> Usuario:</label>
                            <div class="col-md-9">
                                <input type="email" class="form-control" name="usuario" id="usuario"     
                                  value="<%= usuario %>"     
                                  placeholder="Dirección e-mail válida"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="clave" class="col-md-2 control-label"> Contraseña:</label>
                            <div class="col-md-4">
                                <input type="password" class="form-control" name="clave" id="clave"  
                                  value="<%= clave %>"     
                                  placeholder="Contraseña"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="clave2" class="col-md-2 control-label"> Repita Contraseña:</label>
                            <div class="col-md-4">
                                <input type="password" class="form-control" name="clave2" id="clave2"       
                                  placeholder="Contraseña"/>
                            </div>
                        </div>
                      
                        <br/>     

                        <div class="col-md-offset-2 col-md-8">
                            <button type="submit" class="btn btn-primary" name="Enviar" value="Enviar">Registrar</button>
                            <button type="reset" class="btn btn-primary" name="Limpiar" value="Limpiar">Limpiar</button>
                        </div>
                        <br/>       
                    </form>
                        
                </div>             
                                
            </article>
        </div>    
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
