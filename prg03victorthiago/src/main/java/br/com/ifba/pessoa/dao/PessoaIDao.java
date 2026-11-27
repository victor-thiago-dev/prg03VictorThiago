/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.dao;

import br.com.ifba.pessoa.entity.Pessoa;
import java.util.List;
import java.util.Optional;

public interface PessoaIDao {

    Pessoa save(Pessoa pessoa);

    Pessoa update(Pessoa pessoa);

    void delete(Pessoa pessoa);

    Optional<Pessoa> findById(Long id);

    List<Pessoa> findAll();

    Optional<Pessoa> findByCpf(String cpf);
}
