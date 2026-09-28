/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.pessoa.entity;
 
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.mycompany.prg03victorthiago.Prg03victorthiago;
 
class PessoaTest {
 
    // ---------- Task 01/02 - cada subclasse responde do seu jeito ----------
 
    @Test
    void deveDescreverFuncaoDeClienteComSeuNome() {
        Pessoa pessoa = new Cliente("Maria", "11111111111", "74999999999", "123@gmail.com");
 
        String resultado = pessoa.descreverFuncao();
 
        assertTrue(resultado.contains("Maria"));
    }
 
    @Test
    void deveDescreverFuncaoDeBarbeiroComSuaEspecialidade() {
        Pessoa pessoa = new Barbeiro("Thiago", "11111111111", "74999999999", "123@gmail.com", "Corte Masculino");
 
        String resultado = pessoa.descreverFuncao();
 
        assertTrue(resultado.contains("Thiago"));
        assertTrue(resultado.contains("Corte Masculino"));
    }
 
    @Test
    void deveDescreverFuncaoDeAdminComSeuNome() {
        Pessoa pessoa = new Admin("Victor", "11111111111", "74999999999", "123@gmail.com");
 
        String resultado = pessoa.descreverFuncao();
 
        assertTrue(resultado.contains("Victor"));
    }
 
    @Test
    void deveRetornarDescricoesDiferentesParaClasseDiferentes() {
        Pessoa cliente = new Cliente("Maria", "11111111111", "74999999999", "123@gmail.com");
        Pessoa barbeiro = new Barbeiro("Thiago", "22222222222", "74999999999", "123@gmail.com", "Corte Masculino");
 
        // mesma chamada, tipo geral, mas cada objeto responde diferente - isso é o polimorfismo
        assertNotEquals(cliente.descreverFuncao(), barbeiro.descreverFuncao());
    }
 
    // ---------- Task 03 - método que recebe o tipo geral ----------
 
    @Test
    void deveApresentarClienteUsandoOTipoGeralPessoa() {
        Pessoa cliente = new Cliente("Maria", "11111111111", "74999999999", "123@gmail.com");
 
        String resultado = Prg03victorthiago.apresentarPessoa(cliente);
 
        assertEquals(cliente.descreverFuncao(), resultado);
    }
 
    @Test
    void deveApresentarBarbeiroUsandoOTipoGeralPessoa() {
        Pessoa barbeiro = new Barbeiro("Thiago", "22222222222", "74999999999", "123@gmail.com", "Corte Masculino");
 
        String resultado = Prg03victorthiago.apresentarPessoa(barbeiro);
 
        assertEquals(barbeiro.descreverFuncao(), resultado);
    }
}