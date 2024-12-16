package com.edutecno.horoscopo_chino.servlet;

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

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private final UsuarioService usuarioService;

    public LoginServlet() {
        this.usuarioService = new UsuarioServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("usuario");
        String password = req.getParameter("contrasena");

        if (usuarioService.autenticarUsuario(username, password)) {
            UsuarioResponseDTO usuarioAutenticado = usuarioService.buscarPorUsername(username);

            HttpSession session = req.getSession();

            session.setAttribute("usuario", usuarioAutenticado.getUsername());
            session.setAttribute("usuarioActual", usuarioAutenticado);

            resp.sendRedirect("bienvenida.jsp");
        }else {
            resp.sendRedirect("index.jsp?error=1");
        }
    }
}
