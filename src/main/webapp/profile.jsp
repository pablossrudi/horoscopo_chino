<%@ page import="java.util.List" %>
<%@ page import="com.edutecno.horoscopo_chino.dto.UsuarioResponseDTO " %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("usuarioActual") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    UsuarioResponseDTO usuarioActual = (UsuarioResponseDTO) session.getAttribute("usuarioActual");

    String mensajeExito = (String) session.getAttribute("usuarioEditado");
    session.removeAttribute("usuarioEditado");

%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>bienvenida</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card mb-5">
                <div class="card-body">
                    <h2 class="card-title text-center mb-4">Tu animal: <%= usuarioActual.getAnimal() %></h2>
                    <!-- Mostrar mensaje de éxito -->
                    <% if (mensajeExito != null) { %>
                    <div class="alert alert-success" role="alert">
                        <%= mensajeExito %>
                    </div>
                    <% } %>
                    <!-- Información del usuario actual -->
                    <div class="card mb-4">
                        <div class="card-header">
                            Tu información
                        </div>
                        <div class="card-body">
                            <p><strong>Nombre:</strong> <%= usuarioActual.getNombre() %></p>
                            <p><strong>Username:</strong> <%= usuarioActual.getUsername() %></p>
                            <p><strong>Email:</strong> <%= usuarioActual.getEmail() %></p>
                            <p><strong>Fecha de nacimiento:</strong><%= usuarioActual.getFechaNacimiento()%></p>
                            <p><strong>Animal:</strong> <%= usuarioActual.getAnimal() %></p>
                            <div class="d-grid gap-2 mt-4">
                                <a href="bienvenida.jsp" class="btn btn-danger">Volver a menu principal</a>
                            </div>
                        </div>

                    </div>
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
