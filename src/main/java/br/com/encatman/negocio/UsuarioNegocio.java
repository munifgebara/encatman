package br.com.encatman.negocio;

import br.com.encatman.entidades.Usuario;
import br.com.encatman.repositorio.Repositorio;
import java.util.List;

public class UsuarioNegocio {

    private Repositorio<Usuario> repositorio;

    public UsuarioNegocio() {
        repositorio = new Repositorio<>(Usuario.class);
    }

    public Usuario salvar(Usuario objeto) {
        return repositorio.salvar(objeto);
    }

    public Usuario alterar(Usuario objeto) {
        return repositorio.alterar(objeto);
    }

    public Usuario excluir(Usuario objeto) {
        return repositorio.excluir(objeto);
    }

    public Usuario recuperar(Long chave) {
        return repositorio.recuperar(chave);
    }

    public List<Usuario> listar() {
        return repositorio.listar();
    }

}
