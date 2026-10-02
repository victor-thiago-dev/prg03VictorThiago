/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.validar;
import br.com.ifba.pessoa.entity.Cliente;
import br.com.ifba.usuario.entity.Usuario;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
 
class ValidadorUsuarioTest {
 
    // ---------- cpfValido ----------
    @Test 
    void deveRetornarTrueCpfValidoNumeros(){ 
        assertTrue(ValidadorUsuario.cpfValido("12345678901")); 
    }
    
    @Test 
    void deveRetornarTrueCpfValidoComMascara(){ 
        assertTrue(ValidadorUsuario.cpfValido("123.456.789-01")); 
    }
    
    @Test 
    void deveRetornarFalseCpfVazio(){ 
        assertFalse(ValidadorUsuario.cpfValido("")); 
    }
    
    @Test 
    void deveRetornarFalseCpfComLetras(){ 
        assertFalse(ValidadorUsuario.cpfValido("1234567890a")); 
    }
    
    @Test 
    void deveRetornarFalseCpfCurto(){ 
        assertFalse(ValidadorUsuario.cpfValido("123456789")); 
    }
    
    @Test 
    void deveRetornarFalseCpfNull(){ 
        assertFalse(ValidadorUsuario.cpfValido(null)); 
    }
 
    // ---------- senhaForte ----------
    @Test 
    void deveRetornarTrueSenhaValida(){ 
        assertTrue(ValidadorUsuario.senhaForte("abcd1234")); 
    }
    
    @Test 
    void deveRetornarFalseSenhaCurta(){ 
        assertFalse(ValidadorUsuario.senhaForte("abc1234"));  // 7 caracteres 
    }
    
    @Test 
    void deveRetornarFalseSenhaVazia(){ 
        assertFalse(ValidadorUsuario.senhaForte("")); 
    }
    
    @Test 
    void deveRetornarFalseSenhaNull(){ 
        assertFalse(ValidadorUsuario.senhaForte(null)); 
    }
 
    // ---------- camposPreenchidos ----------
    @Test 
    void deveRetornarTrueCamposTodosPreenchidos(){ 
        assertTrue(ValidadorUsuario.camposPreenchidos("Victor", "123", "victor")); 
    }
    
    @Test 
    void deveRetornarFalseCampoVazio(){ 
        assertFalse(ValidadorUsuario.camposPreenchidos("Victor", "", "victor")); 
    }
    
    @Test 
    void deveRetornarFalseCampoSoEspacos(){ 
        assertFalse(ValidadorUsuario.camposPreenchidos("Victor", "   ", "Victor")); 
    }
    
    @Test 
    void deveRetornarFalseCampoNull(){ 
        assertFalse(ValidadorUsuario.camposPreenchidos("Victor", null, "victor")); 
    }
 
    // ---------- senhasIguais ----------
    @Test void deveRetornarTrueSenhasIguais(){ 
        assertTrue(ValidadorUsuario.senhasIguais("abc", "abc")); 
    }
    
    @Test void deveRetornarFalseSenhasDiferentes(){ 
        assertFalse(ValidadorUsuario.senhasIguais("abc", "abd")); 
    }
    
    @Test 
    void deveRetornarFalseSenhasNull(){ 
        assertFalse(ValidadorUsuario.senhasIguais(null, "abc")); 
    }
 
    // ---------- contemPalavraProibida ----------
    @Test 
    void deveRetornarTrueContemPalavraProibida(){ 
        assertTrue(ValidadorUsuario.contemPalavraProibida("admin")); 
    }
    
    @Test 
    void deveRetornarFalseContemPalavraPermitida(){ 
        assertFalse(ValidadorUsuario.contemPalavraProibida("victor")); 
    }
    
    @Test 
    void deveRetornarFalsePalavraProibidaNull(){ 
        assertFalse(ValidadorUsuario.contemPalavraProibida(null)); 
    }
    
    @Test
    void deveLancarExcecaoSeSetSenhaReceberSenhaFraca() {
        Cliente cliente = new Cliente("Victor", "12345678901", "77999990000", "victor@email.com");
        Usuario usuario = new Usuario(cliente, "victor", "senha1234");

        assertThrows(IllegalArgumentException.class, () -> usuario.setSenha("123"));
    }
    
    @Test
    void deveLancarExcecaoSeSetLoginReceberPalavraProibida() {
        Cliente cliente = new Cliente("Victor", "12345678901", "77999990000", "victor@email.com");
        Usuario usuario = new Usuario(cliente, "victor", "senha1234");

        assertThrows(IllegalArgumentException.class, () -> usuario.setLogin("admin"));
    }
    
     @Test
    void deveRetornarTrueSeContemPalavraProibidaCaixaAlta() {
        assertTrue(ValidadorUsuario.contemPalavraProibida("ADMIN"));
    }

    @Test
    void deveRetornarFalseSePalavraProibidaEstaDentroDeOutroTexto() {
        assertFalse(ValidadorUsuario.contemPalavraProibida("admin123"));
    }
}