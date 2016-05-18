package br.com.encatman.teste;

import br.com.encatman.entidades.Usuario;
import br.com.encatman.negocio.UsuarioNegocio;
import br.com.encatman.repositorio.Repositorio;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import static javax.persistence.Persistence.createEntityManagerFactory;

public class Programa {

    public static void main(String args[]) {
        UsuarioNegocio un=new UsuarioNegocio();
        Usuario u=new Usuario();
        u.setNome("Renan");
        u.setSenha("meuAmor");
        u.setAtivo(Boolean.TRUE);
        u.setEmail("casado@fafiman.br");
        
        un.salvar(u);
        
        
        System.exit(0);
    }

}
