/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.usuario.service;

import br.com.ifba.infrastructure.service.GenericIService;
import br.com.ifba.usuario.entity.Perfil;
import java.util.Optional;

public interface PerfilIService extends GenericIService<Perfil> {

    Optional<Perfil> findByNome(String nome);
}
