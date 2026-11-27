/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.dao;

import br.com.ifba.usuario.entity.Perfil;
import java.util.List;
import java.util.Optional;

public interface PerfilIDao {

    Perfil save(Perfil perfil);

    Perfil update(Perfil perfil);

    void delete(Perfil perfil);

    Optional<Perfil> findById(Long id);

    List<Perfil> findAll();

    Optional<Perfil> findByNome(String nome);
}
