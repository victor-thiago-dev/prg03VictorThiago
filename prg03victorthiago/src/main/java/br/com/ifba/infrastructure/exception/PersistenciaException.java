/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.exception;

/**
 *
 * @author victo
 */
public class PersistenciaException extends RuntimeException {

    public PersistenciaException(String operacao, Class<?> entidade, Throwable causa) {
        super("Não foi possível " + operacao + " " + entidade.getSimpleName()
                + ". Verifique os dados informados e a conexão com o banco de dados.", causa);
    }
}