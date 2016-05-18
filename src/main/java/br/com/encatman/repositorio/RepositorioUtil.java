package br.com.encatman.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RepositorioUtil {

    private final EntityManagerFactory emf;

    public RepositorioUtil() {
        emf = Persistence.createEntityManagerFactory("encatmanPU");
    }

    public EntityManager criaEntityManager() {
        return emf.createEntityManager();
    }

}
