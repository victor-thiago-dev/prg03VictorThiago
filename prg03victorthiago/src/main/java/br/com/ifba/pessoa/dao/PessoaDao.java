/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.dao;

import br.com.ifba.infrastructure.dao.GenericDao;
import br.com.ifba.pessoa.entity.Pessoa;
import java.util.Optional;

public class PessoaDao extends GenericDao<Pessoa> implements PessoaIDao {

    public PessoaDao() {
        super(Pessoa.class);
    }

    @Override
    public Optional<Pessoa> findByCpf(String cpf) {
        return consultar("select p from Pessoa p where p.cpf = :cpf", "cpf", cpf)
                .stream().findFirst();
    }
}