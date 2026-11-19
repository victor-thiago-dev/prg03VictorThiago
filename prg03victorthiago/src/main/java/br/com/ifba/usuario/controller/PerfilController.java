/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.controller;

import br.com.ifba.usuario.entity.Perfil;
import br.com.ifba.usuario.service.PerfilIService;
import java.util.List;
import java.util.Optional;

public class PerfilController implements PerfilIController {

    private final PerfilIService service;

    public PerfilController(PerfilIService service) {
        this.service = service;
    }

    @Override
    public Perfil save(Perfil perfil) {
        return service.save(perfil);
    }

    @Override
    public Perfil update(Perfil perfil) {
        return service.update(perfil);
    }

    @Override
    public void delete(Perfil perfil) {
        service.delete(perfil);
    }

    @Override
    public Optional<Perfil> findById(Long id) {
        return service.findById(id);
    }

    @Override
    public List<Perfil> findAll() {
        return service.findAll();
    }

    @Override
    public Optional<Perfil> findByNome(String nome) {
        return service.findByNome(nome);
    }
}