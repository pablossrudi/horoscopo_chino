package com.edutecno.horoscopo_chino.servlet;

import com.edutecno.horoscopo_chino.dto.UsuarioCreateDTO;
import com.edutecno.horoscopo_chino.dto.UsuarioResponseDTO;
import com.edutecno.horoscopo_chino.service.UsuarioService;
import com.edutecno.horoscopo_chino.service.UsuarioServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "EditServlet", value = "/edit")
public class EditServlet extends HttpServlet {
    private final UsuarioService usuarioService;

    public EditServlet() {
        this.usuarioService = new UsuarioServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        Date fechaNacimiento = Date.valueOf(req.getParameter("fechaNacimiento"));

        UsuarioCreateDTO usuario = new UsuarioCreateDTO(
                nombre,
                username,
                email,
                fechaNacimiento,
                null
        );

        HttpSession session = req.getSession();
        UsuarioResponseDTO user = (UsuarioResponseDTO) session.getAttribute("usuarioActual");

        if (usuarioService.actualizarUsuario(usuario, user)) {
            session.setAttribute("usuarioActual", usuarioService.buscarPorUsername(usuario.getUsername()));
            session.setAttribute("usuarioEditado", "Se editó el usuario correctamente");
            resp.sendRedirect("profile.jsp");
        }else {
            if (nombre.isEmpty() || username.isEmpty() || email.isEmpty() || fechaNacimiento == null) {
                session.setAttribute("usuarioEmpty", "No pueden existir campos vacios");
            }else {
                session.setAttribute("errorUsuarioEditado", "Nombre de usuario o Email no se encuentran disponibles");
            }

            resp.sendRedirect("editarUsuario.jsp");
        }
    }
}
