/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.validar;

public final class ValidadorCpf {

    private ValidadorCpf() { }

    // aceita 11 dígitos, com ou sem pontos e traço (123.456.789-01)
    public static boolean valido(String cpf) {
        if (cpf == null) {
            return false;
        }
        String apenasNumeros = cpf.trim().replaceAll("[.\\-]", "");
        return apenasNumeros.matches("\\d{11}");
    }
}
