/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.pessoa.controller;

import br.com.ifba.infrastructure.controller.GenericIController;
import br.com.ifba.pessoa.entity.Admin;
import java.util.Optional;

public interface AdminIController extends GenericIController<Admin> {

    Optional<Admin> findByEmail(String email);
}
