<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel='stylesheet' type='text/css' href='css/estilos.css' />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>    
        <jsp:include page="navegacion.jsp"></jsp:include> 

        <div class="panel-body">    
          <br/>  
          <form class="form-horizontal" action="Validar" method="post">  
            <div class="form-group">
               <label for="usuario" class="col-xs-2 control-label"> Usuario:</label>
               <div class="col-xs-2">
                    <input type="text" class="form-control" name="user" id="usuario" 
                                          placeholder="Usuario"/>  
               </div>
            </div>    
            <div class="form-group">
               <label for="clave" class="col-xs-2 control-label"> Contraseña:</label>
               <div class="col-xs-2">
                    <input type="password" class="form-control" name="pass" id="clave" 
                        placeholder="Contraseña"/>
               </div>
            </div>                 
            <br/>
            <div class="col-lg-offset-2 col-lg-10">
               <button type="submit" class="btn btn-primary" name="Enviar" value="Enviar">Entrar</button>
            </div>      
            <br/>
          </form>
          <br/>
        </div>  
        
        <jsp:include page="footer.jsp"></jsp:include>  
    </body>
</html>
