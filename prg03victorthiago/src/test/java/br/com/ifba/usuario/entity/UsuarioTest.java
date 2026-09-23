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
    
     @Test
    void deveNascerComStatusAtivo() {
        Usuario usuario = new Usuario("Victor", "12345678901", "victor", "senha1234");
        assertEquals(StatusUsuario.ATIVO, usuario.getStatus());
    }
 
    @Test
    void deveAlterarStatusParaInativo() {
        Usuario usuario = new Usuario("Victor", "12345678901", "victor", "senha1234");
        usuario.setStatus(StatusUsuario.INATIVO);
        assertEquals(StatusUsuario.INATIVO, usuario.getStatus());
    }
 
    @Test
    void deveAumentarListaDePerfisAoAdicionar() {
        Usuario usuario = new Usuario("Victor", "12345678901", "victor", "senha1234");
 
        usuario.adicionarPerfil(new Perfil(TipoUsuario.CLIENTE, "Cliente"));
 
        assertEquals(1, usuario.getPerfis().size());
    }
 
    @Test
    void devePermitirMaisDeUmPerfilParaOMesmoUsuario() {
        Usuario usuario = new Usuario("Ana", "98765432100", "ana.barbeira", "senhaForte1");
 
        usuario.adicionarPerfil(new Perfil(TipoUsuario.BARBEIRO, "Barbeira titular"));
        usuario.adicionarPerfil(new Perfil(TipoUsuario.CLIENTE, "Cliente da própria barbearia"));
 
        assertEquals(2, usuario.getPerfis().size());
    }
 
    @Test
    void deveLancarExcecaoAoTentarModificarListaDePerfisPorFora() {
        Usuario usuario = new Usuario("Victor", "12345678901", "victor", "senha1234");
        usuario.adicionarPerfil(new Perfil(TipoUsuario.CLIENTE, "Cliente"));
 
        assertThrows(UnsupportedOperationException.class,
            () -> usuario.getPerfis().add(new Perfil(TipoUsuario.ADMIN, "Admin")));
    }

    @Test
    void devePrimeiroPerfilVirarOAtivoAutomaticamente() {
        Usuario usuario = new Usuario("Victor", "12345678901", "victor", "senha1234");
        Perfil cliente = new Perfil(TipoUsuario.CLIENTE, "Cliente");
 
        usuario.adicionarPerfil(cliente);
 
        assertSame(cliente, usuario.getPerfilAtivo());
    }
 
    @Test
    void deveTrocarPerfilAtivoSeUsuarioPossuiOPerfil() {
        Usuario usuario = new Usuario("Ana", "98765432100", "ana.barbeira", "senhaForte1");
        Perfil barbeiro = new Perfil(TipoUsuario.BARBEIRO, "Barbeira titular");
        Perfil cliente = new Perfil(TipoUsuario.CLIENTE, "Cliente");
        usuario.adicionarPerfil(barbeiro);
        usuario.adicionarPerfil(cliente);
 
        usuario.setPerfilAtivo(cliente);
 
        assertSame(cliente, usuario.getPerfilAtivo());
    }
 
    @Test
    void deveLancarExcecaoSeAtivarPerfilQueUsuarioNaoPossui() {
        Usuario usuario = new Usuario("Victor", "12345678901", "victor", "senha1234");
        usuario.adicionarPerfil(new Perfil(TipoUsuario.CLIENTE, "Cliente"));
        Perfil admin = new Perfil(TipoUsuario.ADMIN, "Admin"); // nunca foi adicionado a esse usuário
 
        assertThrows(IllegalArgumentException.class, () -> usuario.setPerfilAtivo(admin));
    }
}