/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.dao;

import br.com.ifba.infrastructure.dao.GenericDao;
import br.com.ifba.usuario.entity.Perfil;
import jakarta.persistence.EntityManagerFactory;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class PerfilDao extends GenericDao<Perfil> implements PerfilIDao {

    public PerfilDao(EntityManagerFactory fabrica) {
        super(Perfil.class, fabrica);
    }

    @Override
    public Optional<Perfil> findByNome(String nome) {
        return consultar("select p from Perfil p where p.nome = :nome", "nome", nome)
                .stream().findFirst();
    }
}