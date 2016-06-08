/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.encatman.negocio;

import br.com.encatman.entidades.Enquete;
import br.com.encatman.repositorio.Repositorio;
import java.util.List;

/**
 *
 * @author carlos cancian
 */
public class EnqueteNegocio {
    private Repositorio<Enquete> repositorio;

    public EnqueteNegocio() {
        repositorio = new Repositorio<>(Enquete.class);
    }

    public Enquete salvar(Enquete objeto) {
        return repositorio.salvar(objeto);
    }

    public Enquete alterar(Enquete objeto) {
        return repositorio.alterar(objeto);
    }

    public Enquete excluir(Enquete objeto) {
        return repositorio.excluir(objeto);
    }

    public Enquete recuperar(Long chave) {
        return repositorio.recuperar(chave);
    }

    public List<Enquete> listar() {
        return repositorio.listar();
    }

    
}
