/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.usuario.controller;

import br.com.ifba.infrastructure.controller.GenericIController;
import br.com.ifba.usuario.entity.Perfil;
import java.util.Optional;

public interface PerfilIController extends GenericIController<Perfil> {

    Optional<Perfil> findByNome(String nome);
}
