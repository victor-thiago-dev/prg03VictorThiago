/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.repositorio;

import br.com.ifba.usuario.entity.Usuario;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author victo
 */
public class RepositorioUsuarioEmMemoria {
    
    private final List<Usuario> usuarios = new ArrayList<>();
    private final Map<String, Usuario> porLogin = new HashMap<>();
    
    public void cadastrar(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo.");
        }

        // Task 04: verificação pelo Map, não pela List — é consulta direta, não varredura
        if (porLogin.containsKey(usuario.getLogin())) {
            throw new IllegalArgumentException("Já existe um usuário com esse login.");
        }

        usuarios.add(usuario);
        porLogin.put(usuario.getLogin(), usuario);
    }
    
    public List<Usuario> listarTodos() {
        return Collections.unmodifiableList(usuarios);
    }
    
        //versão 1: percorre a List com for (O(n) - cresce com o tamanho da lista)
    public Usuario buscarPorLoginVarrendoLista(String login) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login)) {
                return usuario;
            }
        }
        return null;
    }
 
    //versão 2: usa o Map (O(1) em média - não cresce com o tamanho da lista)
    public Usuario buscarPorLogin(String login) {
        return porLogin.get(login);
    }
}
