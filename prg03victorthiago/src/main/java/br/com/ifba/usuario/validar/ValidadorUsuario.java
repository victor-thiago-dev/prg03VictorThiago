/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.validar;

/**
 *
 * @author victo
 */
public class ValidadorUsuario {
    
    public static final int TAMANHO_MINIMO_SENHA = 8;
     
    public static boolean contemPalavraProibida(String texto){
        //declara array com palavras proibidas
        String[] palavrasProibidas = {"admin", "administrador", "root", "support", "moderator", "webmaster",
        "teste", "senha123"};
        //compara se o texto é igual a alguma palavra proibida
        for (String palavraProibida : palavrasProibidas) {
            if(texto.equals(palavraProibida)){
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
        if (cpf == null) {
            return false;
        }
        String limpo = cpf.trim().replaceAll("[.\\-]", "");
        return limpo.matches("\\d{11}");
    }
 
    public static boolean senhaForte(String senha) {
        return senha != null && senha.length() >= TAMANHO_MINIMO_SENHA;
    }
 
    public static boolean senhasIguais(String senha, String confirmarSenha) {
        return senha != null && senha.equals(confirmarSenha);
    }
}
