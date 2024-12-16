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

@WebServlet(name = "EditPassServlet", value = "/editPass")
public class EditPassServlet extends HttpServlet {
    private static UsuarioService usuarioService;

    public EditPassServlet() {
        this.usuarioService = new UsuarioServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPassword = req.getParameter("currentPassword");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        UsuarioCreateDTO usuario = new UsuarioCreateDTO(
                password
        );

        HttpSession session = req.getSession();
        UsuarioResponseDTO user = (UsuarioResponseDTO) session.getAttribute("usuarioActual");

        if (usuarioService.actualizarContrasena(usuario, user, confirmPassword, currentPassword)) {
            session.setAttribute("contrasenaEditado", "Se editó la contraseña correctamente");
            session.removeAttribute("usuarioActual");

            resp.sendRedirect("index.jsp");
        }else{
            if (currentPassword.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                session.setAttribute("contrasenaEmpty", "No pueden existir campos vacios");
            }else {
                session.setAttribute("ErrorContrasena", "Error en la contraseña");
            }
            resp.sendRedirect("cambiarContrasena.jsp");
        }
    }
}
