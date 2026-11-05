/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.dao;

import br.com.ifba.infrastructure.dao.GenericDao;
import br.com.ifba.usuario.entity.StatusUsuario;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.exception.UsuarioNaoEncontradoException;
import java.util.List;

public class UsuarioDao extends GenericDao<Usuario> implements UsuarioIDao {

    public UsuarioDao() {
        super(Usuario.class);
    }

    @Override
    public List<Usuario> findAll() {
        return consultar("select u from Usuario u join fetch u.pessoa order by u.login");
    }

    @Override
    public Usuario findByLogin(String login) {
        List<Usuario> encontrados = consultar(
                "select u from Usuario u join fetch u.pessoa where u.login = :login",
                "login", login);
        if (encontrados.isEmpty()) {
            throw new UsuarioNaoEncontradoException(login);
        }
        return encontrados.get(0);
    }

    @Override
    public boolean existsByLogin(String login) {
        return !consultar("select u from Usuario u where u.login = :login",
                "login", login).isEmpty();
    }

    @Override
    public List<Usuario> findByStatus(StatusUsuario status) {
        return consultar(
                "select u from Usuario u join fetch u.pessoa where u.status = :status order by u.login",
                "status", status);
    }

    // Task 07 da atividade anterior: busca textual com JPQL e where
    @Override
    public List<Usuario> findByLoginOrNome(String texto) {
        return consultar(
                "select u from Usuario u join fetch u.pessoa p "
                + "where lower(u.login) like :texto or lower(p.nome) like :texto "
                + "order by u.login",
                "texto", "%" + texto.toLowerCase() + "%");
    }
}