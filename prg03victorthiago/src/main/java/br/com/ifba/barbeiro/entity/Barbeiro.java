/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.barbeiro.entity;

import br.com.ifba.pessoa.entity.Pessoa;

/**
 *
 * @author victo
 */
public class Barbeiro extends Pessoa{
    
    private String especialidade;

    public Barbeiro(String nome, String cpf, String telefone, String email, String especialidade) {
        super(nome, cpf, telefone, email);
        this.especialidade = especialidade;
    }
    
}
