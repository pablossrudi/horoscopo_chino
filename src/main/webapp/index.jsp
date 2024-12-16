<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%

    String mensajeExito = (String) session.getAttribute("usuarioEliminado");
    session.removeAttribute("usuarioEliminado");

    String mensajeRegistro = (String) session.getAttribute("usuarioRegistrado");
    session.removeAttribute("usuarioRegistrado");

    String logout = (String) session.getAttribute("logout");
    session.removeAttribute("logout");

    String mensajeContrasena = (String) session.getAttribute("contrasenaEditado");
    session.removeAttribute("contrasenaEditado");

%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio Sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>

<body class="bg-light">
<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <h2 class="card-title text-center mb-4">Inicio Sesión</h2>
                    <!-- Mostrar mensaje de usuario eliminado -->
                    <% if (mensajeExito != null) { %>
                    <div class="alert alert-danger" role="alert">
                        <%= mensajeExito %>
                    </div>
                    <% } %>
                    <!-- Mostrar mensaje de registro exitoso -->
                    <% if (mensajeRegistro != null) { %>
                    <div class="alert alert-success" role="alert">
                        <%= mensajeRegistro %>
                    </div>
                    <% } %>
                    <!-- Mostrar mensaje de éxito de logaut -->
                    <% if (logout != null) { %>
                    <div class="alert alert-success" role="alert">
                        <%= logout %>
                    </div>
                    <% } %>
                    <!-- Mostrar mensaje de éxito al cambiar contraseña -->
                    <% if (mensajeContrasena != null) { %>
                    <div class="alert alert-success" role="alert">
                        <%= mensajeContrasena %>
                    </div>
                    <% } %>

                    <% if (request.getParameter("error") != null) { %>
                    <div class="alert alert-danger" role="alert">
                        Usuario o contraseña incorrectas
                    </div>
                    <% } %>

                    <form action="login" method="post">
                        <div class="mb-3">
                            <label for="usuario" class="form-label">Usuario</label>
                            <input type="text" class="form-control" id="usuario" name="usuario" required>
                        </div>
                        <div class="mb-3">
                            <label for="contrasena" class="form-label">Contraseña</label>
                            <input type="password" class="form-control" id="contrasena" name="contrasena" required>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Ingresar</button>
                        </div>
                        <div class="text-center mt-3">
                            <p>¿No tienes una cuenta? <a href="register.jsp" class="text-decoration-none">Registrate aquí</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>