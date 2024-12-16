<%@ page import="java.util.List" %>
<%@ page import="com.edutecno.horoscopo_chino.dto.UsuarioResponseDTO " %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    UsuarioResponseDTO usuarioActual = (UsuarioResponseDTO) session.getAttribute("usuarioActual");

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
                    <h2 class="card-title text-center mb-4">¿Qué deseas hacer, <%= usuarioActual.getUsername()%>?</h2>
                        <div class="d-grid gap-2">
                            <a href="perfil" class="btn btn-primary my-2" type="submit">Conoce tu animal</a>
                            <a href="usuarios" class="btn btn-primary my-2" type="submit">Buscar Usuarios</a>
                            <a href="editarUsuario.jsp" class="btn btn-primary my-2" type="submit">Modificar datos</a>
                            <button class="btn btn-primary my-2" type="button" data-bs-toggle="modal" data-bs-target="#confirmDeleteModal">
                                Eliminar cuenta
                            </button>
                        </div>
                    <div class="d-grid gap-2 mt-4">
                        <a href="logout" class="btn btn-danger">Cerrar sesión</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal de confirmación -->
<div class="modal fade" id="confirmDeleteModal" tabindex="-1" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmDeleteModalLabel">Confirmar eliminación de cuenta</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                ¿Estás seguro de que deseas eliminar tu cuenta? Esta acción es irreversible.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <form action="eliminate" method="post">
                    <button type="submit" class="btn btn-danger">Eliminar cuenta</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>