<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  if (session.getAttribute("usuarioActual") == null) {
    response.sendRedirect("index.jsp");
    return;
  }
  String mensajeError1 = (String) session.getAttribute("contrasenaEmpty");
  session.removeAttribute("contrasenaEmpty");

  String mensajeError2 = (String) session.getAttribute("ErrorContrasena");
  session.removeAttribute("ErrorContrasena");

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Editar contraseña</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <div class="row justify-content-center mt-5">
    <div class="col-md-6">
      <div class="card">
        <div class="card-body">
          <h2 class="card-title text-center mb-4">Editar contraseña</h2>
          <!-- Mostrar mensaje de éxito -->
          <% if (mensajeError1 != null) { %>
          <div class="alert alert-danger" role="alert">
            <%= mensajeError1 %>
          </div>
          <% } %>
          <% if (mensajeError2 != null) { %>
          <div class="alert alert-danger" role="alert">
            <%= mensajeError2 %>
          </div>
          <% } %>
          <form action="editPass" method="post">
            <div class="mb-3">
              <label for="currentPassword" class="form-label">Contraseña actual</label>
              <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
            </div>
            <div class="mb-3">
              <label for="password" class="form-label">Contraseña nueva</label>
              <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="mb-3">
              <label for="confirmPassword" class="form-label">Confirmar Contraseña nueva</label>
              <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
            </div>
            <div class="d-grid gap-2 mt-4">
              <button type="submit" class="btn btn-primary">Actualizar</button>
            </div>
            <div class="d-grid gap-2 mt-3">
              <a href="bienvenida.jsp" class="btn btn-danger">Cancelar</a>
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