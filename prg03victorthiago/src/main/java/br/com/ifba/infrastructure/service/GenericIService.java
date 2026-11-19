/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.infrastructure.service;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import java.util.List;
import java.util.Optional;

public interface GenericIService<Entity extends PersistenceEntity> {

    Entity save(Entity entity);

    Entity update(Entity entity);

    void delete(Entity entity);

    Optional<Entity> findById(Long id);

    List<Entity> findAll();
}
