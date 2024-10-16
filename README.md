# ✨Leilões TDS✨

## 💭 Contexto
&nbsp;  
Concluídos os ajustes iniciais, você e sua equipe percebem como é importante incluir, na rotina, ferramentas de gerenciamento de versões.  
Essa importância será ressaltada nesta nova etapa, em que será necessário criar novas funcionalidades no sistema.  
Para manter o trabalho organizado, será preciso usar ramificações (branches) em seu repositório GitHub.  
&nbsp;  

# 🎯 Objetivo da atividade:
&nbsp;  
Você deverá desenvolver as seguintes funcionalidades no projeto trabalhado na atividade anterior:  

- Operação de `venderProduto()` em `ProdutosDAO` – a função deve atualizar o status de um produto para “Vendido”.
- Operação de `listarProdutosVendidos()` em `ProdutosDAO` – a função deve buscar todos os produtos no banco de dados com o status “Vendido”.
- Deve ser implementada uma tela de Vendas exibindo apenas os itens com status “Vendido”.
- Deve ser implementada a funcionalidade de `venderProduto` na tela de listagem – já há um botão para isso, mas ele ainda não está funcionando.
- Deve ser implementada a navegação no botão Consultar Vendas para abrir a tela de Vendas.  
&nbsp;  

Os commits dessa atividade devem ser enviados para uma ramificação (branch) separada da utilizada anteriormente.  
Então, crie uma nova ramificação, chamada “melhorias”, com o comando `git branch`, e envie o registro de versionamento local para o repositório remoto, com o comando `git push`, apontando para essa nova ramificação.  
&nbsp;  

Para cada funcionalidade implementada deve ser feito um commit descrevendo o que foi implementado. Exemplo:  

- Commit #1: adicionado o botão Vender.
- Commit #2: criada a tela de vendas.
- Commit #3: filtro para listar apenas itens vendidos.  

Observação: essas descrições são apenas alguns exemplos. Sinta-se à vontade para inserir o texto que descreve melhor a sua atividade no versionamento.  
&nbsp;  

Após implementar e testar as novas funcionalidades, você deve mesclar a ramificação “melhorias” com a ramificação principal.  
&nbsp;  

Dica: se preferir, você pode fazer esse processo dentro da página do seu repositório remoto. Uma nova opção chamada **Compare & pull request** aparecerá após a nova ramificação receber seus commits:  

- Clique para obter mais opções
- Clique nela e, na nova tela que apareceu, selecione a opção **Create pull request** para abrir uma solicitação de mesclagem da ramificação “melhorias” com a principal.  
- Por fim, selecione a opção **Merge pull request** na nova tela para aprovar a mesclagem.
