/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.usuario.repository;

import br.com.ifba.usuario.dao.PerfilIDao;
import br.com.ifba.usuario.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long>, PerfilIDao {

    @Override
    default Perfil update(Perfil perfil) {
        return ((JpaRepository<Perfil, Long>) this).save(perfil);
    }
}