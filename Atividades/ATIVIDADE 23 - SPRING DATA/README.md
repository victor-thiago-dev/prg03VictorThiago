# Atividade 23: Spring Data JPA

## git diff --stat do commit que apagou os DAOs

Commit `cae09a3` ("Remove todos os daos"):

```
 .../br/com/ifba/infrastructure/dao/GenericDao.java | 138 --------------------
 .../com/ifba/infrastructure/dao/GenericIDao.java   |  22 ----
 .../exception/PersistenciaException.java           |  17 ---
 .../main/java/br/com/ifba/pessoa/dao/AdminDao.java |  25 ----
 .../java/br/com/ifba/pessoa/dao/BarbeiroDao.java   |  25 ----
 .../java/br/com/ifba/pessoa/dao/BarbeiroIDao.java  |  16 ++-
 .../java/br/com/ifba/pessoa/dao/ClienteDao.java    |  25 ----
 .../java/br/com/ifba/pessoa/dao/PessoaDao.java     |  25 ----
 .../java/br/com/ifba/usuario/dao/PerfilDao.java    |  25 ----
 .../java/br/com/ifba/usuario/dao/UsuarioDao.java   |  60 ---------
 .../ifba/infrastructure/dao/GenericDaoTest.java    | 145 ---------------------
 11 files changed, 13 insertions(+), 510 deletions(-)
```

**Linhas removidas no total: 510** (neste commit).

Somando os 5 commits da refatoração (`git diff --shortstat main...HEAD`):
28 arquivos alterados, 386 inserções(+) e 531 remoções(-).

## O que saiu
`GenericDao`, `GenericIDao`, `UsuarioDao`, `PerfilDao`, `PessoaDao`, `ClienteDao`,
`BarbeiroDao`, `AdminDao`, `PersistenciaException` (nenhuma classe a lança mais) e o
`GenericDaoTest` (testava o `GenericDao`). O `JPAUtil` já tinha saído na Atividade 22.

## O que entrou
Seis interfaces `...Repository extends JpaRepository<..., Long>, ...IDao`. As
`...IDao` ficaram: o Service continua dependendo da interface, sem saber que o
Spring Data existe, e o `FakeUsuarioDao` dos testes não mudou.

## Resultado
Quase 510 linhas de código de acesso a dados escrito à mão deixaram de existir, e
o comportamento continua o mesmo: as 63 classes de teste antigas passam sem
alteração, mais 7 testes novos (`UsuarioServiceLoginUnicoTest` e
`UsuarioRepositoryTest`).