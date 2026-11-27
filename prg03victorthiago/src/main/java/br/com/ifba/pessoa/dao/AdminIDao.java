/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.dao;

import br.com.ifba.pessoa.entity.Admin;
import java.util.List;
import java.util.Optional;

public interface AdminIDao {

    Admin save(Admin admin);

    Admin update(Admin admin);

    void delete(Admin admin);

    Optional<Admin> findById(Long id);

    List<Admin> findAll();

    Optional<Admin> findByEmail(String email);
}
