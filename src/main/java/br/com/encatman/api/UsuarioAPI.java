package br.com.encatman.api;

import br.com.encatman.entidades.Usuario;
import br.com.encatman.negocio.UsuarioNegocio;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
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

    public UsuarioAPI() {
        mapper = new ObjectMapper();
        usuarioNegocio = new UsuarioNegocio();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String parametros[] = getUrlParameters(request);
        if (parametros.length == 0) {
            List<Usuario> lista = usuarioNegocio.listar();
            response.getOutputStream().write(mapper.writeValueAsBytes(lista));
        } else if (parametros.length == 1) {
            Long id = Long.parseLong(parametros[0]);
            Usuario usuario = usuarioNegocio.recuperar(id);
            response.getOutputStream().write(mapper.writeValueAsBytes(usuario));
        } else {
            response.setStatus(400);
            response.getOutputStream().write(mapper.writeValueAsBytes("Número de parâmetros errado."));
        }
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
        String parametros[] = getUrlParameters(request);
        if (parametros.length == 0) {
            response.setStatus(400);
            response.getOutputStream().write(mapper.writeValueAsBytes("Número de parâmetros errado."));
        } else if (parametros.length == 1) {
            Long id = Long.parseLong(parametros[0]);
            Usuario usuario = usuarioNegocio.recuperar(id);
            usuarioNegocio.excluir(usuario);
            response.getOutputStream().write(mapper.writeValueAsBytes(usuario));
        } else {
            response.setStatus(400);
            response.getOutputStream().write(mapper.writeValueAsBytes("Número de parâmetros errado."));
        }
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

    protected String[] getUrlParameters(HttpServletRequest request) {
        String url = this.getServletContext().getContextPath() + this.getClass().getAnnotation(WebServlet.class).urlPatterns()[0].replace("/*", "").trim();
        String paramters = request.getRequestURI().replaceFirst(url, "");
        paramters = paramters.replaceFirst("/", "");
        if (paramters.isEmpty()) {
            return new String[0];
        }
        return paramters.split("/");
    }

}
