/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.repositorio;

import br.com.ifba.pessoa.entity.Cliente;
import br.com.ifba.usuario.entity.Usuario;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
 
class RepositorioUsuarioEmMemoriaTest {
 
    @Test
    void deveApareceEmListarTodosAoCadastrarUmUsuario() {
        RepositorioUsuarioEmMemoria repositorio = new RepositorioUsuarioEmMemoria();
        Cliente cliente = new Cliente("Victor", "12345678901", "77999990000", "victor@email.com");
        Usuario usuario = new Usuario(cliente, "victor", "senha1234");
 
        repositorio.cadastrar(usuario);
 
        assertTrue(repositorio.listarTodos().contains(usuario));
    }
 
    @Test
    void deveDevolverOUsuarioCertoAoBuscarPorLoginComDoisCadastrados() {
        RepositorioUsuarioEmMemoria repositorio = new RepositorioUsuarioEmMemoria();
        Cliente cliente1 = new Cliente("Victor", "12345678901", "77999990000", "victor@email.com");
        Cliente cliente2 = new Cliente("Ana", "98765432100", "77988887777", "ana@email.com");
        Usuario usuario1 = new Usuario(cliente1, "victor", "senha1234");
        Usuario usuario2 = new Usuario(cliente2, "ana", "outraSenha1");
 
        repositorio.cadastrar(usuario1);
        repositorio.cadastrar(usuario2);
 
        assertEquals(usuario1, repositorio.buscarPorLogin("victor"));
        assertEquals(usuario2, repositorio.buscarPorLogin("ana"));
    }
 
    @Test
    void deveRetornarNullAoBuscarPorLoginQueNaoExiste() {
        RepositorioUsuarioEmMemoria repositorio = new RepositorioUsuarioEmMemoria();
 
        assertNull(repositorio.buscarPorLogin("naoExiste"));
    }
 
    @Test
    void deveLancarExcecaoAoCadastrarLoginDuplicado() {
        RepositorioUsuarioEmMemoria repositorio = new RepositorioUsuarioEmMemoria();
        Cliente cliente1 = new Cliente("Victor", "12345678901", "77999990000", "victor@email.com");
        Cliente cliente2 = new Cliente("Outro Victor", "11122233344", "77977776666", "outro@email.com");
        Usuario usuario1 = new Usuario(cliente1, "victor", "senha1234");
        Usuario usuario2 = new Usuario(cliente2, "victor", "senhaDiferente1");
 
        repositorio.cadastrar(usuario1);
 
        assertThrows(IllegalArgumentException.class, () -> repositorio.cadastrar(usuario2));
    }
 
    // Task 02 - prova do equals: dois objetos diferentes, mesmo login, são iguais pra lista
    @Test
    void doisUsuariosDiferentesComMesmoLoginDevemSerIguaisParaAColecao() {
        Cliente cliente1 = new Cliente("Victor", "12345678901", "77999990000", "victor@email.com");
        Cliente cliente2 = new Cliente("Victor Duplicado", "22233344455", "77966665555", "victor2@email.com");
        Usuario usuario1 = new Usuario(cliente1, "victor", "senha1234");
        Usuario usuario2 = new Usuario(cliente2, "victor", "outraSenha1");
 
        assertEquals(usuario1, usuario2);
        assertTrue(java.util.List.of(usuario1).contains(usuario2));
    }
}
