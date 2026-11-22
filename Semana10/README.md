# Atividade TDD — Semana 10

Este pacote contém os quatro projetos da atividade, com implementação, testes JUnit 5 e configuração JaCoCo.

## Projetos

- `bubblesort-tdd`: ordenação crescente usando Bubble Sort.
- `carrinho-compras-tdd`: inclusão e remoção de produtos, total e desconto de 10% a partir de R$ 500,00.
- `conta-bancaria-tdd-maven`: depósitos e saques sem permitir saldo negativo.
- `estoque-tdd`: entradas e retiradas sem permitir quantidade inválida ou estoque negativo.

## Como executar

É necessário ter Java 17 ou superior e Maven instalado.

Entre na pasta de cada projeto e execute:

```bash
mvn clean verify
```

O comando executa os testes, gera o relatório JaCoCo e verifica os limites de cobertura configurados.

O relatório HTML fica em:

```text
target/site/jacoco/index.html
```

A configuração exige 100% de cobertura de linhas e branches. Se algum requisito novo for acrescentado, crie primeiro o teste correspondente e mantenha os limites de cobertura.

## Decisões de comportamento

- Carrinho: produto com preço negativo lança `IllegalArgumentException`, conforme uma das alternativas previstas no enunciado.
- Conta bancária: depósitos e saques com valor zero ou negativo são ignorados; saque acima do saldo também é ignorado.
- Estoque: adicionar ou retirar quantidade zero/negativa é ignorado; retirada acima do estoque também é ignorada.
- Carrinho: o desconto é aplicado quando o subtotal é maior ou igual a R$ 500,00.

## Sobre TDD

O estado final contém testes e implementações. Para demonstrar o ciclo TDD durante a apresentação, explique a sequência RED (teste falhando), GREEN (implementação mínima) e REFACTOR (melhoria mantendo os testes passando). O ZIP final não contém o histórico de cada ciclo.
