/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.dao;

import br.com.ifba.pessoa.entity.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteIDao {

    Cliente save(Cliente cliente);

    Cliente update(Cliente cliente);

    void delete(Cliente cliente);

    Optional<Cliente> findById(Long id);

    List<Cliente> findAll();

    Optional<Cliente> findByEmail(String email);
}
