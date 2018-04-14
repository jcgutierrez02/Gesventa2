<%@page import="java.io.FileInputStream"%>
<%@page contentType="application/pdf" pageEncoding="UTF-8"%>

<%
    request.setCharacterEncoding("UTF-8");
    String archivoPdf = (String) request.getAttribute("archivo");
    FileInputStream ficheroInput = new FileInputStream("C:\\upload\\fichero.pdf");

    int tamanoInput = ficheroInput.available();

    byte[] datosPDF = new byte[tamanoInput];
    ficheroInput.read( datosPDF, 0, tamanoInput);

    response.setHeader("Content-disposition","inline; filename=instalacion_tomcat.pdf" );
    response.setContentType("application/pdf");
    response.setContentLength(tamanoInput);
    response.getOutputStream().write(datosPDF);

    ficheroInput.close();
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedido</title>
        <link rel='stylesheet' type='text/css' href='css/estilos.css' /> 
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    
    <body>
        <jsp:include page="header.jsp"></jsp:include>  
        <jsp:include page="navegacion.jsp"></jsp:include>  
               
        <div class="row" id="principal">  
            
            <article class="col-xs-12 col-sm-8">
              
            </article>
        </div>                 
        <jsp:include page="footer.jsp"></jsp:include>  
    </body>
</html>
