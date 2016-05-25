package br.com.encatman.repositorio;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "FiltroTransacoes", urlPatterns = {"/api/*"})
public class FiltroTransacoes implements Filter {
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        Throwable problem = null;
        EntityManager em = Persistencia.getInstancia().getEntityManager();
        try {
            em.getTransaction().begin();
            chain.doFilter(request, response);
            em.getTransaction().commit();
        } catch (Throwable t) {
            em.getTransaction().rollback();
            problem = t;
            t.printStackTrace();
        }
        
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
        Persistencia.getInstancia().encerra();
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        Persistencia.getInstancia();
    }
    
    private void sendProcessingError(Throwable t, ServletResponse resposta) throws IOException {
        resposta.setContentType("application/json;charset=UTF-8");
        if (resposta instanceof HttpServletResponse) {
            HttpServletResponse r = (HttpServletResponse) resposta;
            r.setStatus(500);
        }
        new ObjectMapper().writeValue(resposta.getOutputStream(), new Erro(getStackTrace(t)));
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
}
