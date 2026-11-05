/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.dao;

import br.com.ifba.infrastructure.dao.GenericIDao;
import br.com.ifba.pessoa.entity.Barbeiro;
import java.util.List;

public interface BarbeiroIDao extends GenericIDao<Barbeiro> {

    List<Barbeiro> findByEspecialidade(String especialidade);
}
