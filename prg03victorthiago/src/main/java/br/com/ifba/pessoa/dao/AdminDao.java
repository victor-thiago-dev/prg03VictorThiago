/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.dao;

import br.com.ifba.infrastructure.dao.GenericDao;
import br.com.ifba.pessoa.entity.Admin;
import java.util.Optional;

public class AdminDao extends GenericDao<Admin> implements AdminIDao {

    public AdminDao() {
        super(Admin.class);
    }

    @Override
    public Optional<Admin> findByEmail(String email) {
        return consultar("select a from Admin a where a.email = :email", "email", email)
                .stream().findFirst();
    }
}