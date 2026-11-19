/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.service;

import br.com.ifba.infrastructure.exception.NegocioException;
import br.com.ifba.pessoa.dao.ClienteIDao;
import br.com.ifba.pessoa.entity.Cliente;
import java.util.List;
import java.util.Optional;

public class ClienteService implements ClienteIService {

    private final ClienteIDao dao;

    public ClienteService(ClienteIDao dao) {
        this.dao = dao;
    }

    @Override
    public Cliente save(Cliente cliente) {
        RegrasPessoa.validar(cliente);
        if (dao.findByEmail(cliente.getEmail()).isPresent()) {
            throw new NegocioException("Já existe um cliente com esse e-mail.");
        }
        return dao.save(cliente);
    }

    @Override
    public Cliente update(Cliente cliente) {
        RegrasPessoa.validar(cliente);
        return dao.update(cliente);
    }

    @Override
    public void delete(Cliente cliente) {
        dao.delete(cliente);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Cliente> findAll() {
        return dao.findAll();
    }

    @Override
    public Optional<Cliente> findByEmail(String email) {
        return dao.findByEmail(email);
    }
}
