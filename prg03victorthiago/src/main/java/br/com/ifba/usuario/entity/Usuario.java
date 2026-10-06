/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.entity;

import br.com.ifba.usuario.interfaces.Autenticavel;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import br.com.ifba.pessoa.entity.Pessoa;
import br.com.ifba.usuario.validar.ValidadorUsuario;
import java.util.Objects;

/**
 *
 * @author victo
 */
public class Usuario implements Autenticavel {
    
   private Pessoa pessoa;
   private String login;
   private String senha;
   private StatusUsuario status = StatusUsuario.ATIVO;
   private final List<Perfil> perfis = new ArrayList<>();
   private Perfil perfilAtivo;
   
   @Override
    public boolean autenticar(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }

    public Usuario() {
    }
 
    public Usuario(Pessoa pessoa, String login, String senha) {
        this.pessoa = pessoa;
        this.login = login;
        this.senha = senha;
    }
    
    public Usuario(Pessoa pessoa, String login, String senha, Perfil perfil) {
        this(pessoa, login, senha);
        adicionarPerfil(perfil);
    }
    
    public final void adicionarPerfil(Perfil perfil) {
        if (perfil != null && !perfis.contains(perfil)) {
            perfis.add(perfil);
            if (perfilAtivo == null) {
                perfilAtivo = perfil;
            }
        }
    }
 
    public List<Perfil> getPerfis() {
        return Collections.unmodifiableList(perfis);
    }
 
    public Perfil getPerfilAtivo() {
        return perfilAtivo;
    }

    public void setPerfilAtivo(Perfil perfil) {
        if (perfil == null || !perfis.contains(perfil)) {
            throw new IllegalArgumentException("Usuário não possui esse perfil.");
        }
        this.perfilAtivo = perfil;
    }

    public String getLogin() {
        return login;
    }

public void setLogin(String login) {
    if (ValidadorUsuario.contemPalavraProibida(login)) {
        throw new IllegalArgumentException("Login contém palavra não permitida.");
    }
    this.login = login;
}

    public String getSenha() {
        return senha;
    }

public void setSenha(String senha) {
    if (!ValidadorUsuario.senhaForte(senha)) {
        throw new IllegalArgumentException("Senha não atende ao tamanho mínimo.");
    }
    this.senha = senha;
}

    public StatusUsuario getStatus() {
        return status;
    }

    public void setStatus(StatusUsuario status) {
        this.status = status;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.login);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.login, other.login);
    }
}
