package com.edutecno.horoscopo_chino.servlet;

import com.edutecno.horoscopo_chino.dto.UsuarioCreateDTO;
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

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    private final UsuarioService usuarioService;

    public RegisterServlet() {
        this.usuarioService = new UsuarioServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        Date fechaNacimiento = Date.valueOf(req.getParameter("fechaNacimiento"));
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        UsuarioCreateDTO usuario = new UsuarioCreateDTO(
                nombre,
                username,
                email,
                fechaNacimiento,
                password,
                null
        );

        if (usuarioService.registrarUsuario(usuario, confirmPassword)){
            HttpSession session = req.getSession();
            session.setAttribute("usuarioRegistrado", "Se registró el usuario correctamente");
            resp.sendRedirect("index.jsp");
        }else {
            resp.sendRedirect("register.jsp?error=1");
        }

    }
}
