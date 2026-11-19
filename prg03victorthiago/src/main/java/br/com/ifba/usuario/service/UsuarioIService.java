/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.usuario.service;

import br.com.ifba.infrastructure.service.GenericIService;
import br.com.ifba.usuario.entity.StatusUsuario;
import br.com.ifba.usuario.entity.Usuario;
import java.util.List;

public interface UsuarioIService extends GenericIService<Usuario> {

    Usuario findByLogin(String login);

    List<Usuario> findByStatus(StatusUsuario status);

    List<Usuario> findByLoginOrNome(String texto);
}
