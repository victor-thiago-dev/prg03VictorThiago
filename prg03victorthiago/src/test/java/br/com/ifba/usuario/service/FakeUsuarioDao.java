/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.service;

import br.com.ifba.usuario.dao.UsuarioIDao;
import br.com.ifba.usuario.entity.StatusUsuario;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.exception.UsuarioNaoEncontradoException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// dublê: cumpre o contrato do DAO guardando tudo numa lista, sem banco
class FakeUsuarioDao implements UsuarioIDao {

    private final List<Usuario> lista = new ArrayList<>();
    private long proximoId = 1;

    @Override
    public Usuario save(Usuario usuario) {
        usuario.setId(proximoId++); // o "banco" devolve o id
        lista.add(usuario);
        return usuario;
    }

    @Override
    public Usuario update(Usuario usuario) {
        return usuario;
    }

    @Override
    public void delete(Usuario usuario) {
        lista.remove(usuario);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return lista.stream().filter(u -> id.equals(u.getId())).findFirst();
    }

    @Override
    public List<Usuario> findAll() {
        return new ArrayList<>(lista);
    }

    @Override
    public Usuario findByLogin(String login) {
        return lista.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst()
                .orElseThrow(() -> new UsuarioNaoEncontradoException(login));
    }

    @Override
    public boolean existsByLogin(String login) {
        return lista.stream().anyMatch(u -> u.getLogin().equals(login));
    }

    @Override
    public List<Usuario> findByStatus(StatusUsuario status) {
        return lista.stream().filter(u -> u.getStatus() == status).toList();
    }

    @Override
    public List<Usuario> findByLoginOrNome(String texto) {
        String t = texto.toLowerCase();
        return lista.stream()
                .filter(u -> u.getLogin().toLowerCase().contains(t)
                        || u.getPessoa().getNome().toLowerCase().contains(t))
                .toList();
    }
}