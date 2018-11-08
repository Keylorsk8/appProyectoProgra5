<%-- 
    Document   : menuPrincipal
    Created on : 05-nov-2018, 18:29:33
    Author     : KeylorSk8
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="Templates/vendor/jquery/jquery.min.js"></script>
        <script src="Templates/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <%@include file="Templates/componentes/head.html" %>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">
        <title>Menu Principal - DEAS UTN</title>
    </head>
    <body>
        <%@include file="Templates/componentes/navMenuPrincipal.html" %>
        <div class="container" style="margin: 10%">
            <div class="row">
                <div class="col-md-12">
                    <h1>Accesos Rapidos</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6" style=";padding: 5%">
                    <div class="container" style="text-align: center">
                        <a href="#" style="text-decoration: none"><i class="fa fa-book" style="font-size: 100px"></i><h5 style="display: block;padding: 4%"">Solicitar Reservacion</h5></a>
                    </div>
                </div>
                <div class="col-md-6" style=";padding: 5%">
                    <div class="container" style="text-align: center">
                        <a href="#" style="text-decoration: none"><i class="fa fa-user-circle" style="font-size: 100px"></i><h5 style="display: block;padding: 4%">Modificar Perfil</h5></a>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="Templates/componentes/footer.html" %>
    </body>
</html>
