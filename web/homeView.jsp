<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Página Inicial</title>
        <link rel='stylesheet' type='text/css' href='css/estilos.css' />
       <!-- <link href="css/bootstrap.css" rel="stylesheet" media="screen">
        <script src="js/bootstrap.js"></script> 
        <script src="js/jquery-3.2.1.min"></script> 
      -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>  
    </head>
    <body>

        <jsp:include page="header.jsp"></jsp:include>        
        <jsp:include page="navegacion.jsp"></jsp:include>

        <div class="row content" id="principal">  
                    
            <article class="col-xs-12 col-sm-8">                 
                <img style="margin-left: 15em;" src="img/tablets.jpg">                    
            </article>
        </div>    

        <jsp:include page="footer.jsp"></jsp:include>
  
    </body>
</html>
