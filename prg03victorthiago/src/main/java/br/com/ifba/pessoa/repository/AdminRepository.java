/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.pessoa.repository;

import br.com.ifba.pessoa.dao.AdminIDao;
import br.com.ifba.pessoa.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long>, AdminIDao {

    @Override
    default Admin update(Admin admin) {
        return ((JpaRepository<Admin, Long>) this).save(admin);
    }
}
