/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.dao;

import br.com.ifba.infrastructure.dao.GenericDao;
import br.com.ifba.pessoa.entity.Barbeiro;
import java.util.List;

public class BarbeiroDao extends GenericDao<Barbeiro> implements BarbeiroIDao {

    public BarbeiroDao() {
        super(Barbeiro.class);
    }

    @Override
    public List<Barbeiro> findByEspecialidade(String especialidade) {
        return consultar("select b from Barbeiro b where b.especialidade = :especialidade",
                "especialidade", especialidade);
    }
}