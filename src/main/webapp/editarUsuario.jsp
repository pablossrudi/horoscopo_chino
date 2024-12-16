<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  if (session.getAttribute("usuarioActual") == null) {
    response.sendRedirect("index.jsp");
    return;
  }
  String mensajeError1 = (String) session.getAttribute("usuarioEmpty");
  session.removeAttribute("usuarioEmpty");

  String mensajeError2 = (String) session.getAttribute("errorUsuarioEditado");
  session.removeAttribute("errorUsuarioEditado");

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Editar Usuario</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <div class="row justify-content-center mt-5">
    <div class="col-md-6">
      <div class="card">
        <div class="card-body">
          <h2 class="card-title text-center mb-4">Editar usuario</h2>
          <!-- Mostrar mensaje de usuario empty -->
          <% if (mensajeError1 != null) { %>
          <div class="alert alert-danger" role="alert">
            <%= mensajeError1 %>
          </div>
          <% } %>
          <!-- Mostrar mensaje de error usuario editado -->
          <% if (mensajeError2 != null) { %>
          <div class="alert alert-danger" role="alert">
            <%= mensajeError2 %>
          </div>
          <% } %>
          <form action="edit" method="post">
            <div class="mb-3">
              <label for="username" class="form-label">Nombre de Usuario</label>
              <input type="text" class="form-control" id="username" name="username" value="${usuarioActual.username}">
            </div>
            <div class="mb-3">
              <label for="nombre" class="form-label">Nombre</label>
              <input type="text" class="form-control" id="nombre" name="nombre" value="${usuarioActual.nombre}">
            </div>
            <div class="mb-3">
              <label for="email" class="form-label">Email</label>
              <input type="text" class="form-control" id="email" name="email" value="${usuarioActual.email}">
            </div>
            <div class="mb-3">
              <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento</label>
              <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" value="${usuarioActual.fechaNacimiento}">
            </div>
            <div class="d-grid gap-2 mt-4">
              <button type="submit" class="btn btn-primary">Actualizar</button>
            </div>
            <div class="d-grid gap-2 mt-3">
              <a href="bienvenida.jsp" class="btn btn-danger">Cancelar</a>
            </div>
            <div class="text-center mt-3">
              <p>¿Quieres cambiar tu contraseña? <a href="cambiarContrasena.jsp" class="text-decoration-none">Cámbiala aquí</a></p>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>