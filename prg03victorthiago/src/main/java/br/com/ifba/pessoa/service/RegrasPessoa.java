/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.service;

import br.com.ifba.infrastructure.exception.NegocioException;
import br.com.ifba.pessoa.entity.Pessoa;
import br.com.ifba.usuario.validar.ValidadorUsuario;

final class RegrasPessoa {

    private RegrasPessoa() { }

    static void validar(Pessoa pessoa) {
        if (pessoa == null) {
            throw new NegocioException("Pessoa não pode ser nula.");
        }
        if (!ValidadorUsuario.camposPreenchidos(pessoa.getNome(), pessoa.getCpf(), pessoa.getEmail())) {
            throw new NegocioException("Preencha nome, CPF e e-mail.");
        }
        if (!ValidadorUsuario.cpfValido(pessoa.getCpf())) {
            throw new NegocioException("CPF inválido.");
        }
    }
}