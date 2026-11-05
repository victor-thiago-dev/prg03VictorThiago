/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.dao;

import br.com.ifba.infrastructure.dao.GenericIDao;
import br.com.ifba.pessoa.entity.Admin;
import java.util.Optional;

public interface AdminIDao extends GenericIDao<Admin> {

    Optional<Admin> findByEmail(String email);
}
