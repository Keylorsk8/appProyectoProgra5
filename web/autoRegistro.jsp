<%-- 
    Document   : autoRegistro
    Created on : 05-nov-2018, 17:51:44
    Author     : KeylorSk8
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autoregistro DEAS UTN</title>
        <script src="Templates/vendor/jquery/jquery.min.js"></script>
        <script src="Templates/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <%@include file="Templates/componentes/head.html" %>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark navbar-custom fixed-top" style="margin-bottom: 10%">
            <div class="container">
                <a class="navbar-brand" href="index.jsp"><i class="fa fa-tree"></i>DEAS UTN</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="index.jsp">Iniciar Sesion</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <section style="padding: 5%;margin-top: 26px;">
            <div class="container" id="inicioSesion">
                <div class="row align-items-center">
                    <div class="col-lg-6 order-lg-2">
                        <div class="p-5">
                            <img class="img-fluid" src="source/utn.jpg" alt="">
                        </div>
                    </div>
                    <div class="col-lg-6 order-lg-1">
                        <div class="p-5">
                            <h2 class="display-4">Auto Registro</h2>
                            <table class="table">
                                <tr>
                                    <td>
                                        <label>Cedula: </label>
                                    </td>
                                    <td>
                                        <input type="text" class="form form-control">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Email: </label>
                                    </td>
                                    <td>
                                        <input type="email" class="form form-control">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Nombre: </label>
                                    </td>
                                    <td>
                                        <input type="text" class="form form-control">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Primer Apellido: </label>
                                    </td>
                                    <td>
                                        <input type="text" class="form form-control">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Segundo Apellido: </label>
                                    </td>
                                    <td>
                                        <input type="text" class="form form-control">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Contrasena: </label>
                                    </td>
                                    <td>
                                        <input type="text" class="form form-control">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Repetir contrasena: </label>
                                    </td>
                                    <td>
                                        <input type="text" class="form form-control">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Fecha de Nacimiento: </label>
                                    </td>
                                    <td>
                                        <input type="date" class="form form-control" class="form form-control">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Provincia: </label>
                                    </td>
                                    <td>
                                        <select class="form form-control">
                                            <option>1</option>
                                            <option>2</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Canton: </label>
                                    </td>
                                    <td>
                                        <select class="form form-control">
                                            <option>1</option>
                                            <option>2</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Distrito: </label>
                                    </td>
                                    <td>
                                        <select class="form form-control">
                                            <option>1</option>
                                            <option>2</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Otras señas: </label>
                                    </td>
                                    <td>
                                        <input type="text" class="form form-control">
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" style="text-align: center"> 
                                        <button class="btn" style="background-color: #0d2d6d;color: white">Solicitar Cuenta</button>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="Templates/componentes/footer.html" %>
    </body>
</html>
