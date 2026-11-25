/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.controller;

import br.com.ifba.pessoa.entity.Barbeiro;
import br.com.ifba.pessoa.service.BarbeiroIService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;

@Controller
public class BarbeiroController implements BarbeiroIController {

    private final BarbeiroIService service;

    public BarbeiroController(BarbeiroIService service) {
        this.service = service;
    }

    @Override
    public Barbeiro save(Barbeiro barbeiro) {
        return service.save(barbeiro);
    }

    @Override
    public Barbeiro update(Barbeiro barbeiro) {
        return service.update(barbeiro);
    }

    @Override
    public void delete(Barbeiro barbeiro) {
        service.delete(barbeiro);
    }

    @Override
    public Optional<Barbeiro> findById(Long id) {
        return service.findById(id);
    }

    @Override
    public List<Barbeiro> findAll() {
        return service.findAll();
    }

    @Override
    public List<Barbeiro> findByEspecialidade(String especialidade) {
        return service.findByEspecialidade(especialidade);
    }
}
