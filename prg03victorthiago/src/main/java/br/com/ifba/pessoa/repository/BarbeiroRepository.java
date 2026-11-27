/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.pessoa.repository;

import br.com.ifba.pessoa.dao.BarbeiroIDao;
import br.com.ifba.pessoa.entity.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long>, BarbeiroIDao {

    @Override
    default Barbeiro update(Barbeiro barbeiro) {
        return ((JpaRepository<Barbeiro, Long>) this).save(barbeiro);
    }
}
