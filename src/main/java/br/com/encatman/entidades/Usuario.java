package br.com.encatman.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class Usuario {

    @Id
    @GeneratedValue
    private Long codigo;

    private String nome;
    private String email;
    private String senha;
    private Boolean ativo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataNascimento;

    public Usuario() {
        ativo = true;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.length() > 2) {
            this.nome = nome;
        } else {
            throw new RuntimeException("Nome do usuário muito curto");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.contains("@")) {
            this.email = email;
        } else {
            throw new RuntimeException("Email inválido");
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha.length() > 4) {
            this.senha = senha;
        } else {
            throw new RuntimeException("Senha muito curta");
        }
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

}
