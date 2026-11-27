/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.dao;

import br.com.ifba.pessoa.entity.Barbeiro;
import java.util.List;
import java.util.Optional;

public interface BarbeiroIDao {

    Barbeiro save(Barbeiro barbeiro);

    Barbeiro update(Barbeiro barbeiro);

    void delete(Barbeiro barbeiro);

    Optional<Barbeiro> findById(Long id);

    List<Barbeiro> findAll();

    List<Barbeiro> findByEspecialidade(String especialidade);
}