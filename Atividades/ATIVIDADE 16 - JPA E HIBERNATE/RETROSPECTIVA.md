# Retrospectiva

**Task 06 (pergunta do DTO):** numa listagem de `Usuario` não devem aparecer `senha` (dado sensível), `pessoa` (arrasta CPF, telefone e e-mail) nem `perfis`/`perfilAtivo` (relacionamentos internos de permissão).
Só `login`, `status` e o nome da pessoa fazem sentido para a tela, e esses três campos serão o `UsuarioDTO` quando implementarmos.
