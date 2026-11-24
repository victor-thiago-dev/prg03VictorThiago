/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.validar;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ValidadorCpfTest {

    @Test
    void deveAceitarCpfSoComNumeros() {
        assertTrue(ValidadorCpf.valido("12345678901"));
    }

    @Test
    void deveAceitarCpfComPontosETraco() {
        assertTrue(ValidadorCpf.valido("123.456.789-01"));
    }

    @Test
    void deveRecusarCpfComMenosDeOnzeDigitos() {
        assertFalse(ValidadorCpf.valido("123"));
    }

    @Test
    void deveRecusarCpfNulo() {
        assertFalse(ValidadorCpf.valido(null));
    }
}