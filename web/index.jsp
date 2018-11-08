<%-- 
    Document   : index
    Created on : 05-nov-2018, 15:41:13
    Author     : KeylorSk8
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio-DEAS UTN</title>
        <script src="Templates/vendor/jquery/jquery.min.js"></script>
        <script src="Templates/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <%@include file="Templates/componentes/head.html" %>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">
    </head>
    <body>
        <%@include file="Templates/componentes/nav.html" %>
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
                            <h2 class="display-4">Inicio de Sesion</h2>
                            <table class="table">
                                <tr>
                                    <td>
                                        <label>Usuario: </label>
                                    </td>
                                    <td>
                                        <input type="text" class="form form-control">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Contraseña: </label>
                                    </td>
                                    <td>
                                        <input type="password" class="form form-control">
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" style="text-align: center"> 
                                        <button class="btn" style="background-color: #0d2d6d;color: white">Iniciar</button>
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
