/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prg03victorthiago;

import br.com.ifba.pessoa.entity.Admin;
import br.com.ifba.pessoa.entity.Barbeiro;
import br.com.ifba.pessoa.entity.Cliente;
import br.com.ifba.pessoa.entity.Pessoa;

/**
 *
 * @author victo
 */
public class Prg03victorthiago {

    public static void main(String[] args) {
        Pessoa administrador = new Admin("Victor", "11111111111", "74999999999", "123@gmail.com");
        Pessoa barbeiro = new Barbeiro("Thiago", "11111111111", "74999999999", "123@gmail.com", "Corte Masculino");
        Pessoa cliente = new Cliente("Maria", "11111111111", "74999999999", "123@gmail.com");
        
        System.out.println(administrador.descreverFuncao());
        System.out.println(barbeiro.descreverFuncao());
        System.out.println(cliente.descreverFuncao());
        
        
    }
}
