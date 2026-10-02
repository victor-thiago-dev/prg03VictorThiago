# Retrospectiva — Code Review (Checkpoint 1)

Corrigi um bug real apontado na revisão: setSenha/setLogin não validavam nada, permitindo burlar a regra de negócio depois de criado o objeto.
Um teste sugerido revelou que contemPalavraProibida usava equals() em vez de equalsIgnoreCase(), deixando "ADMIN" passar sem bloqueio.
Revisando a Ariane, vi um bom uso de polimorfismo (ProcessadorUsuario recebendo o tipo geral sem if/instanceof) e uma regex de CPF rejeitando CPF formatado.
Revisando o Arthur, encontrei atributos nomeados como componentes de tela (	xtnome), um matricula sem validação, e números soltos numa regra de senha sem constante nomeada.
As duas revisões me mostraram que nomenclatura e "números mágicos" passam despercebidos por quem escreve, mas saltam aos olhos de quem lê de fora.
