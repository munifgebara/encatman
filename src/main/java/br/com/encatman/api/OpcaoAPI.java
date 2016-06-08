package br.com.encatman.api;

import br.com.encatman.entidades.Opcao;
import br.com.encatman.negocio.OpcaoNegocio;
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
@WebServlet(name = "OpcaoAPI", urlPatterns = {"/api/opcao/*"})
public class OpcaoAPI extends HttpServlet {

    private ObjectMapper mapper;
    private OpcaoNegocio opcaoNegocio;

    public OpcaoAPI() {
        mapper = new ObjectMapper();
        opcaoNegocio = new OpcaoNegocio();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String parametros[] = getUrlParameters(request);
        if (parametros.length == 0) {
            List<Opcao> lista = opcaoNegocio.listar();
            response.getOutputStream().write(mapper.writeValueAsBytes(lista));
        } else if (parametros.length == 1) {
            Long id = Long.parseLong(parametros[0]);
            Opcao opcao = opcaoNegocio.recuperar(id);
            response.getOutputStream().write(mapper.writeValueAsBytes(opcao));
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
            Opcao novo = mapper.readValue(request.getInputStream(), Opcao.class);
            Opcao salvar = opcaoNegocio.salvar(novo);
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
            Opcao novo = mapper.readValue(request.getInputStream(), Opcao.class);
            novo.setCodigo(id);
            Opcao salvar = opcaoNegocio.alterar(novo);
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
            Opcao opcao = opcaoNegocio.recuperar(id);
            opcaoNegocio.excluir(opcao);
            response.getOutputStream().write(mapper.writeValueAsBytes(opcao));
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
        return "Opcao API";
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
