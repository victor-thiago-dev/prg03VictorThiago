/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.controller;

import br.com.ifba.pessoa.entity.Pessoa;
import br.com.ifba.pessoa.service.PessoaIService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;

@Controller
public class PessoaController implements PessoaIController {

    private final PessoaIService service;

    public PessoaController(PessoaIService service) {
        this.service = service;
    }

    @Override
    public Pessoa save(Pessoa pessoa) {
        return service.save(pessoa);
    }

    @Override
    public Pessoa update(Pessoa pessoa) {
        return service.update(pessoa);
    }

    @Override
    public void delete(Pessoa pessoa) {
        service.delete(pessoa);
    }

    @Override
    public Optional<Pessoa> findById(Long id) {
        return service.findById(id);
    }

    @Override
    public List<Pessoa> findAll() {
        return service.findAll();
    }

    @Override
    public Optional<Pessoa> findByCpf(String cpf) {
        return service.findByCpf(cpf);
    }
}
