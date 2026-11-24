/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.validar;

import br.com.ifba.pessoa.validar.ValidadorCpf;

/**
 *
 * @author victo
 */
public class ValidadorUsuario {
    
    public static final int TAMANHO_MINIMO_SENHA = 8;
     
    public static boolean contemPalavraProibida(String texto){
        if (texto == null) {
        return false;
        }
        String[] palavrasProibidas = {"admin", "administrador", "root", "support", "moderator", "webmaster",
        "teste", "senha123"};
        for (String palavraProibida : palavrasProibidas) {
            // ignora maiúsculas: "Admin" e "ADMIN" não podem se passar por "admin"
            if(texto.equalsIgnoreCase(palavraProibida)){
                return true;
            }
        }
        return false;
    }
    
     // true somente se TODOS os campos forem não nulos e não vazios
    public static boolean camposPreenchidos(String... campos) {
        if (campos == null) {
            return false;
        }
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
 
    // aceita 11 dígitos, com ou sem pontos e traço (123.456.789-01)
    public static boolean cpfValido(String cpf) {
        return ValidadorCpf.valido(cpf);
    }
 
    public static boolean senhaForte(String senha) {
        return senha != null && senha.length() >= TAMANHO_MINIMO_SENHA;
    }
 
    public static boolean senhasIguais(String senha, String confirmarSenha) {
        return senha != null && senha.equals(confirmarSenha);
    }
}
