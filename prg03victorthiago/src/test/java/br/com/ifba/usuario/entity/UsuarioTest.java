/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
 
class UsuarioTest {
 
    @Test
    void deveRetornarTrueSeCredenciaisCorretas() {
        Usuario usuario = new Usuario("Victor", "12345678901", "victor", "senha1234");
        assertTrue(usuario.autenticar("victor", "senha1234"));
    }
 
    @Test
    void deveRetornarFalseSeSenhaIncorreta() {
        Usuario usuario = new Usuario("Victor", "12345678901", "victor", "senha1234");
        assertFalse(usuario.autenticar("victor", "senhaerrada"));
    }
}