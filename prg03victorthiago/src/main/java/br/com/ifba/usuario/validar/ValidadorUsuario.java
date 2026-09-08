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
}
