package br.com.encatman.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Persistencia {

    private static Persistencia instancia = new Persistencia();
    
    public static Persistencia getInstancia() {
        return instancia;
    }

    private final EntityManagerFactory entityManagerFactory;
    
    private ThreadLocal<EntityManager> tlem;

    private Persistencia() {
        entityManagerFactory = Persistence.createEntityManagerFactory("encatmanPU");
        tlem=new ThreadLocal<>();
    }

    public EntityManager getEntityManager() {
        if (tlem.get()==null){
            tlem.set(entityManagerFactory.createEntityManager());
        }
        return tlem.get();
    }

    public void encerra() {
        entityManagerFactory.close();
    }

}
