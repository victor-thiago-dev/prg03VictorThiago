/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.service;

import br.com.ifba.infrastructure.exception.NegocioException;
import br.com.ifba.pessoa.entity.Pessoa;
import br.com.ifba.pessoa.validar.ValidadorCpf;

final class RegrasPessoa {

    private RegrasPessoa() { }

    static void validar(Pessoa pessoa) {
        if (pessoa == null) {
            throw new NegocioException("Pessoa não pode ser nula.");
        }
        if (estaEmBranco(pessoa.getNome()) || estaEmBranco(pessoa.getCpf())
                || estaEmBranco(pessoa.getEmail())) {
            throw new NegocioException("Preencha nome, CPF e e-mail.");
        }
        if (!ValidadorCpf.valido(pessoa.getCpf())) {
            throw new NegocioException("CPF inválido.");
        }
    }

    private static boolean estaEmBranco(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
}