/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.ifba.infrastructure.exception.PersistenciaException;
import br.com.ifba.pessoa.entity.Cliente;
import br.com.ifba.usuario.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class GenericDaoTest {

    // EntityManager de mentira: só anota o que o DAO chamou, sem tocar em banco
    private static class EmFalso {

        final List<String> chamadas = new ArrayList<>();
        private final boolean falharNoPersist;
        private boolean transacaoAtiva = false;

        EmFalso(boolean falharNoPersist) {
            this.falharNoPersist = falharNoPersist;
        }

        EntityManager criar() {
            EntityTransaction transacao = (EntityTransaction) Proxy.newProxyInstance(
                    getClass().getClassLoader(), new Class<?>[]{EntityTransaction.class},
                    (proxy, metodo, args) -> {
                        String nome = metodo.getName();
                        if (nome.equals("begin")) {
                            transacaoAtiva = true;
                            chamadas.add("begin");
                        } else if (nome.equals("commit")) {
                            transacaoAtiva = false;
                            chamadas.add("commit");
                        } else if (nome.equals("rollback")) {
                            transacaoAtiva = false;
                            chamadas.add("rollback");
                        } else if (nome.equals("isActive")) {
                            return transacaoAtiva;
                        }
                        return null;
                    });

            return (EntityManager) Proxy.newProxyInstance(
                    getClass().getClassLoader(), new Class<?>[]{EntityManager.class},
                    (proxy, metodo, args) -> {
                        String nome = metodo.getName();
                        if (nome.equals("getTransaction")) {
                            return transacao;
                        } else if (nome.equals("persist")) {
                            chamadas.add("persist");
                            if (falharNoPersist) {
                                throw new IllegalStateException("falha simulada");
                            }
                        } else if (nome.equals("merge")) {
                            chamadas.add("merge");
                            return args[0];
                        } else if (nome.equals("find")) {
                            chamadas.add("find");
                            return null; // nada encontrado
                        } else if (nome.equals("close")) {
                            chamadas.add("close");
                        }
                        return null;
                    });
        }
    }

    private Usuario novoUsuario() {
        Cliente cliente = new Cliente("João Silva", "12345678901", "74999990000", "joao@email.com");
        return new Usuario(cliente, "joao.silva", "senha1234");
    }

    @Test
    void saveDeveLancarPersistenciaExceptionQuandoOPersistFalha() {
        EmFalso em = new EmFalso(true);
        GenericDao<Usuario> dao = new GenericDao<>(Usuario.class, em::criar);

        assertThrows(PersistenciaException.class, () -> dao.save(novoUsuario()));
        assertTrue(em.chamadas.contains("rollback"));
        assertTrue(em.chamadas.contains("close"));
        assertFalse(em.chamadas.contains("commit"));
    }

    @Test
    void saveNaoDeveLancarExcecaoNoCaminhoNormal() {
        EmFalso em = new EmFalso(false);
        GenericDao<Usuario> dao = new GenericDao<>(Usuario.class, em::criar);

        assertDoesNotThrow(() -> dao.save(novoUsuario()));
        assertEquals(List.of("begin", "persist", "commit", "close"), em.chamadas);
    }

    @Test
    void deleteDeveLancarPersistenciaExceptionQuandoORegistroNaoExiste() {
        EmFalso em = new EmFalso(false);
        GenericDao<Usuario> dao = new GenericDao<>(Usuario.class, em::criar);
        Usuario usuario = novoUsuario();
        usuario.setId(99L);

        assertThrows(PersistenciaException.class, () -> dao.delete(usuario));
        assertTrue(em.chamadas.contains("rollback"));
        assertTrue(em.chamadas.contains("close"));
    }

    @Test
    void updateDeveLancarPersistenciaExceptionQuandoOUsuarioNaoFoiSalvo() {
        EmFalso em = new EmFalso(false);
        GenericDao<Usuario> dao = new GenericDao<>(Usuario.class, em::criar);

        assertThrows(PersistenciaException.class, () -> dao.update(novoUsuario()));
    }

    @Test
    void mensagemDaExcecaoDeveCitarAEntidade() {
        EmFalso em = new EmFalso(true);
        GenericDao<Usuario> dao = new GenericDao<>(Usuario.class, em::criar);

        PersistenciaException e = assertThrows(PersistenciaException.class,
                () -> dao.save(novoUsuario()));

        assertTrue(e.getMessage().contains("Usuario"));
    }

    @Test
    void deveLancarPersistenciaExceptionQuandoNaoConseguePegarOEntityManager() {
        GenericDao<Usuario> dao = new GenericDao<>(Usuario.class, () -> {
            throw new IllegalStateException("sem banco");
        });

        assertThrows(PersistenciaException.class, () -> dao.save(novoUsuario()));
    }
}