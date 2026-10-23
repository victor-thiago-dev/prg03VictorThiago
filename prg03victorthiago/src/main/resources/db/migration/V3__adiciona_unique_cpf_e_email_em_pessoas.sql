/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  victo
 * Created: 23 de out. de 2026
 */
 -- V3__adiciona_unique_cpf_e_email_em_pessoas.sql
-- A Entity Pessoa declara cpf e email como unique = true; o banco passa a garantir isso.
-- Sem esta restrição, dois cadastros com o mesmo CPF ou e-mail seriam aceitos pelo MySQL.
 
ALTER TABLE pessoas
    ADD CONSTRAINT uk_pessoas_cpf   UNIQUE (cpf),
    ADD CONSTRAINT uk_pessoas_email UNIQUE (email);
 
