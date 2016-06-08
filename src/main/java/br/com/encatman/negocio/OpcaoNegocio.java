package br.com.encatman.negocio;

import br.com.encatman.entidades.Opcao;
import br.com.encatman.repositorio.Repositorio;
import java.util.List;

public class OpcaoNegocio {

    private Repositorio<Opcao> repositorio;

    public OpcaoNegocio() {
        repositorio = new Repositorio<>(Opcao.class);
    }

    public Opcao salvar(Opcao objeto) {
        return repositorio.salvar(objeto);
    }

    public Opcao alterar(Opcao objeto) {
        return repositorio.alterar(objeto);
    }

    public Opcao excluir(Opcao objeto) {
        return repositorio.excluir(objeto);
    }

    public Opcao recuperar(Long chave) {
        return repositorio.recuperar(chave);
    }

    public List<Opcao> listar() {
        return repositorio.listar();
    }

}