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
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
@Table(name = "usuarios")
public class Usuario implements Autenticavel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false, unique = true, length = 50)
    private String login;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 10)
    private StatusUsuario status = StatusUsuario.ATIVO;

    // O cascade = ALL faz o Hibernate salvar e apagar a Pessoa junto com o Usuario.
    //O EAGER traz a pessoa na mesma consulta, e a listagem precisa do nome
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    @Transient
    private final List<Perfil> perfis = new ArrayList<>();

    @Transient
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
    
    public Long getId() {
        return id;
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
