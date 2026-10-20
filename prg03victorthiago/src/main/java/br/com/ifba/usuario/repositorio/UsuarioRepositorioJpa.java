/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.usuario.repositorio;

import br.com.ifba.infrastructure.util.JPAUtil;
import br.com.ifba.usuario.entity.StatusUsuario;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.exception.UsuarioNaoEncontradoException;
import jakarta.persistence.EntityManager;
import java.util.List;

public class UsuarioRepositorioJpa {

    // CREATE: persist dentro de transação
    public void cadastrar(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo.");
        }
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Long existentes = em.createQuery(
                    "select count(u) from Usuario u where u.login = :login", Long.class)
                    .setParameter("login", usuario.getLogin())
                    .getSingleResult();
            if (existentes > 0) {
                throw new IllegalArgumentException("Já existe um usuário com esse login.");
            }
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // READ: lista tudo
    public List<Usuario> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "select u from Usuario u join fetch u.pessoa order by u.login", Usuario.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // Task 07: busca com JPQL e where (por login ou nome, sem diferenciar maiúsculas)
    public List<Usuario> buscarPorTexto(String texto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "select u from Usuario u join fetch u.pessoa p "
                    + "where lower(u.login) like :texto or lower(p.nome) like :texto "
                    + "order by u.login", Usuario.class)
                    .setParameter("texto", "%" + texto.toLowerCase() + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // lança a exceção em vez de devolver null
    public Usuario buscarPorLogin(String login) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return buscarNoEm(em, login);
        } finally {
            em.close();
        }
    }

    // UPDATE: busca, altera e faz merge dentro de transação
    public void atualizar(String login, String novoNome, StatusUsuario novoStatus) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Usuario usuario = buscarNoEm(em, login);
            usuario.getPessoa().setNome(novoNome);
            usuario.setStatus(novoStatus);
            em.merge(usuario);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // DELETE: remove dentro de transação (o cascade apaga a Pessoa junto)
    public void remover(String login) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Usuario usuario = buscarNoEm(em, login);
            em.remove(usuario);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    private Usuario buscarNoEm(EntityManager em, String login) {
        List<Usuario> encontrados = em.createQuery(
                "select u from Usuario u join fetch u.pessoa where u.login = :login", Usuario.class)
                .setParameter("login", login)
                .getResultList();
        if (encontrados.isEmpty()) {
            throw new UsuarioNaoEncontradoException(login);
        }
        return encontrados.get(0);
    }
}
