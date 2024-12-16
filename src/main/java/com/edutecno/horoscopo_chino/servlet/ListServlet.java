package com.edutecno.horoscopo_chino.servlet;

import com.edutecno.horoscopo_chino.dto.UsuarioResponseDTO;
import com.edutecno.horoscopo_chino.service.UsuarioService;
import com.edutecno.horoscopo_chino.service.UsuarioServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "listServlet", value = "/usuarios")
public class ListServlet extends HttpServlet {
    private UsuarioService usuarioService;

    public ListServlet() {
        this.usuarioService = new UsuarioServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UsuarioResponseDTO> usuarios = usuarioService.findAll();
        req.setAttribute("usuarios", usuarios);
        req.getRequestDispatcher("listarUsuarios.jsp").forward(req, resp);
    }
}
