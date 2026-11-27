/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.pessoa.repository;

import br.com.ifba.pessoa.dao.PessoaIDao;
import br.com.ifba.pessoa.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaIDao {

    @Override
    default Pessoa update(Pessoa pessoa) {
        return ((JpaRepository<Pessoa, Long>) this).save(pessoa);
    }
}
