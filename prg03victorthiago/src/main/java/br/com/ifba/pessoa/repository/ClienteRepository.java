/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.pessoa.repository;

import br.com.ifba.pessoa.dao.ClienteIDao;
import br.com.ifba.pessoa.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, ClienteIDao {

    @Override
    default Cliente update(Cliente cliente) {
       return ((JpaRepository<Cliente, Long>) this).save(cliente);
    }
}
