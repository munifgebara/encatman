package br.com.encatman.api;

import br.com.encatman.entidades.Enquete;
import br.com.encatman.negocio.EnqueteNegocio;
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
@WebServlet(name = "EnqueteAPI", urlPatterns = {"/api/enquete/*"})
public class EnqueteAPI extends HttpServlet {

    private ObjectMapper mapper;
    private EnqueteNegocio enqueteNegocio;

    public EnqueteAPI() {
        mapper = new ObjectMapper();
        enqueteNegocio = new EnqueteNegocio();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String parametros[] = getUrlParameters(request);
        if (parametros.length == 0) {
            List<Enquete> lista = enqueteNegocio.listar();
            response.getOutputStream().write(mapper.writeValueAsBytes(lista));
        } else if (parametros.length == 1) {
            Long id = Long.parseLong(parametros[0]);
            Enquete enquete = enqueteNegocio.recuperar(id);
            response.getOutputStream().write(mapper.writeValueAsBytes(enquete));
        } else {
            response.setStatus(400);
            response.getOutputStream().write(mapper.writeValueAsBytes("Número de parâmetros errado."));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String parametros[] = getUrlParameters(request);
        if (parametros.length == 0) {
            Enquete novo = mapper.readValue(request.getInputStream(), Enquete.class);
            Enquete salvar = enqueteNegocio.salvar(novo);
            response.getOutputStream().write(mapper.writeValueAsBytes(salvar));
        } else {
            response.setStatus(400);
            response.getOutputStream().write(mapper.writeValueAsBytes("Número de parâmetros errado."));
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String parametros[] = getUrlParameters(request);
        if (parametros.length == 1) {
            Long id = Long.parseLong(parametros[0]);
            Enquete novo = mapper.readValue(request.getInputStream(), Enquete.class);
            novo.setCodigo(id);
            Enquete salvar = enqueteNegocio.alterar(novo);
            response.getOutputStream().write(mapper.writeValueAsBytes(salvar));
        } else {
            response.setStatus(400);
            response.getOutputStream().write(mapper.writeValueAsBytes("Número de parâmetros errado."));
        }
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
            Enquete enquete = enqueteNegocio.recuperar(id);
            enqueteNegocio.excluir(enquete);
            response.getOutputStream().write(mapper.writeValueAsBytes(enquete));
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
        return "Enquete API";
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
