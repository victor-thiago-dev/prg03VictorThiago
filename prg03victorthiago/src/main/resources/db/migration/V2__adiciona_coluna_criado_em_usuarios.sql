/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  victo
 * Created: 23 de out. de 2026
 */
-- V2__adiciona_coluna_criado_em_usuarios.sql
-- Novo requisito: saber quando cada usuário foi cadastrado.
-- O DEFAULT preenche a data sozinho, então os INSERTs do Hibernate continuam funcionando.
 
ALTER TABLE usuarios
    ADD COLUMN criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
 

