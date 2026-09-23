/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.entity;

import br.com.ifba.usuario.interfaces.Autenticavel;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

/**
 *
 * @author victo
 */
public class Usuario implements Autenticavel {
    
   private String nome;
   private String cpf;
   private String telefone;
   private String email;
   private String login;
   private String senha;
   private StatusUsuario status;
   private final List<Perfil> perfis = new ArrayList<>();
   private Perfil perfilAtivo;
   
    public boolean autenticar(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }

    public Usuario() {
    }

    public Usuario(StatusUsuario status) {
        this.status = status.ATIVO;
    }

    public Usuario(String nome, String telefone, String login, String senha, StatusUsuario status) {
        this.nome = nome;
        this.telefone = telefone;
        this.login = login;
        this.senha = senha;
        this.status = status.ATIVO;
    }
    
    public Usuario(String nome, String cpf, String login, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
    }

    public void adicionarPerfil(Perfil perfil) {
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
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public StatusUsuario getStatus() {
        return status;
    }

    public void setStatus(StatusUsuario status) {
        this.status = status;
    }   
}
