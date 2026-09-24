/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import br.com.ifba.pessoa.entity.Cliente;
import br.com.ifba.pessoa.entity.Barbeiro;
 
class UsuarioTest {
 
    @Test
    void deveRetornarTrueSeCredenciaisCorretas() {
        Cliente cliente = new Cliente("Victor", "12345678901", "77999990000", "victor@email.com");
        Usuario usuario = new Usuario(cliente, "victor", "senha1234");
        assertTrue(usuario.autenticar("victor", "senha1234"));
    }
 
    @Test
    void deveRetornarFalseSeSenhaIncorreta() {
        Cliente cliente = new Cliente("Victor", "12345678901", "77999990000", "victor@email.com");
        Usuario usuario = new Usuario(cliente, "victor", "senha1234");
        assertFalse(usuario.autenticar("victor", "senhaerrada"));
    }
 
    @Test
    void deveNascerComStatusAtivo() {
        Cliente cliente = new Cliente("Victor", "12345678901", "77999990000", "victor@email.com");
        Usuario usuario = new Usuario(cliente, "victor", "senha1234");
        assertEquals(StatusUsuario.ATIVO, usuario.getStatus());
    }
 
    @Test
    void deveAlterarStatusParaInativo() {
        Cliente cliente = new Cliente("Victor", "12345678901", "77999990000", "victor@email.com");
        Usuario usuario = new Usuario(cliente, "victor", "senha1234");
        usuario.setStatus(StatusUsuario.INATIVO);
        assertEquals(StatusUsuario.INATIVO, usuario.getStatus());
    }
 
    @Test
    void deveAumentarListaDePerfisAoAdicionar() {
        Cliente cliente = new Cliente("Victor", "12345678901", "77999990000", "victor@email.com");
        Usuario usuario = new Usuario(cliente, "victor", "senha1234");
 
        usuario.adicionarPerfil(new Perfil("Cliente", "Pode agendar serviços"));
 
        assertEquals(1, usuario.getPerfis().size());
    }
 
    @Test
    void devePermitirMaisDeUmPerfilParaOMesmoUsuario() {
        Barbeiro barbeiro = new Barbeiro("Ana", "98765432100", "77988887777", "ana@email.com", "Corte masculino");
        Usuario usuario = new Usuario(barbeiro, "ana.barbeira", "senhaForte1");
 
        usuario.adicionarPerfil(new Perfil("Barbeira", "Presta serviços e vê os próprios agendamentos"));
        usuario.adicionarPerfil(new Perfil("Cliente", "Pode agendar serviços"));
 
        assertEquals(2, usuario.getPerfis().size());
    }
 
    @Test
    void deveLancarExcecaoAoTentarModificarListaDePerfisPorFora() {
        Cliente cliente = new Cliente("Victor", "12345678901", "77999990000", "victor@email.com");
        Usuario usuario = new Usuario(cliente, "victor", "senha1234");
        usuario.adicionarPerfil(new Perfil("Cliente", "Pode agendar serviços"));
 
        assertThrows(UnsupportedOperationException.class,
            () -> usuario.getPerfis().add(new Perfil("Admin", "Acesso total ao sistema")));
    }
 
    @Test
    void devePrimeiroPerfilVirarOAtivoAutomaticamente() {
        Cliente cliente = new Cliente("Victor", "12345678901", "77999990000", "victor@email.com");
        Usuario usuario = new Usuario(cliente, "victor", "senha1234");
        Perfil perfilCliente = new Perfil("Cliente", "Pode agendar serviços");
 
        usuario.adicionarPerfil(perfilCliente);
 
        assertSame(perfilCliente, usuario.getPerfilAtivo());
    }
 
    @Test
    void deveTrocarPerfilAtivoSeUsuarioPossuiOPerfil() {
        Barbeiro barbeiro = new Barbeiro("Ana", "98765432100", "77988887777", "ana@email.com", "Corte masculino");
        Usuario usuario = new Usuario(barbeiro, "ana.barbeira", "senhaForte1");
        Perfil perfilBarbeiro = new Perfil("Barbeira", "Presta serviços e vê os próprios agendamentos");
        Perfil perfilCliente = new Perfil("Cliente", "Pode agendar serviços");
        usuario.adicionarPerfil(perfilBarbeiro);
        usuario.adicionarPerfil(perfilCliente);
 
        usuario.setPerfilAtivo(perfilCliente);
 
        assertSame(perfilCliente, usuario.getPerfilAtivo());
    }
 
    @Test
    void deveLancarExcecaoSeAtivarPerfilQueUsuarioNaoPossui() {
        Cliente cliente = new Cliente("Victor", "12345678901", "77999990000", "victor@email.com");
        Usuario usuario = new Usuario(cliente, "victor", "senha1234");
        usuario.adicionarPerfil(new Perfil("Cliente", "Pode agendar serviços"));
        Perfil admin = new Perfil("Admin", "Acesso total ao sistema"); // nunca foi adicionado a esse usuário
 
        assertThrows(IllegalArgumentException.class, () -> usuario.setPerfilAtivo(admin));
    }
}