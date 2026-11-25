/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.service;

import br.com.ifba.infrastructure.exception.NegocioException;
import br.com.ifba.usuario.dao.PerfilIDao;
import br.com.ifba.usuario.entity.Perfil;
import br.com.ifba.usuario.validar.ValidadorUsuario;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PerfilService implements PerfilIService {

    private final PerfilIDao dao;

    public PerfilService(PerfilIDao dao) {
        this.dao = dao;
    }

    @Override
    public Perfil save(Perfil perfil) {
        validarNome(perfil);
        if (dao.findByNome(perfil.getNome()).isPresent()) {
            throw new NegocioException("Já existe um perfil com esse nome.");
        }
        return dao.save(perfil);
    }

    @Override
    public Perfil update(Perfil perfil) {
        validarNome(perfil);
        return dao.update(perfil);
    }

    @Override
    public void delete(Perfil perfil) {
        dao.delete(perfil);
    }

    @Override
    public Optional<Perfil> findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Perfil> findAll() {
        return dao.findAll();
    }

    @Override
    public Optional<Perfil> findByNome(String nome) {
        return dao.findByNome(nome);
    }

    private void validarNome(Perfil perfil) {
        if (perfil == null || !ValidadorUsuario.camposPreenchidos(perfil.getNome())) {
            throw new NegocioException("O nome do perfil é obrigatório.");
        }
    }
}
