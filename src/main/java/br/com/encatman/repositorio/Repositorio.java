package br.com.encatman.repositorio;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class Repositorio<T> {

    private Class<T> clazz;

    public Repositorio(Class classe) {
        this.clazz = classe;
    }

    public T salvar(T objeto) {
        EntityManager em = Persistencia.getInstancia().getEntityManager();
        objeto = em.merge(objeto);
        return objeto;
    }

    public T alterar(T objeto) {
        EntityManager em = Persistencia.getInstancia().getEntityManager();
        objeto = em.merge(objeto);
        return objeto;
    }

    public T excluir(T objeto) {
        EntityManager em = Persistencia.getInstancia().getEntityManager();
        em.remove(objeto);
        return objeto;
    }

    public T recuperar(Long chave) {
        EntityManager em = Persistencia.getInstancia().getEntityManager();
        T aRetornar = em.find(clazz, chave);
        return aRetornar;
    }

    public List<T> listar() {
        EntityManager em = Persistencia.getInstancia().getEntityManager();
        Query consulta = em.createQuery("from " + clazz.getSimpleName());
        List<T> aRetornar = consulta.getResultList();
        return aRetornar;
    }
}
