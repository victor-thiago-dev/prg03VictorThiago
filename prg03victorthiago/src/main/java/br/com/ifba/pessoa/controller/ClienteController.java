/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.controller;

import br.com.ifba.pessoa.entity.Cliente;
import br.com.ifba.pessoa.service.ClienteIService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;

@Controller
public class ClienteController implements ClienteIController {

    private final ClienteIService service;

    public ClienteController(ClienteIService service) {
        this.service = service;
    }

    @Override
    public Cliente save(Cliente cliente) {
        return service.save(cliente);
    }

    @Override
    public Cliente update(Cliente cliente) {
        return service.update(cliente);
    }

    @Override
    public void delete(Cliente cliente) {
        service.delete(cliente);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return service.findById(id);
    }

    @Override
    public List<Cliente> findAll() {
        return service.findAll();
    }

    @Override
    public Optional<Cliente> findByEmail(String email) {
        return service.findByEmail(email);
    }
}
