/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.ifba.infrastructure.exception.NegocioException;
import br.com.ifba.pessoa.entity.Cliente;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.exception.UsuarioNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsuarioServiceTest {

    private FakeUsuarioDao dao;
    private UsuarioIService service;

    @BeforeEach
    void preparar() {
        dao = new FakeUsuarioDao();
        service = new UsuarioService(dao); // o dublê entra pelo construtor
    }

    private Usuario novoUsuario(String nome, String cpf, String login, String senha) {
        Cliente cliente = new Cliente(nome, cpf, "77999990000", "victor@email.com");
        return new Usuario(cliente, login, senha);
    }

    @Test
    void naoDeveAceitarSenhaCurta() {
        Usuario u = novoUsuario("Victor", "12345678901", "victor", "123");

        assertThrows(NegocioException.class, () -> service.save(u));
    }

    @Test
    void naoDeveAceitarLoginComPalavraProibida() {
        Usuario u = novoUsuario("Victor", "12345678901", "admin", "senha1234");

        assertThrows(NegocioException.class, () -> service.save(u));
    }

    @Test
    void naoDeveAceitarCpfInvalido() {
        Usuario u = novoUsuario("Victor", "123", "victor", "senha1234");

        assertThrows(NegocioException.class, () -> service.save(u));
    }

    @Test
    void naoDeveAceitarLoginRepetido() {
        service.save(novoUsuario("Victor", "12345678901", "victor", "senha1234"));
        Usuario repetido = novoUsuario("Outro", "11122233344", "victor", "senha5678");

        assertThrows(NegocioException.class, () -> service.save(repetido));
    }

    @Test
    void naoDeveAceitarNomeEmBranco() {
        Usuario u = novoUsuario("   ", "12345678901", "victor", "senha1234");

        assertThrows(NegocioException.class, () -> service.save(u));
    }

    @Test
    void deveSalvarUsuarioValidoEGerarId() {
        Usuario u = novoUsuario("Victor", "12345678901", "victor", "senha1234");

        assertDoesNotThrow(() -> service.save(u));
        assertNotNull(u.getId());
        assertEquals(1, service.findAll().size());
    }

    @Test
    void naoDeveGravarNadaQuandoARegraFalha() {
        Usuario u = novoUsuario("Victor", "12345678901", "victor", "123");

        assertThrows(NegocioException.class, () -> service.save(u));
        assertTrue(dao.findAll().isEmpty());
    }

    @Test
    void naoDeveAtualizarComNomeEmBranco() {
        Usuario u = service.save(novoUsuario("Victor", "12345678901", "victor", "senha1234"));
        u.getPessoa().setNome("  ");

        assertThrows(NegocioException.class, () -> service.update(u));
    }

    @Test
    void buscaEmBrancoDeveListarTodos() {
        service.save(novoUsuario("Victor", "12345678901", "victor", "senha1234"));
        service.save(novoUsuario("Ana", "98765432100", "ana", "senha5678"));

        assertEquals(2, service.findByLoginOrNome("  ").size());
    }

    @Test
    void deveLancarExcecaoAoBuscarLoginInexistente() {
        assertThrows(UsuarioNaoEncontradoException.class, () -> service.findByLogin("inexistente"));
    }
}
