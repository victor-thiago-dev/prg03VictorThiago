/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.service;

import br.com.ifba.infrastructure.exception.NegocioException;
import br.com.ifba.pessoa.dao.AdminIDao;
import br.com.ifba.pessoa.entity.Admin;
import java.util.List;
import java.util.Optional;

public class AdminService implements AdminIService {

    private final AdminIDao dao;

    public AdminService(AdminIDao dao) {
        this.dao = dao;
    }

    @Override
    public Admin save(Admin admin) {
        RegrasPessoa.validar(admin);
        if (dao.findByEmail(admin.getEmail()).isPresent()) {
            throw new NegocioException("Já existe um administrador com esse e-mail.");
        }
        return dao.save(admin);
    }

    @Override
    public Admin update(Admin admin) {
        RegrasPessoa.validar(admin);
        return dao.update(admin);
    }

    @Override
    public void delete(Admin admin) {
        dao.delete(admin);
    }

    @Override
    public Optional<Admin> findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Admin> findAll() {
        return dao.findAll();
    }

    @Override
    public Optional<Admin> findByEmail(String email) {
        return dao.findByEmail(email);
    }
}
