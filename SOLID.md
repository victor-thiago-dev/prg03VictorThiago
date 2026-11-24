# Auditoria SOLID do projeto

## Task 01: um exemplo de cada princípio

| Princípio | Arquivo e linha | Por que é exemplo |
|---|---|---|
| **S**: Responsabilidade Única | `UsuarioService.java`, linha 16 | Só tem regras de negócio de usuário. A tela não valida, o DAO não julga. Só muda quando a regra muda. |
| **O**: Aberto/Fechado | `GenericDao.java`, linha 16, e `PerfilDao.java`, linha 11 | O `GenericDao` foi escrito uma vez. Cada entidade nova ganha um DAO só estendendo (`PerfilDao extends GenericDao<Perfil>`), sem editar o genérico. |
| **L**: Substituição de Liskov | `UsuarioDao.java`, linha 13, e `Usuario.java`, linha 47 | Onde se espera um `GenericDao`, o `UsuarioDao` entra sem surpresa. E `Usuario` **não** estende `Pessoa`: ele *tem* uma (`private Pessoa pessoa`). "Todo Usuario é uma Pessoa" soa errado, então a relação é composição. |
| **I**: Segregação de Interfaces | `UsuarioIDao.java`, linha 14 | `findByLogin` fica na interface do `Usuario`, e não na `GenericIDao`. O `PerfilDao` não é obrigado a implementar um método sem sentido para ele. |
| **D**: Inversão de Dependência | `UsuarioService.java`, linha 20 (construtor) | Depende de `UsuarioIDao` (abstração), e não de `UsuarioDao`. Recebe pelo construtor, então os testes usam o `FakeUsuarioDao` e o `Prg03victorthiago` entrega o `UsuarioDao` de verdade. |

## Task 02: violações encontradas

### Violação 1: SRP: `UsuarioCadastroView.salvar()` (linha 94)
O método fazia cinco trabalhos: ler os campos, validar a tela, montar o objeto, chamar o controller e exibir mensagens. Tinha mais de um motivo para mudar (layout, regra de tela, tratamento de erro). **Corrigida** (Task 03).

### Violação 2: SRP e direção de dependência: `ValidadorUsuario` e `RegrasPessoa`
`ValidadorUsuario.cpfValido` (linha 45) guarda uma regra de **Pessoa** dentro de uma classe de **Usuario**. Por causa disso, `RegrasPessoa.java` (pacote `pessoa`, linha 11) importava `usuario.validar`: o domínio `pessoa` dependia de `usuario`, no sentido contrário ao do modelo (o `Usuario` é que *tem* uma `Pessoa`). **Corrigida** (Task 03).

### Violação 3: OCP: `UsuarioService.save()` (linha 26)
É uma sequência de `if`. Toda regra nova de cadastro obriga a abrir e editar um método que já funciona e já está testado. O ideal seria cada regra ser uma classe própria, com o `save()` percorrendo uma lista. **Não corrigida:** exigiria mudar o construtor do `UsuarioService`, e o ganho hoje não compensa o custo (princípio não é lei).

### Violação 4: DIP: `GenericDao.java`, linha 21
O construtor público usa `JPAUtil::getEntityManager`, uma dependência concreta e estática. Só o construtor `protected` (que recebe um `Supplier`) permite trocar a origem. **Não corrigida:** é o que permite os DAOs específicos chamarem só `super(Usuario.class)`.

## Task 03: correções feitas
Descritas no Pull Request: violações 1 e 2.