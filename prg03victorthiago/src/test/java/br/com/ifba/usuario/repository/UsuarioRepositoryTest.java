/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.ifba.pessoa.entity.Cliente;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.exception.UsuarioNaoEncontradoException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;

// banco em memória (H2): o schema vem das Entities, sem Flyway e sem MySQL
@DataJpaTest(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.flyway.enabled=false"
})
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repository;

    private Usuario salvar(String login, String nome, String cpf) {
        Cliente cliente = new Cliente(nome, cpf, "77999990000", login + "@email.com");
       return ((JpaRepository<Usuario, Long>) repository).save(new Usuario(cliente, login, "senha1234"));
    }

    @Test
    void existsByLoginDeveSerVerdadeiroParaLoginCadastrado() {
        salvar("victor", "Victor Santos", "11111111111");

        assertTrue(repository.existsByLogin("victor"));
    }

    @Test
    void existsByLoginDeveSerFalsoParaLoginInexistente() {
        assertFalse(repository.existsByLogin("naoexiste"));
    }

    @Test
    void findByLoginDeveLancarExcecaoQuandoOLoginNaoExiste() {
        assertThrows(UsuarioNaoEncontradoException.class, () -> repository.findByLogin("naoexiste"));
    }

    @Test
    void findByLoginOrNomeDeveAcharPorTrechoDoLoginOuDoNomeIgnorandoMaiusculas() {
        salvar("ana.lima", "Ana Lima", "11111111111");
        salvar("beatriz", "Beatriz Ana Costa", "22222222222");
        salvar("carlos", "Carlos Souza", "33333333333");

        List<Usuario> encontrados = repository.findByLoginOrNome("ANA");

        assertEquals(2, encontrados.size());
    }

    @Test
    void findByLoginOrNomeDeveOrdenarPorLogin() {
        salvar("zeca", "Zeca Dias", "11111111111");
        salvar("ana", "Ana Lima", "22222222222");
        salvar("maria", "Maria Souza", "33333333333");

        List<String> logins = repository.findByLoginOrNome("a").stream()
                .map(Usuario::getLogin)
                .toList();

        assertEquals(List.of("ana", "maria", "zeca"), logins);
    }
}
