<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">WebSiteName</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="homeView.jsp">Home</a></li>   
            <li><a href="#">Contacto</a></li>   
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Productos
                <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="ListarProductos">Listar Productos</a></li>
                    <li><a href="ListarProductos?cat=1">Astronomía</a></li>
                    <li><a href="ListarProductos?cat=2">Informática</a></li>
                    <li><a href="ListarProductos?cat=3">Telefonía móvil</a></li>                  
                    <li><a href="ListarProductos?cat=4">Videojuegos</a></li>
                    <li><a href="ListarProductos?cat=5">Juguetes articulados</a></li>
                </ul>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Mi Cuenta
                <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="registroView.jsp"><span class="glyphicon glyphicon-user"></span> Registrarse</a></li>
                    <li><a href="login2.jsp"><span class="glyphicon glyphicon-log-in"></span> Entrar</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Privado
                <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="login.jsp"><span class="glyphicon glyphicon-user"></span> Login</a></li>
                    <li><a href="ListarProductosAdmin">Listar Productos</a></li>
                    <li><a href="AltaProductos">Alta Productos</a></li>
                    <li><a href="ListarProductosAdmin">Modificar Productos</a></li>
                    <li><a href="ListarProductosAdmin">Eliminar Productos</a></li>
                    <li class="divider"></li> 
                    <li><a href="ListarProveedores">Proveedores</a></li>
                    <li><a href="AltaProveedores">Alta Proveedores</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Salir
                <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="homeView.jsp">
                        <img src="img/inicio.png" width="20%" title="Inicio" /> Inicio</a>
                    </li>
                    <li><a href="CerrarSesion">
                        <img src="img/cerrarsesion.png" width="25%" title="Cerrar Sesión" /> Cerrar Sesión</a>
                    </li>
                </ul>  
            </li>  
        </ul>
      
    </div>
</nav>  
    