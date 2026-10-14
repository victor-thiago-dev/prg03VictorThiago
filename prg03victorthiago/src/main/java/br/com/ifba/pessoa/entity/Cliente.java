/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
/**
 *
 * @author victo
 */
@Entity
@DiscriminatorValue("CLIENTE")
public class Cliente extends Pessoa{
    
    protected Cliente() { }
    
    public Cliente(String nome, String cpf, String telefone, String email) {
        super(nome, cpf, telefone, email);
    }

    @Override
    public String descreverFuncao() {
        return getNome() + " é cliente e agenda serviços na barbearia"; 
    }
}
