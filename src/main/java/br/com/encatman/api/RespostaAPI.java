package br.com.encatman.api;

import br.com.encatman.entidades.Resposta;
import br.com.encatman.negocio.RespostaNegocio;
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
@WebServlet(name = "RespostaAPI", urlPatterns = {"/api/resposta/*"})
public class RespostaAPI extends HttpServlet {

    private ObjectMapper mapper;
    private RespostaNegocio respostaNegocio;

    public RespostaAPI() {
        mapper = new ObjectMapper();
        respostaNegocio = new RespostaNegocio();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String parametros[] = getUrlParameters(request);
        if (parametros.length == 0) {
            List<Resposta> lista = respostaNegocio.listar();
            response.getOutputStream().write(mapper.writeValueAsBytes(lista));
        } else if (parametros.length == 1) {
            Long id = Long.parseLong(parametros[0]);
            Resposta resposta = respostaNegocio.recuperar(id);
            response.getOutputStream().write(mapper.writeValueAsBytes(resposta));
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
            Resposta novo = mapper.readValue(request.getInputStream(), Resposta.class);
            Resposta salvar = respostaNegocio.salvar(novo);
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
            Resposta novo = mapper.readValue(request.getInputStream(), Resposta.class);
            novo.setCodigo(id);
            Resposta salvar = respostaNegocio.alterar(novo);
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
            Resposta resposta = respostaNegocio.recuperar(id);
            respostaNegocio.excluir(resposta);
            response.getOutputStream().write(mapper.writeValueAsBytes(resposta));
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
        return "Resposta API";
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
