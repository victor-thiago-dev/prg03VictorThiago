/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.pessoa.service;

import br.com.ifba.infrastructure.service.GenericIService;
import br.com.ifba.pessoa.entity.Admin;
import java.util.Optional;

public interface AdminIService extends GenericIService<Admin> {

    Optional<Admin> findByEmail(String email);
}
