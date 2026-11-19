/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.usuario.controller;

import br.com.ifba.infrastructure.controller.GenericIController;
import br.com.ifba.usuario.entity.StatusUsuario;
import br.com.ifba.usuario.entity.Usuario;
import java.util.List;

public interface UsuarioIController extends GenericIController<Usuario> {

    Usuario findByLogin(String login);

    List<Usuario> findByStatus(StatusUsuario status);

    List<Usuario> findByLoginOrNome(String texto);
}
