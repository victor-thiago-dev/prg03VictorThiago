/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.dao;

import br.com.ifba.usuario.entity.StatusUsuario;
import br.com.ifba.usuario.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioIDao {

    Usuario save(Usuario usuario);
    
    Usuario update(Usuario usuario);

    void delete(Usuario usuario);

    Optional<Usuario> findById(Long id);

    List<Usuario> findAll();

    Usuario findByLogin(String login); // lança UsuarioNaoEncontradoException

    boolean existsByLogin(String login);

    List<Usuario> findByStatus(StatusUsuario status);

    List<Usuario> findByLoginOrNome(String texto);
}