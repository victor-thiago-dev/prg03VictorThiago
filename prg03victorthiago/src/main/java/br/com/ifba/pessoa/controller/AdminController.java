/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.controller;

import br.com.ifba.pessoa.entity.Admin;
import br.com.ifba.pessoa.service.AdminIService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController implements AdminIController {

    private final AdminIService service;

    public AdminController(AdminIService service) {
        this.service = service;
    }

    @Override
    public Admin save(Admin admin) {
        return service.save(admin);
    }

    @Override
    public Admin update(Admin admin) {
        return service.update(admin);
    }

    @Override
    public void delete(Admin admin) {
        service.delete(admin);
    }

    @Override
    public Optional<Admin> findById(Long id) {
        return service.findById(id);
    }

    @Override
    public List<Admin> findAll() {
        return service.findAll();
    }

    @Override
    public Optional<Admin> findByEmail(String email) {
        return service.findByEmail(email);
    }
}
