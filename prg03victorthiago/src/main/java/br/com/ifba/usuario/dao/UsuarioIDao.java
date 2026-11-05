/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.dao;

import br.com.ifba.infrastructure.dao.GenericIDao;
import br.com.ifba.usuario.entity.StatusUsuario;
import br.com.ifba.usuario.entity.Usuario;
import java.util.List;

public interface UsuarioIDao extends GenericIDao<Usuario> {

    Usuario findByLogin(String login); // lança UsuarioNaoEncontradoException

    boolean existsByLogin(String login);

    List<Usuario> findByStatus(StatusUsuario status);

    List<Usuario> findByLoginOrNome(String texto);
}