/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BARBEIRO")
public class Barbeiro extends Pessoa{
    
     // nullable (padrão): na tabela única, Cliente e Admin deixam essa coluna vazia
    @Column(name = "especialidade", length = 100)
    private String especialidade;
    
    protected Barbeiro() { }

    public Barbeiro(String nome, String cpf, String telefone, String email, String especialidade) {
        super(nome, cpf, telefone, email);
        this.especialidade = especialidade;
    }

    @Override
    public String descreverFuncao() {
        return "Barbeiro: "+ getNome()+ ", especialidade: "+this.especialidade;
    } 
}
