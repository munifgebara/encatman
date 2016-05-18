package br.com.encatman.teste;

import br.com.encatman.entidades.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import static javax.persistence.Persistence.createEntityManagerFactory;

public class Programa {

    public static void main(String args[]) {
        EntityManager em = Persistence.createEntityManagerFactory("encatmanPU").createEntityManager();

        try {
            em.getTransaction().begin();
            
            //Usuario lucas=new Usuario();
            //lucas.setNome("Lucas");
            //lucas.setEmail("lucas@fafiman.br");
            //em.persist(lucas);
            
            Usuario usu=em.find(Usuario.class, 2l);
            System.out.println("--->"+usu.getNome());
            
            //em.remove(usu);
            
            usu.setNome("Lucas Lemos");
            
            
            

            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
        System.exit(0);
    }

}
