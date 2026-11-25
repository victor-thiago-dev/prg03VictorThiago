/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.dao;

import br.com.ifba.infrastructure.dao.GenericDao;
import br.com.ifba.pessoa.entity.Cliente;
import jakarta.persistence.EntityManagerFactory;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteDao extends GenericDao<Cliente> implements ClienteIDao {

    public ClienteDao(EntityManagerFactory fabrica) {
        super(Cliente.class, fabrica);
    }

    @Override
    public Optional<Cliente> findByEmail(String email) {
        return consultar("select c from Cliente c where c.email = :email", "email", email)
                .stream().findFirst();
    }
}