/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.dao;

import br.com.ifba.infrastructure.dao.GenericDao;
import br.com.ifba.pessoa.entity.Barbeiro;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class BarbeiroDao extends GenericDao<Barbeiro> implements BarbeiroIDao {

    public BarbeiroDao(EntityManagerFactory fabrica) {
        super(Barbeiro.class, fabrica);
    }

    @Override
    public List<Barbeiro> findByEspecialidade(String especialidade) {
        return consultar("select b from Barbeiro b where b.especialidade = :especialidade",
                "especialidade", especialidade);
    }
}