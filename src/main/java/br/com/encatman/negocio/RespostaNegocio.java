package br.com.encatman.negocio;

import br.com.encatman.entidades.Resposta;
import br.com.encatman.repositorio.Repositorio;
import java.util.List;

public class RespostaNegocio {

    private Repositorio<Resposta> repositorio;

    public RespostaNegocio() {
        repositorio = new Repositorio<>(Resposta.class);
    }

    public Resposta salvar(Resposta objeto) {
        return repositorio.salvar(objeto);
    }

    public Resposta alterar(Resposta objeto) {
        return repositorio.alterar(objeto);
    }

    public Resposta excluir(Resposta objeto) {
        return repositorio.excluir(objeto);
    }

    public Resposta recuperar(Long chave) {
        return repositorio.recuperar(chave);
    }

    public List<Resposta> listar() {
        return repositorio.listar();
    }

}

