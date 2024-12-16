<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.edutecno.horoscopo_chino.dto.UsuarioResponseDTO" %>
<%
    if (session.getAttribute("usuarioActual") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    List<UsuarioResponseDTO> usuarios = (List<UsuarioResponseDTO>) request.getAttribute("usuarios");

    // Obtener los parámetros de búsqueda, si existen
    String searchQuery = request.getParameter("searchQuery");

    // Filtrar los usuarios según el nombre o username
    if (searchQuery != null && !searchQuery.trim().isEmpty()) {
        usuarios = usuarios.stream()
                .filter(u -> u.getNombre().toLowerCase().contains(searchQuery.toLowerCase()) ||
                        u.getUsername().toLowerCase().contains(searchQuery.toLowerCase()))
                .toList();
    }

    // Número de elementos por página
    int elementosPorPagina = 6;

    // Número total de páginas
    int totalUsuarios = usuarios.size();
    int totalPaginas = (int) Math.ceil((double) totalUsuarios / elementosPorPagina);

    // Obtención del número de página actual desde la petición (por defecto, es la página 1)
    int paginaActual = request.getParameter("pagina") != null ? Integer.parseInt(request.getParameter("pagina")) : 1;

    // Validación para evitar que la página actual sea menor a 1 o mayor al total de páginas
    if (paginaActual < 1) {
        paginaActual = 1;
    }
    if (paginaActual > totalPaginas) {
        paginaActual = totalPaginas;
    }

    // Cálculo del índice de inicio y final para la sublista
    int inicio = (paginaActual - 1) * elementosPorPagina;
    int fin = Math.min(inicio + elementosPorPagina, totalUsuarios);

    // Sublista con los elementos de la página actual
    List<UsuarioResponseDTO> usuariosPaginaActual = usuarios.subList(inicio, fin);
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Usuarios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center mt-5">
        <div class="col-md-8">
            <div class="card">
                <div class="card-body">
                    <h2 class="card-title text-center mb-4">Lista de Usuarios</h2>
                    <!-- Formulario de búsqueda -->
                    <form method="get" action="" class="mb-4">
                        <div class="input-group">
                            <input type="text" class="form-control" name="searchQuery" placeholder="Buscar por nombre o username" value="<%= searchQuery != null ? searchQuery : "" %>">
                            <button class="btn btn-outline-secondary" type="submit">Buscar</button>
                        </div>
                    </form>
                    <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Username</th>
                                <th>Email</th>
                                <th>Fecha de nacimiento</th>
                                <th>Animal</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                if (usuariosPaginaActual != null) {
                                    for (UsuarioResponseDTO usuario : usuariosPaginaActual) {
                            %>
                            <tr>
                                <td><%= usuario.getNombre() %></td>
                                <td><%= usuario.getUsername() %></td>
                                <td><%= usuario.getEmail() %></td>
                                <td><%= usuario.getFechaNacimiento() %></td>
                                <td><%= usuario.getAnimal() %></td>
                            </tr>
                            <%
                                }
                                    }
                            %>
                        </tbody>
                    </table>
                    </div>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-end">
                            <%
                                // Botón de primera página
                                if (paginaActual > 1) {
                            %>
                            <li class="page-item">
                                <a class="page-link" href="?pagina=1">Primera</a>
                            </li>
                            <%
                                }

                                // Botón de página anterior
                                if (paginaActual > 1) {
                            %>
                            <li class="page-item">
                                <a class="page-link" href="?pagina=<%= paginaActual - 1 %>"><strong> < </strong></a>
                            </li>
                            <%
                                }

                                // Páginas intermedias
                                for (int i = 1; i <= totalPaginas; i++) {
                                    if (i == paginaActual) {
                            %>
                            <li class="page-item active">
                                <a class="page-link" href="?pagina=<%= i %>"><%= i %></a>
                            </li>
                            <%
                            } else {
                            %>
                            <li class="page-item">
                                <a class="page-link" href="?pagina=<%= i %>"><%= i %></a>
                            </li>
                            <%
                                    }
                                }
                                // Botón de página siguiente
                                if (paginaActual < totalPaginas) {
                            %>
                            <li class="page-item">
                                <a class="page-link" href="?pagina=<%= paginaActual + 1 %>"> <strong> > </strong> </a>
                            </li>
                            <%
                                }

                                // Botón de última página
                                if (paginaActual < totalPaginas) {
                            %>
                            <li class="page-item">
                                <a class="page-link" href="?pagina=<%= totalPaginas %>">Última</a>
                            </li>
                            <%
                                }
                            %>
                        </ul>
                    </nav>
                    <div class="d-grid gap-2 mt-4">
                        <a href="bienvenida.jsp" class="btn btn-danger">Volver a menu principal</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>