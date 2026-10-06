# prg03victorthiago

## Resposta atividade 13 Sobrecarga — Usuario

O `Usuario` tem dois construtores que recebem `Pessoa`, `login` e `senha`: um sem perfil e outro que também recebe um `Perfil`. A versão sem perfil é usada no cadastro comum, quando a própria pessoa se cadastra pela tela e ainda não tem perfil definido. A versão com perfil é usada quando o admin cria o usuário dentro do sistema e já define o perfil dele na criação.

## Perfil (preparação para próxima etapa)

A classe `Perfil` guarda nome e descrição, e `Usuario` mantém uma lista
de perfis e um perfil ativo. Hoje isso ainda não altera o comportamento
do sistema — é a base para a próxima etapa, em que cada tipo de pessoa
(`Cliente`, `Barbeiro`, `Admin`) vai ter permissões diferentes nas
telas, conforme combinado com o professor.

## Busca por login: List vs Map

Com dez usuários, a busca percorrendo a List e a busca pelo Map são
praticamente instantâneas, a diferença é imperceptível. Com dez mil
usuários, a busca na List precisa percorrer, em média, metade da
lista até achar (O(n)), enquanto a busca no Map vai direto na posição
calculada pelo hashCode do login, sem crescer com o tamanho (O(1)).