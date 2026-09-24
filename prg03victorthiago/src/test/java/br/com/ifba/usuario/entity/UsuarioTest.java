/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import br.com.ifba.cliente.entity.Cliente;
import br.com.ifba.barbeiro.entity.Barbeiro;
 
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
 
        usuario.adicionarPerfil(new Perfil(TipoUsuario.CLIENTE, "Cliente"));
 
        assertEquals(1, usuario.getPerfis().size());
    }
 
    @Test
    void devePermitirMaisDeUmPerfilParaOMesmoUsuario() {
        Barbeiro barbeiro = new Barbeiro("Ana", "98765432100", "77988887777", "ana@email.com", "Corte masculino");
        Usuario usuario = new Usuario(barbeiro, "ana.barbeira", "senhaForte1");
 
        usuario.adicionarPerfil(new Perfil(TipoUsuario.BARBEIRO, "Barbeira titular"));
        usuario.adicionarPerfil(new Perfil(TipoUsuario.CLIENTE, "Cliente da própria barbearia"));
 
        assertEquals(2, usuario.getPerfis().size());
    }
 
    @Test
    void deveLancarExcecaoAoTentarModificarListaDePerfisPorFora() {
        Cliente cliente = new Cliente("Victor", "12345678901", "77999990000", "victor@email.com");
        Usuario usuario = new Usuario(cliente, "victor", "senha1234");
        usuario.adicionarPerfil(new Perfil(TipoUsuario.CLIENTE, "Cliente"));
 
        assertThrows(UnsupportedOperationException.class,
            () -> usuario.getPerfis().add(new Perfil(TipoUsuario.ADMIN, "Admin")));
    }
 
    @Test
    void devePrimeiroPerfilVirarOAtivoAutomaticamente() {
        Cliente cliente = new Cliente("Victor", "12345678901", "77999990000", "victor@email.com");
        Usuario usuario = new Usuario(cliente, "victor", "senha1234");
        Perfil perfilCliente = new Perfil(TipoUsuario.CLIENTE, "Cliente");
 
        usuario.adicionarPerfil(perfilCliente);
 
        assertSame(perfilCliente, usuario.getPerfilAtivo());
    }
 
    @Test
    void deveTrocarPerfilAtivoSeUsuarioPossuiOPerfil() {
        Barbeiro barbeiro = new Barbeiro("Ana", "98765432100", "77988887777", "ana@email.com", "Corte masculino");
        Usuario usuario = new Usuario(barbeiro, "ana.barbeira", "senhaForte1");
        Perfil perfilBarbeiro = new Perfil(TipoUsuario.BARBEIRO, "Barbeira titular");
        Perfil perfilCliente = new Perfil(TipoUsuario.CLIENTE, "Cliente");
        usuario.adicionarPerfil(perfilBarbeiro);
        usuario.adicionarPerfil(perfilCliente);
 
        usuario.setPerfilAtivo(perfilCliente);
 
        assertSame(perfilCliente, usuario.getPerfilAtivo());
    }
 
    @Test
    void deveLancarExcecaoSeAtivarPerfilQueUsuarioNaoPossui() {
        Cliente cliente = new Cliente("Victor", "12345678901", "77999990000", "victor@email.com");
        Usuario usuario = new Usuario(cliente, "victor", "senha1234");
        usuario.adicionarPerfil(new Perfil(TipoUsuario.CLIENTE, "Cliente"));
        Perfil admin = new Perfil(TipoUsuario.ADMIN, "Admin"); // nunca foi adicionado a esse usuário
 
        assertThrows(IllegalArgumentException.class, () -> usuario.setPerfilAtivo(admin));
    }
}