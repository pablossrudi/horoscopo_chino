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

@WebServlet(name = "DeleteServlet", value = "/eliminate")
public class DeleteServlet extends HttpServlet {
    private final UsuarioService usuarioService;

    public DeleteServlet() {
        this.usuarioService = new UsuarioServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UsuarioResponseDTO user = (UsuarioResponseDTO) session.getAttribute("usuarioActual");
        if (usuarioService.eliminarUsuario(user.getId())){
            session.removeAttribute("usuarioActual");
            session.setAttribute("usuarioEliminado", "Se eliminó el usuario correctamente");
            resp.sendRedirect("index.jsp");
        }else {
            resp.sendRedirect("profile.jsp");
        }
    }
}
