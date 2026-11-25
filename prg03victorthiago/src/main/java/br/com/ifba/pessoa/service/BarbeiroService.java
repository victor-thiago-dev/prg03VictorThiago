/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.service;

import br.com.ifba.pessoa.dao.BarbeiroIDao;
import br.com.ifba.pessoa.entity.Barbeiro;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BarbeiroService implements BarbeiroIService {

    private final BarbeiroIDao dao;

    public BarbeiroService(BarbeiroIDao dao) {
        this.dao = dao;
    }

    @Override
    public Barbeiro save(Barbeiro barbeiro) {
        RegrasPessoa.validar(barbeiro);
        return dao.save(barbeiro);
    }

    @Override
    public Barbeiro update(Barbeiro barbeiro) {
        RegrasPessoa.validar(barbeiro);
        return dao.update(barbeiro);
    }

    @Override
    public void delete(Barbeiro barbeiro) {
        dao.delete(barbeiro);
    }

    @Override
    public Optional<Barbeiro> findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Barbeiro> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Barbeiro> findByEspecialidade(String especialidade) {
        return dao.findByEspecialidade(especialidade);
    }
}