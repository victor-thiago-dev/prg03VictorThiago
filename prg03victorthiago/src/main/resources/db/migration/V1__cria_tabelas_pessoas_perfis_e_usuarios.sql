/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  victo
 * Created: 23 de out. de 2026
 */
-- V1__cria_tabelas_pessoas_perfis_e_usuarios.sql
-- Estrutura inicial, escrita a partir das Entities (Pessoa, Perfil, Usuario).
-- Pessoa usa SINGLE_TABLE: Cliente, Barbeiro e Admin ficam todos em "pessoas",
-- diferenciados pela coluna discriminadora tipo_pessoa.
 
CREATE TABLE pessoas (
    id            BIGINT       AUTO_INCREMENT PRIMARY KEY,
    tipo_pessoa   VARCHAR(31)  NOT NULL,
    nome          VARCHAR(100) NOT NULL,
    cpf           VARCHAR(14)  NOT NULL,
    telefone      VARCHAR(20),
    email         VARCHAR(100) NOT NULL,
    especialidade VARCHAR(100)   -- só o Barbeiro usa; Cliente e Admin deixam NULL
);
 
CREATE TABLE perfis (
    id        BIGINT       AUTO_INCREMENT PRIMARY KEY,
    nome      VARCHAR(50)  NOT NULL,
    descricao VARCHAR(255),
    CONSTRAINT uk_perfis_nome UNIQUE (nome)
);
 
CREATE TABLE usuarios (
    id        BIGINT       AUTO_INCREMENT PRIMARY KEY,
    login     VARCHAR(50)  NOT NULL,
    senha     VARCHAR(255) NOT NULL,
    status    ENUM('ATIVO', 'INATIVO') NOT NULL,
    pessoa_id BIGINT       NOT NULL,
    CONSTRAINT uk_usuarios_login  UNIQUE (login),
    CONSTRAINT fk_usuarios_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoas (id)
);
 

