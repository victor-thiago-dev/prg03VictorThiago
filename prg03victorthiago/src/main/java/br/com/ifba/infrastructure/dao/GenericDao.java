/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.dao;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import br.com.ifba.infrastructure.exception.PersistenciaException;
import br.com.ifba.infrastructure.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class GenericDao<Entity extends PersistenceEntity> implements GenericIDao<Entity> {

    private final Class<Entity> tipo;
    private final Supplier<EntityManager> fornecedorEm;

    public GenericDao(Class<Entity> tipo) {
        this(tipo, JPAUtil::getEntityManager);
    }

    // permite trocar a origem do EntityManager (usado nos testes, sem banco)
    protected GenericDao(Class<Entity> tipo, Supplier<EntityManager> fornecedorEm) {
        this.tipo = tipo;
        this.fornecedorEm = fornecedorEm;
    }

    @Override
    public Entity save(Entity entity) {
        EntityManager em = null;
        try {
            em = fornecedorEm.get();
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return entity;
        } catch (RuntimeException e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("salvar", tipo, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Entity update(Entity entity) {
        EntityManager em = null;
        try {
            if (entity.getId() == null) {
                throw new IllegalArgumentException("o registro ainda não foi salvo");
            }
            em = fornecedorEm.get();
            em.getTransaction().begin();
            Entity atualizada = em.merge(entity);
            em.getTransaction().commit();
            return atualizada;
        } catch (RuntimeException e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("atualizar", tipo, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void delete(Entity entity) {
        EntityManager em = null;
        try {
            em = fornecedorEm.get();
            em.getTransaction().begin();
            Entity gerenciada = em.find(tipo, entity.getId());
            if (gerenciada == null) {
                throw new IllegalArgumentException("registro não encontrado (id " + entity.getId() + ")");
            }
            em.remove(gerenciada);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("excluir", tipo, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Optional<Entity> findById(Long id) {
        EntityManager em = null;
        try {
            em = fornecedorEm.get();
            return Optional.ofNullable(em.find(tipo, id));
        } catch (RuntimeException e) {
            throw new PersistenciaException("buscar", tipo, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Entity> findAll() {
        return consultar("select e from " + tipo.getSimpleName() + " e");
    }

    // usado pelos DAOs específicos: consultar(jpql, "nome1", valor1, "nome2", valor2...)
    protected List<Entity> consultar(String jpql, Object... nomesEValores) {
        EntityManager em = null;
        try {
            em = fornecedorEm.get();
            TypedQuery<Entity> consulta = em.createQuery(jpql, tipo);
            for (int i = 0; i + 1 < nomesEValores.length; i += 2) {
                consulta.setParameter((String) nomesEValores[i], nomesEValores[i + 1]);
            }
            return consulta.getResultList();
        } catch (RuntimeException e) {
            throw new PersistenciaException("consultar", tipo, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}