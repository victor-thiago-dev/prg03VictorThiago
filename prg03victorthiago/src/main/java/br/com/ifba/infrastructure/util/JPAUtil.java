/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class JPAUtil {

    private static final String PERSISTENCE_UNIT = "gerenciamento_usuario";
    private static EntityManagerFactory fabrica;

    private JPAUtil() { }

    // cria a fábrica só na primeira vez; as demais reaproveitam a mesma
    private static synchronized EntityManagerFactory getFabrica() {
        if (fabrica == null || !fabrica.isOpen()) {
            fabrica = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        return fabrica;
    }

    public static EntityManager getEntityManager() {
        return getFabrica().createEntityManager();
    }

    // chamar ao fechar a aplicação
    public static synchronized void fechar() {
        if (fabrica != null && fabrica.isOpen()) {
            fabrica.close();
        }
    }
}
