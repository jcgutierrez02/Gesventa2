<%@page import="java.awt.Image"%>
<%@page import="modelo.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alta Producto</title>
        <link rel='stylesheet' type='text/css' href='css/estilos.css' />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
     <%   
        request.setCharacterEncoding("UTF-8");  
        Producto prod = (Producto) request.getAttribute("producto");
        String nombre = "", imagen="", proveedor = "";
        Image imagen2 = null;
        Double precio = 0.;
        
        if ( prod != null ) {
            nombre = prod.getNombre();
            precio = prod.getPrecio();
            imagen = prod.getImagen();
            proveedor = prod.getProveedor();
            imagen2 = prod.getImagen2();
        }
     %>
    
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <jsp:include page="navegacion.jsp"></jsp:include>
     
        <div id="principal">  
            <jsp:include page="menu.jsp"></jsp:include>
            <article>
                <div class="panel panel-success">    
                    <div class="panel-heading">
                        <h3 class="panel-title"><strong>Insertar datos de producto.</strong>
                           <span style="float:right;">Conectado como <strong><%= session.getAttribute("user") %>
                                </strong></span>
                        </h3>
                    </div>  
                </div>    
                <br/>  
                <div class="row" >
                <form class="form-horizontal col-md-6" role="form" action="AltaProductos"
                      style="margin-left: 10px;"
                      method="post" enctype="multipart/form-data">
            
                    <div class="form-group">
                        <label for="nom_prod" class="col-md-2 control-label"> Nombre:</label>
                        <div class="col-md-9">
                          <input type="text" class="form-control" name="nom_prod" id="nom_prod" 
                             value="<%= nombre %>"    
                             placeholder="Nombre Producto"/>
                        </div>                      
                    </div>                 
             
                    <div class="form-group">
                        <label for="precio" class="col-md-2 control-label"> Precio:</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control" name="precio" id="precio"  
                              value="<%= precio %>"       
                              placeholder="Dos decimales"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="proveedor" class="col-md-2 control-label"> Proveedor:</label>
                        <div class="col-md-4">
                            <%= request.getAttribute("salida") %>
                        </div>
                    </div>
                        
                    <div class="form-group">
                        <label for="catalogo" class="col-md-2 control-label"> Catálogo:</label>
                        <div class="col-md-4">
                            <%= request.getAttribute("salida2") %>
                        </div>
                    </div>    
                        
                    <div class="form-group">
                        <label for="oferta" class="col-md-2 control-label"> Oferta:</label>
                        <div class="col-md-4">
                            Sí <input type="checkbox" name="oferta" value="1"/>
                            No <input type="checkbox" name="oferta" value="0"/>
                        </div>
                    </div>        
                                        
                    <div class="form-group">
                        <label for="imagen" class="col-md-2 control-label"> Imagen:</label>
                        <div class="col-md-2">
                            <input type="file" name="imagen" id="imagen" required />
                        </div>
                    </div>
                    <br/>     
                        
                    <div class="col-md-offset-2 col-md-8">
                        <button type="submit" class="btn btn-primary" name="Enviar" value="Enviar">Insertar</button>
                        <button type="reset" class="btn btn-primary" name="Limpiar" value="Limpiar">Limpiar</button>
                    </div>
                    <br/>       
                </form>
                        
                <div class="col-xs-2" >
                    <img id="caratula" width="150" height="150" />
                </div>   
                        
                </div>             
                        
                <script>
                    document.getElementById('imagen').onchange = function() {
                        var reader = new FileReader(); //instanciamos el objeto de la api FileReader
 
                        reader.onload = function(e) {
                            //en el evento onload del FileReader, asignamos el path a el src del elemento imagen de html
                            document.getElementById("caratula").src = e.target.result;
                        };
 
                        // carga el contenido del fichero imagen.
                        reader.readAsDataURL(this.files[0]);
                    };
                </script>       
              
            </article>
        </div>    
        <jsp:include page="footer.jsp"></jsp:include>  
    </body>
</html>
</html>
