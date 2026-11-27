/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.ifba.infrastructure.exception.NegocioException;
import br.com.ifba.pessoa.entity.Cliente;
import br.com.ifba.usuario.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsuarioServiceLoginUnicoTest {

    // dublê que conta quais consultas o Service fez ao DAO
    private static class DaoEspiao extends FakeUsuarioDao {

        int consultasExistsByLogin = 0;
        int consultasFindByLogin = 0;

        @Override
        public boolean existsByLogin(String login) {
            consultasExistsByLogin++;
            return super.existsByLogin(login);
        }

        @Override
        public Usuario findByLogin(String login) {
            consultasFindByLogin++;
            return super.findByLogin(login);
        }
    }

    private DaoEspiao dao;
    private UsuarioIService service;

    @BeforeEach
    void preparar() {
        dao = new DaoEspiao();
        service = new UsuarioService(dao);
    }

    private Usuario novoUsuario(String cpf, String login) {
        Cliente cliente = new Cliente("Victor", cpf, "77999990000", "victor@email.com");
        return new Usuario(cliente, login, "senha1234");
    }

    @Test
    void deveVerificarLoginRepetidoSemBuscarOUsuarioInteiro() {
        service.save(novoUsuario("12345678901", "victor"));

        assertEquals(1, dao.consultasExistsByLogin);
        assertEquals(0, dao.consultasFindByLogin);
    }

    @Test
    void deveRecusarLoginRepetidoSemBuscarOUsuarioInteiro() {
        service.save(novoUsuario("12345678901", "victor"));
        Usuario repetido = novoUsuario("98765432100", "victor");

        assertThrows(NegocioException.class, () -> service.save(repetido));
        assertEquals(2, dao.consultasExistsByLogin);
        assertEquals(0, dao.consultasFindByLogin);
    }
}
