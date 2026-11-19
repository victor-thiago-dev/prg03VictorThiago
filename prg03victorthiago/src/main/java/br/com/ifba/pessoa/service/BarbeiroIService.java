/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.pessoa.service;

import br.com.ifba.infrastructure.service.GenericIService;
import br.com.ifba.pessoa.entity.Barbeiro;
import java.util.List;

public interface BarbeiroIService extends GenericIService<Barbeiro> {

    List<Barbeiro> findByEspecialidade(String especialidade);
}