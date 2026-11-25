/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.service;

import br.com.ifba.infrastructure.exception.NegocioException;
import br.com.ifba.pessoa.dao.PessoaIDao;
import br.com.ifba.pessoa.entity.Pessoa;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PessoaService implements PessoaIService {

    private final PessoaIDao dao;

    public PessoaService(PessoaIDao dao) {
        this.dao = dao;
    }

    @Override
    public Pessoa save(Pessoa pessoa) {
        RegrasPessoa.validar(pessoa);
        if (dao.findByCpf(pessoa.getCpf()).isPresent()) {
            throw new NegocioException("Já existe uma pessoa com esse CPF.");
        }
        return dao.save(pessoa);
    }

    @Override
    public Pessoa update(Pessoa pessoa) {
        RegrasPessoa.validar(pessoa);
        return dao.update(pessoa);
    }

    @Override
    public void delete(Pessoa pessoa) {
        dao.delete(pessoa);
    }

    @Override
    public Optional<Pessoa> findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Pessoa> findAll() {
        return dao.findAll();
    }

    @Override
    public Optional<Pessoa> findByCpf(String cpf) {
        return dao.findByCpf(cpf);
    }
}