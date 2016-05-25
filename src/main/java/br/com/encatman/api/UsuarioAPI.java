package br.com.encatman.api;

import br.com.encatman.entidades.Usuario;
import br.com.encatman.negocio.UsuarioNegocio;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author munif
 */
@WebServlet(name = "UsuarioAPI", urlPatterns = {"/api/usuario/*"})
public class UsuarioAPI extends HttpServlet {
    
    private ObjectMapper mapper;
    private UsuarioNegocio usuarioNegocio;
    
    public UsuarioAPI(){
        mapper=new ObjectMapper();
        usuarioNegocio=new UsuarioNegocio();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Usuario> lista=usuarioNegocio.listar();
        response.getOutputStream().write(mapper.writeValueAsBytes(lista));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getOutputStream().write("Fez um POST".getBytes());
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getOutputStream().write("Fez um PUT".getBytes());
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getOutputStream().write("Fez um DELETE".getBytes());
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Usuario API";
    }// </editor-fold>

}
