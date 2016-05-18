package br.com.encatman.repositorio;

import br.com.encatman.entidades.Usuario;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class Repositorio<T> {

    private RepositorioUtil repositorioUtil;
    private Class<T> clazz;

    public Repositorio(Class classe) {
        this.clazz = classe;
        repositorioUtil = new RepositorioUtil();
    }

    public T salvar(T objeto) {
        EntityManager em = repositorioUtil.criaEntityManager();
        em.getTransaction().begin();
        objeto = em.merge(objeto);
        em.getTransaction().commit();
        return objeto;
    }

    public T alterar(T objeto) {
        EntityManager em = repositorioUtil.criaEntityManager();
        em.getTransaction().begin();
        objeto = em.merge(objeto);
        em.getTransaction().commit();
        return objeto;
    }

    public T excluir(T objeto) {
        EntityManager em = repositorioUtil.criaEntityManager();
        em.getTransaction().begin();
        em.remove(objeto);
        em.getTransaction().commit();
        return objeto;
    }

    public T recuperar(Long chave) {
        EntityManager em = repositorioUtil.criaEntityManager();
        em.getTransaction().begin();
        T aRetornar = em.find(clazz, chave);
        em.getTransaction().commit();
        return aRetornar;
    }

    public List<T> listar() {
        EntityManager em = repositorioUtil.criaEntityManager();
        em.getTransaction().begin();
        Query consulta = em.createQuery("from " + clazz.getSimpleName());
        List<T> aRetornar = consulta.getResultList();
        em.getTransaction().commit();
        return aRetornar;
    }
}
