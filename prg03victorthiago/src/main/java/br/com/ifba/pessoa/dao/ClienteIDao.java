/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.dao;

import br.com.ifba.infrastructure.dao.GenericIDao;
import br.com.ifba.pessoa.entity.Cliente;
import java.util.Optional;

public interface ClienteIDao extends GenericIDao<Cliente> {

    Optional<Cliente> findByEmail(String email);
}
